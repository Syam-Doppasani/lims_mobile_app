(async function () {
  const params = new URLSearchParams(window.location.search);
  const token = params.get('token');

  const container = document.getElementById('report-container');
  const loading = document.getElementById('loading');
  const errorBox = document.getElementById('error');

  if (!token) {
    showError('No report token found. Please scan the QR code again.');
    return;
  }

  try {
    const response = await fetch(
      `https://asia-south1-YOUR_PROJECT_ID.cloudfunctions.net/verifyAndServeReport?token=${encodeURIComponent(token)}`
    );

    const data = await response.json();
    loading.style.display = 'none';

    if (!response.ok) {
      if (response.status === 403) {
        showError('This report has expired (30-day access period has ended).');
      } else if (response.status === 404) {
        showError('Report not found.');
      } else {
        showError(data.error || 'Failed to load report.');
      }
      return;
    }

    renderReport(data);
  } catch (e) {
    loading.style.display = 'none';
    showError('Network error. Please check your connection and try again.');
  }

  function showError(message) {
    loading.style.display = 'none';
    errorBox.textContent = message;
    errorBox.style.display = 'block';
    container.style.display = 'none';
  }

  function renderReport(data) {
    const dateStr = data.reportDate ? new Date(data.reportDate._seconds * 1000).toLocaleDateString('en-IN', {
      day: '2-digit', month: 'long', year: 'numeric'
    }) : '';

    container.innerHTML = `
      <div class="header">
        <div>
          <div class="lab-name">${escapeHtml(data.labName)}</div>
          <div class="subtitle">Online Medical Diagnostic Report</div>
        </div>
      </div>

      <div class="patient-section">
        <div class="patient-item"><span class="patient-label">Patient Name</span><span class="patient-value">${escapeHtml(data.patientName)}</span></div>
        <div class="patient-item"><span class="patient-label">Age / Sex</span><span class="patient-value">${escapeHtml(data.patientAge)} yrs / ${escapeHtml(data.patientSex)}</span></div>
        <div class="patient-item"><span class="patient-label">Report Date</span><span class="patient-value">${dateStr}</span></div>
        <div class="patient-item"><span class="patient-label">Ref. Doctor</span><span class="patient-value">${escapeHtml(data.referralDoctor)}</span></div>
      </div>

      <div class="report-title">${escapeHtml(data.testType)}</div>

      <table>
        <thead>
          <tr>
            <th>Parameter</th>
            <th>Result</th>
            <th>Unit</th>
            <th>Reference Range</th>
          </tr>
        </thead>
        <tbody>
          ${data.values.map(v => {
            const isAbnormal = v.flag === 'HIGH' || v.flag === 'LOW';
            const valueDisplay = v.flag === 'HIGH' ? `<span class="abnormal-value">${escapeHtml(v.value)} ↑</span>` :
                                 v.flag === 'LOW' ? `<span class="abnormal-value">${escapeHtml(v.value)} ↓</span>` :
                                 escapeHtml(v.value);
            return `
              <tr class="${isAbnormal ? 'abnormal' : ''}">
                <td>${escapeHtml(v.parameterName)}</td>
                <td>${valueDisplay}</td>
                <td>${escapeHtml(v.unit)}</td>
                <td>${v.normalMin} – ${v.normalMax}</td>
              </tr>
            `;
          }).join('')}
        </tbody>
      </table>

      <div class="signature">
        <div style="border-top: 1px solid #334155; width: 160px; display: inline-block; padding-top: 4px; text-align: center;">
          Authorised Signatory
        </div>
      </div>

      <div class="footer">
        * This is a secure digitally generated report. Access is active for 30 days from generation.
      </div>
    `;
    container.style.display = 'block';
  }

  function escapeHtml(unsafe) {
    if (!unsafe) return '';
    return unsafe.toString()
      .replace(/&/g, "&amp;")
      .replace(/</g, "&lt;")
      .replace(/>/g, "&gt;")
      .replace(/"/g, "&quot;")
      .replace(/'/g, "&#039;");
  }
})();
