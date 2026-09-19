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
Object.defineProperty(exports, "__esModule", { value: true });
exports.setUserRole = void 0;
const functions = __importStar(require("firebase-functions"));
const admin = __importStar(require("firebase-admin"));
exports.setUserRole = functions
    .region('asia-south1')
    .https.onCall(async (data, context) => {
    const isLocalDev = process.env.FUNCTIONS_EMULATOR === 'true';
    if (!isLocalDev) {
        if (!context.auth || context.auth.token.role !== 'admin') {
            throw new functions.https.HttpsError('permission-denied', 'Only admins can assign roles.');
        }
    }
    const { targetUserId, role, approved } = data;
    if (!targetUserId || typeof targetUserId !== 'string') {
        throw new functions.https.HttpsError('invalid-argument', 'targetUserId is required.');
    }
    if (role !== 'admin' && role !== 'technician') {
        throw new functions.https.HttpsError('invalid-argument', 'role must be admin or technician.');
    }
    try {
        await admin.auth().setCustomUserClaims(targetUserId, {
            role,
            approved: approved === true,
        });
        const batch = admin.firestore().batch();
        const userRef = admin.firestore().doc(`users/${targetUserId}`);
        batch.update(userRef, {
            role,
            approvedAt: approved ? admin.firestore.FieldValue.serverTimestamp() : null,
            approvedBy: context.auth ? context.auth.uid : 'system',
        });
        await batch.commit();
        return { success: true };
    }
    catch (error) {
        console.error('setUserRole error:', error);
        throw new functions.https.HttpsError('internal', 'Failed to set user role.');
    }
});
//# sourceMappingURL=setUserRole.js.map