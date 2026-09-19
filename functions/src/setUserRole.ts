import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin';

export const setUserRole = functions
  .region('asia-south1')
  .https.onCall(async (data, context) => {
    const isLocalDev = process.env.FUNCTIONS_EMULATOR === 'true';
    if (!isLocalDev) {
      if (!context.auth || context.auth.token.role !== 'admin') {
        throw new functions.https.HttpsError(
          'permission-denied',
          'Only admins can assign roles.'
        );
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
    } catch (error) {
      console.error('setUserRole error:', error);
      throw new functions.https.HttpsError('internal', 'Failed to set user role.');
    }
  });
