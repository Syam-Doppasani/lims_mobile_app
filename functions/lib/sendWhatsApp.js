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
exports.sendWhatsApp = sendWhatsApp;
const admin = __importStar(require("firebase-admin"));
const axios_1 = __importDefault(require("axios"));
const WHATSAPP_API_VERSION = 'v19.0';
async function sendWhatsApp(report, pdfUrl) {
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
    const caption = `*${labName}*\n` +
        `Patient: ${patientName}\n` +
        `Test: ${testType}\n` +
        `Please find the attached lab report.\n` +
        `\n_This report is valid for 30 days. Scan the QR code in the PDF to view online._`;
    const apiUrl = `https://graph.facebook.com/${WHATSAPP_API_VERSION}/${phoneNumberId}/messages`;
    try {
        const response = await axios_1.default.post(apiUrl, {
            messaging_product: 'whatsapp',
            recipient_type: 'individual',
            to: toNumber,
            type: 'document',
            document: {
                link: pdfUrl,
                filename: `${patientName.replace(/\s+/g, '_')}_${testType}_Report.pdf`,
                caption,
            },
        }, {
            headers: {
                Authorization: `Bearer ${accessToken}`,
                'Content-Type': 'application/json',
            },
            timeout: 30000,
        });
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
    }
    catch (error) {
        if (axios_1.default.isAxiosError(error)) {
            const detail = error.response?.data
                ? JSON.stringify(error.response.data)
                : error.message;
            throw new Error(`WhatsApp delivery failed: ${detail}`);
        }
        throw error;
    }
}
//# sourceMappingURL=sendWhatsApp.js.map