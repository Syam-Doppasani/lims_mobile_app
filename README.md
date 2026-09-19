# LIMS Mobile App

A role-based Android application for diagnostic laboratories to create, manage, generate, verify, and deliver laboratory reports from a mobile workflow.

The project contains two Android product flavors:

* **Lab Admin** — manages users, approvals, templates, and template assignments.
* **Lab Reporter** — used by laboratory technicians to manage patients, referral doctors, enter test results, create reports, and access generated reports.

The backend is built around Firebase Authentication, Cloud Firestore, Cloud Storage, and Cloud Functions. Generated reports are rendered as A4 PDFs, stored in Cloud Storage, exposed through time-limited signed URLs, and accompanied by QR-based online verification.

## Features

### Authentication & Access Control

* Email/password authentication through Firebase Authentication.
* Separate Admin and Technician application flavors.
* Technician registration with an approval workflow.
* Role and approval state enforced through Firebase custom claims.
* Pending-approval screen for technicians who have not yet been approved.
* Admin-controlled user role assignment and approval.

### Admin

* Admin dashboard.
* User management.
* Technician approval and role management.
* Laboratory report template list and editor.
* Template assignment to technicians.
* Role-aware access controlled by Firestore security rules.

### Technician / Lab Reporter

* Technician dashboard with report statistics.
* Patient management.
* Referral doctor management.
* Automatic **Self** referral-doctor handling.
* Assigned-template based report creation.
* Laboratory result entry.
* Result flagging using configured reference ranges.
* Report history and report details.
* Report actions for generated reports.

### Report Generation

* Cloud Function triggered report generation.
* HTML templating with Handlebars.
* A4 PDF generation with Puppeteer.
* Lab branding and logo support.
* Patient and referral-doctor snapshots embedded in reports.
* Result values with abnormal/reference-range flags.
* Unique report IDs.
* QR code embedded in generated PDFs.
* Time-limited QR verification tokens.
* PDF storage in Firebase Cloud Storage.
* Signed PDF URLs for controlled access.

### Online QR Verification

Generated reports include a QR code that points to the hosted report viewer.

Verification flow:

1. Scan the QR code.
2. Open the hosted report viewer.
3. Send the token to the verification Cloud Function.
4. Verify the JWT signature and expiration.
5. Retrieve the report from Firestore.
6. Return a limited report representation to the viewer.

The public viewer does not receive the raw Firestore report document.

### WhatsApp Delivery

* Generated PDFs can be delivered through the WhatsApp Cloud API.
* Self-referred reports are sent to the patient's mobile number.
* Referred reports are sent to the referral doctor's mobile number.
* Emulator/testing mode uses a stub when WhatsApp credentials are unavailable.

## Architecture

```text
Android App
│
├── Presentation
│   ├── Authentication
│   ├── Admin UI
│   └── Technician UI
│
├── Domain
│   ├── Use cases
│   └── Domain models
│
├── Data
│   ├── Models
│   └── Repository implementations
│
└── Core
    ├── Dependency Injection
    └── Shared utilities

Firebase
│
├── Authentication
├── Firestore
├── Cloud Storage
└── Cloud Functions
    ├── User role management
    ├── PDF report generation
    ├── QR verification
    └── WhatsApp delivery

Firebase Hosting
└── Public report viewer
```

## Tech Stack

### Android

* Kotlin
* Jetpack Compose
* Material 3
* AndroidX Navigation Compose
* Hilt / Dagger
* Kotlin Coroutines
* Coil
* ZXing Embedded
* MPAndroidChart
* Timber

### Backend & Infrastructure

* Firebase Authentication
* Cloud Firestore
* Firebase Cloud Storage
* Firebase Cloud Functions
* Firebase Hosting
* Node.js 20
* TypeScript

### Backend Libraries

* Firebase Admin SDK
* Firebase Functions SDK
* Axios
* Handlebars
* Puppeteer
* JSON Web Token (JWT)
* QRCode

### Networking

* Retrofit
* Gson converter
* OkHttp logging interceptor

## Android Configuration

The Android module currently uses:

* **minSdk:** 26
* **targetSdk:** 34
* **compileSdk:** 34
* **Java:** 17
* **Kotlin:** 1.9.22
* **Android Gradle Plugin:** 8.2.2

### Product Flavors

| Flavor       | Application ID       | App Name     |
| ------------ | -------------------- | ------------ |
| `admin`      | `faition.admin`      | Lab Admin    |
| `technician` | `faition.technician` | Lab Reporter |

## Project Structure

```text
lims_mobile_app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/labapp/
│   │   │   │   ├── core/
│   │   │   │   │   ├── di/
│   │   │   │   │   └── util/
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/
│   │   │   │   │   └── repository/
│   │   │   │   ├── domain/
│   │   │   │   │   ├── model/
│   │   │   │   │   └── usecase/
│   │   │   │   ├── presentation/
│   │   │   │   │   ├── auth/
│   │   │   │   │   └── theme/
│   │   │   │   └── LabApp.kt
│   │   │   └── res/
│   │   ├── admin/
│   │   │   └── java/com/labapp/presentation/admin/
│   │   ├── technician/
│   │   │   └── java/com/labapp/presentation/technician/
│   │   └── test/
│   └── build.gradle.kts
│
├── functions/
│   ├── src/
│   │   ├── generateReport.ts
│   │   ├── sendWhatsApp.ts
│   │   ├── setUserRole.ts
│   │   ├── verifyQrToken.ts
│   │   └── util/
│   ├── templates/
│   │   └── report.html
│   └── package.json
│
├── hosting/
│   ├── index.html
│   └── viewer.js
│
├── firestore.rules
├── firestore.indexes.json
├── storage.rules
├── firebase.json
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## Firestore Data Model

The main application collections are:

### `users`

Stores application users, roles, approval state, and profile information.

Supported roles:

* `admin`
* `technician`

Technicians can only update their own permitted profile fields. User deletion is disabled.

### `templates`

Stores report templates and their configuration.

Admins have full access. Approved technicians can read active templates assigned to them.

### `templateAssignments`

Maps templates to technicians.

Admins can manage assignments. Technicians can read their own assignments.

### `patients`

Stores patient information such as:

* Name
* Age
* Sex
* Mobile number
* Technician ownership

Technicians can access their own patient records.

### `referralDoctors`

Stores referral-doctor information associated with a technician.

A dedicated self-referral entry is supported.

### `reports`

Stores report records, snapshots, processing state, result values, generated PDF metadata, QR token information, and delivery metadata.

Technicians create pending reports and can read their own reports. Report updates are restricted from direct client writes and are handled by backend functions.

## Security Model

Firestore rules enforce role-based and ownership-based access.

Key rules include:

* Protected application data requires authentication.
* Admin access is based on the `role == 'admin'` custom claim.
* Technician access is based on the `role == 'technician'` custom claim.
* Technician workflows also require the `approved == true` custom claim where applicable.
* Patients and reports are scoped to their owning technician.
* Technicians cannot modify reports after creation.
* Technicians cannot delete patient records.
* User deletion is disabled.
* Trusted backend operations are performed by Cloud Functions using the Firebase Admin SDK.

Firebase Storage rules provide:

* Authenticated access to lab logos.
* Technician-owned logo uploads.
* A 2 MB logo upload limit.
* Image-only logo uploads.
* Backend-only writes for generated report files.

## Report Lifecycle

```text
Technician
   │
   ├── Select patient
   ├── Select referral doctor
   ├── Select assigned template
   ├── Enter results
   └── Create report
            │
            ▼
       Firestore report
       status = pending
            │
            ▼
    Cloud Function trigger
            │
            ├── Apply result flags
            ├── Generate QR token
            ├── Render HTML
            ├── Generate A4 PDF
            ├── Upload PDF to Storage
            ├── Generate signed URL
            ├── Update Firestore
            └── Send through WhatsApp
            │
            ▼
       status = done
```

When processing fails, the report is marked as `failed` and an error message is stored.

## QR Verification Lifecycle

```text
PDF QR Code
    │
    ▼
Hosted Report Viewer
    │
    ▼
verifyAndServeReport
    │
    ├── Validate JWT signature
    ├── Validate expiration
    ├── Validate token type
    ├── Load report from Firestore
    └── Return safe report fields
```

The verification function only exposes selected report fields to the public viewer.

## WhatsApp Integration

The backend supports WhatsApp Cloud API delivery.

Required environment variables:

```text
WHATSAPP_PHONE_NUMBER_ID
WHATSAPP_ACCESS_TOKEN
```

When these values are unavailable, the function switches to testing/stub behavior.

## Local Development

### Prerequisites

Install:

* Android Studio
* JDK 17
* Node.js 20
* npm
* Firebase CLI
* A configured Firebase project

### Clone

```bash
git clone https://github.com/Syam-Doppasani/lims_mobile_app.git
cd lims_mobile_app
```

### Firebase Configuration

The Android app expects Firebase configuration in:

```text
app/google-services.json
```

Enable/configure the Firebase services used by the application:

* Authentication
* Firestore
* Storage
* Cloud Functions
* Hosting

Firebase deployment configuration is defined in:

```text
firebase.json
firestore.rules
firestore.indexes.json
storage.rules
```

### Build the Android App

Linux/macOS:

```bash
./gradlew assemble
```

Windows:

```bat
gradlew.bat assemble
```

Build the development flavors individually:

```bash
./gradlew assembleAdminDebug
./gradlew assembleTechnicianDebug
```

Build release artifacts:

```bash
./gradlew assembleAdminRelease
./gradlew assembleTechnicianRelease
```

The repository currently also contains generated APKs under `dist/`.

### Build Cloud Functions

```bash
cd functions
npm install
npm run build
```

### Run Functions Locally

```bash
npm run serve
```

### Deploy Functions

```bash
cd functions
npm run deploy
```

Firebase Hosting, Firestore rules/indexes, and Storage rules can be deployed through the Firebase CLI from the project root.

## Testing

Android unit tests are located under:

```text
app/src/test/
```

The repository includes domain-level unit testing for result flag computation.

Run tests with:

```bash
./gradlew test
```

Windows:

```bat
gradlew.bat test
```

## Environment & Secrets

Do not commit production secrets to source control.

Backend integrations use environment variables for sensitive credentials, including the WhatsApp access token and JWT secret.

Example:

```text
JWT_SECRET=<strong-random-secret>
WHATSAPP_PHONE_NUMBER_ID=<whatsapp-phone-number-id>
WHATSAPP_ACCESS_TOKEN=<whatsapp-access-token>
```

Production deployments should replace development/default values with securely managed secrets.

## Important Security Considerations

This application processes laboratory and patient information. Before production deployment, review and harden:

* Firebase Authentication configuration.
* Firestore and Storage security rules.
* Custom claim assignment and administrative access.
* JWT secret management.
* QR token lifetime and abuse protection.
* Signed PDF URL lifetime and sharing.
* WhatsApp API credentials.
* Logging so sensitive patient data is not unnecessarily exposed.
* Firebase project IAM permissions.
* Applicable legal, privacy, medical-record, and data-protection requirements.

## Deployment Components

### Android

Build and distribute the required product flavor:

* **Lab Admin**
* **Lab Reporter**

### Firebase Cloud Functions

Backend source is in:

```text
functions/src/
```

### Firebase Hosting

The public report viewer is stored in:

```text
hosting/
```

The Firebase configuration rewrites:

```text
/report/**  ->  /index.html
**          ->  /index.html
```

This lets QR links resolve through the hosted report viewer.

## Backend Functions

### `setUserRole`

Assigns Firebase custom claims for a user and updates the corresponding Firestore user record.

### `generateReport`

Handles:

* Result flagging
* QR token generation
* HTML rendering
* A4 PDF creation
* Storage upload
* Signed URL creation
* Firestore report update
* WhatsApp delivery

### `verifyAndServeReport`

Validates the QR/JWT token and returns safe report data to the hosted viewer.

### `sendWhatsApp`

Sends the generated PDF through WhatsApp Cloud API or uses the testing stub when credentials are unavailable.

## Development Principles

The codebase separates responsibilities across:

* **Presentation** — UI and screen state.
* **ViewModels** — user interaction and UI orchestration.
* **Domain use cases** — application/business logic.
* **Repositories** — data access abstraction.
* **Firebase** — authentication, storage, and persistence.
* **Cloud Functions** — trusted server-side workflows.
* **Hosting** — public report verification experience.

This structure is intended to make the application easier to extend with additional test types, templates, workflows, and delivery channels.

## Roadmap

Potential future improvements include:

* Audit trails for administrative actions.
* More granular lab/team permissions.
* Offline-first result entry with controlled synchronization.
* Broader automated test coverage.
* PDF/report regression testing.
* Template versioning.
* Report correction/amendment workflows with immutable history.
* WhatsApp delivery status tracking.
* Rate limiting and abuse protection for public QR verification.
* Centralized production secret management.
* Structured monitoring and alerting.
