import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin';
import puppeteer from 'puppeteer';
import * as fs from 'fs';
import * as path from 'path';
import Handlebars from 'handlebars';
import QRCode from 'qrcode';
import jwt from 'jsonwebtoken';
import { computeFlag } from './util/flagging';
import { sendWhatsApp } from './sendWhatsApp';

// Load template once at cold start
const templateSource = fs.readFileSync(
  path.join(__dirname, '../templates/report.html'),
  'utf-8'
);
Handlebars.registerHelper('eq', (a: string, b: string) => a === b);
const compiledTemplate = Handlebars.compile(templateSource);

export const onReportCreated = functions
  .region('asia-south1')
  .runWith({ timeoutSeconds: 120, memory: '1GB' })
  .firestore.document('reports/{reportId}')
  .onCreate(async (snapshot, context) => {
    const reportId = context.params.reportId;
    const report = snapshot.data();
    if (!report) return;
    const db = admin.firestore();
    const storage = admin.storage().bucket();

    const reportRef = db.doc(`reports/${reportId}`);

    try {
      // 1. Mark as processing
      await reportRef.update({ status: 'processing' });

      // 2. Re-compute flags server-side for consistency
      const valuesWithFlags = report.values.map((v: any) => ({
        ...v,
        flag: computeFlag(v.value, findParameter(report, v.parameterId), report.patientSnapshot.sex),
      }));

      // 3. Generate QR token (JWT signed with secret)
      const jwtSecret = process.env.JWT_SECRET || 'dev_secret_key';

      const expiresAt = new Date();
      expiresAt.setDate(expiresAt.getDate() + 30);

      const token = jwt.sign(
        { reportId, sub: reportId, type: 'report_view' },
        jwtSecret,
        { expiresIn: '30d', algorithm: 'HS256' }
      );

      const hostingDomain = process.env.HOSTING_DOMAIN || 'YOUR_PROJECT_ID.web.app';
      const reportViewerUrl =
        `https://${hostingDomain}/report?token=${encodeURIComponent(token)}`;

      // 4. Generate QR code as base64 PNG
      const qrCodeDataUrl = await QRCode.toDataURL(reportViewerUrl, {
        errorCorrectionLevel: 'H',
        margin: 2,
        width: 200,
      });

      // 5. Prepare template data
      const templateData = {
        labName: report.technicianSnapshot.labName,
        labLogoUrl: report.technicianSnapshot.labLogoUrl,
        patientName: report.patientSnapshot.name,
        patientAge: report.patientSnapshot.age,
        patientSex: report.patientSnapshot.sex,
        patientMobile: report.patientSnapshot.mobile,
        testType: report.testType,
        referralDoctor: report.referralDoctorSnapshot.isSelf
          ? 'Self'
          : report.referralDoctorSnapshot.name,
        reportDate: new Date().toLocaleDateString('en-IN', {
          day: '2-digit', month: 'long', year: 'numeric',
        }),
        reportId,
        values: valuesWithFlags,
        qrCodeDataUrl,
        qrExpiryDate: expiresAt.toLocaleDateString('en-IN', {
          day: '2-digit', month: 'long', year: 'numeric',
        }),
      };

      // 6. Render HTML
      const html = compiledTemplate(templateData);

      // 7. Launch Puppeteer and generate PDF
      const browser = await puppeteer.launch({
        headless: true,
        args: [
          '--no-sandbox',
          '--disable-setuid-sandbox',
          '--disable-dev-shm-usage',
          '--disable-gpu',
        ],
      });

      let pdfBuffer: Buffer;
      try {
        const page = await browser.newPage();
        await page.setContent(html, { waitUntil: 'networkidle0' });
        pdfBuffer = Buffer.from(await page.pdf({
          format: 'A4',
          printBackground: true,
          margin: { top: '20mm', bottom: '20mm', left: '15mm', right: '15mm' },
        }));
      } finally {
        await browser.close();
      }

      // 8. Upload PDF to Cloud Storage
      const storagePath = `reports/${reportId}/report.pdf`;
      const file = storage.file(storagePath);
      await file.save(pdfBuffer, {
        metadata: {
          contentType: 'application/pdf',
          metadata: { reportId, technicianId: report.technicianId },
        },
      });

      // 9. Generate a signed URL valid for 7 days
      const [pdfUrl] = await file.getSignedUrl({
        action: 'read',
        expires: new Date(Date.now() + 7 * 24 * 60 * 60 * 1000),
      });

      // 10. Update report document with all results
      const updatedReport = {
        status: 'done',
        pdfUrl,
        pdfStoragePath: storagePath,
        qrToken: token,
        qrTokenExpiresAt: admin.firestore.Timestamp.fromDate(expiresAt),
        values: valuesWithFlags,
        completedAt: admin.firestore.FieldValue.serverTimestamp(),
      };
      await reportRef.update(updatedReport);

      // 11. Trigger WhatsApp delivery
      const fullReport = { ...report, ...updatedReport };
      await sendWhatsApp(fullReport, pdfUrl);

    } catch (error) {
      console.error(`Report generation failed for ${reportId}:`, error);
      await reportRef.update({
        status: 'failed',
        errorMessage: error instanceof Error ? error.message : 'Unknown error during PDF generation.',
      });
    }
  });

function findParameter(report: any, parameterId: string): any {
  const value = report.values.find((v: any) => v.parameterId === parameterId);
  if (!value) throw new Error(`Parameter ${parameterId} not found in report.`);
  return {
    id: parameterId,
    name: value.parameterName,
    unit: value.unit,
    normalMinMale: value.normalMin ?? 0,
    normalMaxMale: value.normalMax ?? 0,
    normalMinFemale: value.normalMin ?? 0,
    normalMaxFemale: value.normalMax ?? 0,
    inputType: 'number',
  };
}
