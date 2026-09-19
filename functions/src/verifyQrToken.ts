import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin';
import jwt from 'jsonwebtoken';

export const verifyAndServeReport = functions
  .region('asia-south1')
  .https.onRequest(async (req, res) => {
    // CORS headers (allow all origins — this is the public report viewer)
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Access-Control-Allow-Methods', 'GET');

    const token = req.query.token as string;

    if (!token) {
      res.status(400).json({ error: 'Token is required.' });
      return;
    }

    const jwtSecret = process.env.JWT_SECRET || 'dev_secret_key';

    let decoded: jwt.JwtPayload;
    try {
      decoded = jwt.verify(token, jwtSecret, {
        algorithms: ['HS256'],
      }) as jwt.JwtPayload;
    } catch (error) {
      if (error instanceof jwt.TokenExpiredError) {
        res.status(403).json({
          error: 'Report has expired.',
          expiredAt: error.expiredAt,
        });
      } else {
        res.status(401).json({ error: 'Invalid or tampered token.' });
      }
      return;
    }

    const reportId = decoded.reportId as string;
    if (!reportId || decoded.type !== 'report_view') {
      res.status(400).json({ error: 'Invalid token payload.' });
      return;
    }

    try {
      const reportDoc = await admin.firestore()
        .doc(`reports/${reportId}`)
        .get();

      if (!reportDoc.exists) {
        res.status(404).json({ error: 'Report not found.' });
        return;
      }

      const report = reportDoc.data();
      if (!report) return;

      if (report.status !== 'done') {
        res.status(202).json({ error: 'Report is still being processed.' });
        return;
      }

      // Return safe report data — never return raw Firestore doc
      res.status(200).json({
        reportId: report.reportId,
        patientName: report.patientSnapshot.name,
        patientAge: report.patientSnapshot.age,
        patientSex: report.patientSnapshot.sex,
        testType: report.testType,
        reportDate: report.completedAt,
        labName: report.technicianSnapshot.labName,
        referralDoctor: report.referralDoctorSnapshot.isSelf
          ? 'Self'
          : report.referralDoctorSnapshot.name,
        values: report.values,
      });
    } catch (error) {
      console.error('verifyAndServeReport error:', error);
      res.status(500).json({ error: 'Failed to retrieve report.' });
    }
  });
