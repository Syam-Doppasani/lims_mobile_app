import * as admin from 'firebase-admin';

admin.initializeApp();

export { onReportCreated } from './generateReport';
export { verifyAndServeReport } from './verifyQrToken';
export { setUserRole } from './setUserRole';
