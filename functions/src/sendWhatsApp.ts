import * as admin from 'firebase-admin';
import axios from 'axios';

const WHATSAPP_API_VERSION = 'v19.0';

export async function sendWhatsApp(report: any, pdfUrl: string): Promise<void> {
  const phoneNumberId = process.env.WHATSAPP_PHONE_NUMBER_ID;
  const accessToken = process.env.WHATSAPP_ACCESS_TOKEN;

  const recipientMobile = report.referralDoctorSnapshot.isSelf
    ? report.patientSnapshot.mobile
    : report.referralDoctorSnapshot.mobile;

  // Check if we are running in emulator OR if credentials are missing
  const isTestingMode = process.env.FUNCTIONS_EMULATOR === 'true' || !phoneNumberId || !accessToken;

  if (isTestingMode) {
    console.log(`[WhatsApp Stub] Sending report PDF to ${recipientMobile}`);
    console.log(`[WhatsApp Stub] PDF URL: ${pdfUrl}`);
    console.log(`[WhatsApp Stub] Patient Name: ${report.patientSnapshot.name}`);
    console.log(`[WhatsApp Stub] Test: ${report.testType}`);

    await admin.firestore()
      .doc(`reports/${report.reportId}`)
      .update({
        whatsappSentAt: admin.firestore.FieldValue.serverTimestamp(),
        whatsappRecipientMobile: recipientMobile,
      });
    return;
  }

  const toNumber = recipientMobile.replace(/^\+/, '');

  const patientName = report.patientSnapshot.name;
  const testType = report.testType;
  const labName = report.technicianSnapshot.labName;

  const caption =
    `*${labName}*\n` +
    `Patient: ${patientName}\n` +
    `Test: ${testType}\n` +
    `Please find the attached lab report.\n` +
    `\n_This report is valid for 30 days. Scan the QR code in the PDF to view online._`;

  const apiUrl =
    `https://graph.facebook.com/${WHATSAPP_API_VERSION}/${phoneNumberId}/messages`;

  try {
    const response = await axios.post(
      apiUrl,
      {
        messaging_product: 'whatsapp',
        recipient_type: 'individual',
        to: toNumber,
        type: 'document',
        document: {
          link: pdfUrl,
          filename: `${patientName.replace(/\s+/g, '_')}_${testType}_Report.pdf`,
          caption,
        },
      },
      {
        headers: {
          Authorization: `Bearer ${accessToken}`,
          'Content-Type': 'application/json',
        },
        timeout: 30000,
      }
    );

    if (response.status !== 200) {
      throw new Error(`WhatsApp API returned status ${response.status}`);
    }

    await admin.firestore()
      .doc(`reports/${report.reportId}`)
      .update({
        whatsappSentAt: admin.firestore.FieldValue.serverTimestamp(),
        whatsappRecipientMobile: recipientMobile,
      });

    console.log(`WhatsApp sent to ${recipientMobile} for report ${report.reportId}`);
  } catch (error) {
    if (axios.isAxiosError(error)) {
      const detail = error.response?.data
        ? JSON.stringify(error.response.data)
        : error.message;
      throw new Error(`WhatsApp delivery failed: ${detail}`);
    }
    throw error;
  }
}
