"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || (function () {
    var ownKeys = function(o) {
        ownKeys = Object.getOwnPropertyNames || function (o) {
            var ar = [];
            for (var k in o) if (Object.prototype.hasOwnProperty.call(o, k)) ar[ar.length] = k;
            return ar;
        };
        return ownKeys(o);
    };
    return function (mod) {
        if (mod && mod.__esModule) return mod;
        var result = {};
        if (mod != null) for (var k = ownKeys(mod), i = 0; i < k.length; i++) if (k[i] !== "default") __createBinding(result, mod, k[i]);
        __setModuleDefault(result, mod);
        return result;
    };
})();
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.verifyAndServeReport = void 0;
const functions = __importStar(require("firebase-functions"));
const admin = __importStar(require("firebase-admin"));
const jsonwebtoken_1 = __importDefault(require("jsonwebtoken"));
exports.verifyAndServeReport = functions
    .region('asia-south1')
    .https.onRequest(async (req, res) => {
    // CORS headers (allow all origins — this is the public report viewer)
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Access-Control-Allow-Methods', 'GET');
    const token = req.query.token;
    if (!token) {
        res.status(400).json({ error: 'Token is required.' });
        return;
    }
    const jwtSecret = process.env.JWT_SECRET || 'dev_secret_key';
    let decoded;
    try {
        decoded = jsonwebtoken_1.default.verify(token, jwtSecret, {
            algorithms: ['HS256'],
        });
    }
    catch (error) {
        if (error instanceof jsonwebtoken_1.default.TokenExpiredError) {
            res.status(403).json({
                error: 'Report has expired.',
                expiredAt: error.expiredAt,
            });
        }
        else {
            res.status(401).json({ error: 'Invalid or tampered token.' });
        }
        return;
    }
    const reportId = decoded.reportId;
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
        if (!report)
            return;
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
    }
    catch (error) {
        console.error('verifyAndServeReport error:', error);
        res.status(500).json({ error: 'Failed to retrieve report.' });
    }
});
//# sourceMappingURL=verifyQrToken.js.map