package com.labapp.presentation.technician;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b0\b\u0086\b\u0018\u00002\u00020\u0001B\u00d1\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f\u0012\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0011\u0012\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u0011\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u001aJ\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\u0015\u00101\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0011H\u00c6\u0003J\u0015\u00102\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u0011H\u00c6\u0003J\t\u00103\u001a\u00020\u0015H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\t0\u0005H\u00c6\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u0010<\u001a\u00020\fH\u00c6\u0003J\t\u0010=\u001a\u00020\fH\u00c6\u0003J\t\u0010>\u001a\u00020\fH\u00c6\u0003J\t\u0010?\u001a\u00020\fH\u00c6\u0003J\u00d5\u0001\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00112\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\fH\u00c6\u0001J\u0013\u0010A\u001a\u00020\u00032\b\u0010B\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010C\u001a\u00020\u0015H\u00d6\u0001J\t\u0010D\u001a\u00020\fH\u00d6\u0001R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0019\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u001fR\u0011\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u001fR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0011\u0010\r\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\u0017\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0013\u0010\u0018\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001eR\u0013\u0010\n\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010(\u00a8\u0006E"}, d2 = {"Lcom/labapp/presentation/technician/CreateReportUiState;", "", "isLoading", "", "templates", "", "Lcom/labapp/data/model/TemplateDoc;", "selectedTemplate", "referralDoctors", "Lcom/labapp/data/model/ReferralDoctorDoc;", "selectedDoctor", "patientName", "", "patientAge", "patientSex", "patientMobile", "parameterValues", "", "parameterFlags", "Lcom/labapp/data/model/ValueFlag;", "currentStep", "", "isSaving", "saveSuccess", "savedReportId", "error", "(ZLjava/util/List;Lcom/labapp/data/model/TemplateDoc;Ljava/util/List;Lcom/labapp/data/model/ReferralDoctorDoc;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;IZZLjava/lang/String;Ljava/lang/String;)V", "getCurrentStep", "()I", "getError", "()Ljava/lang/String;", "()Z", "getParameterFlags", "()Ljava/util/Map;", "getParameterValues", "getPatientAge", "getPatientMobile", "getPatientName", "getPatientSex", "getReferralDoctors", "()Ljava/util/List;", "getSaveSuccess", "getSavedReportId", "getSelectedDoctor", "()Lcom/labapp/data/model/ReferralDoctorDoc;", "getSelectedTemplate", "()Lcom/labapp/data/model/TemplateDoc;", "getTemplates", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_technicianDebug"})
public final class CreateReportUiState {
    private final boolean isLoading = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.labapp.data.model.TemplateDoc> templates = null;
    @org.jetbrains.annotations.Nullable()
    private final com.labapp.data.model.TemplateDoc selectedTemplate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.labapp.data.model.ReferralDoctorDoc> referralDoctors = null;
    @org.jetbrains.annotations.Nullable()
    private final com.labapp.data.model.ReferralDoctorDoc selectedDoctor = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String patientName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String patientAge = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String patientSex = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String patientMobile = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.String> parameterValues = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, com.labapp.data.model.ValueFlag> parameterFlags = null;
    private final int currentStep = 0;
    private final boolean isSaving = false;
    private final boolean saveSuccess = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String savedReportId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public CreateReportUiState(boolean isLoading, @org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.TemplateDoc> templates, @org.jetbrains.annotations.Nullable()
    com.labapp.data.model.TemplateDoc selectedTemplate, @org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.ReferralDoctorDoc> referralDoctors, @org.jetbrains.annotations.Nullable()
    com.labapp.data.model.ReferralDoctorDoc selectedDoctor, @org.jetbrains.annotations.NotNull()
    java.lang.String patientName, @org.jetbrains.annotations.NotNull()
    java.lang.String patientAge, @org.jetbrains.annotations.NotNull()
    java.lang.String patientSex, @org.jetbrains.annotations.NotNull()
    java.lang.String patientMobile, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> parameterValues, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends com.labapp.data.model.ValueFlag> parameterFlags, int currentStep, boolean isSaving, boolean saveSuccess, @org.jetbrains.annotations.Nullable()
    java.lang.String savedReportId, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.labapp.data.model.TemplateDoc> getTemplates() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.labapp.data.model.TemplateDoc getSelectedTemplate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.labapp.data.model.ReferralDoctorDoc> getReferralDoctors() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.labapp.data.model.ReferralDoctorDoc getSelectedDoctor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPatientName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPatientAge() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPatientSex() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPatientMobile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> getParameterValues() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.labapp.data.model.ValueFlag> getParameterFlags() {
        return null;
    }
    
    public final int getCurrentStep() {
        return 0;
    }
    
    public final boolean isSaving() {
        return false;
    }
    
    public final boolean getSaveSuccess() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSavedReportId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public CreateReportUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.labapp.data.model.ValueFlag> component11() {
        return null;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final boolean component13() {
        return false;
    }
    
    public final boolean component14() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.labapp.data.model.TemplateDoc> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.labapp.data.model.TemplateDoc component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.labapp.data.model.ReferralDoctorDoc> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.labapp.data.model.ReferralDoctorDoc component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.labapp.presentation.technician.CreateReportUiState copy(boolean isLoading, @org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.TemplateDoc> templates, @org.jetbrains.annotations.Nullable()
    com.labapp.data.model.TemplateDoc selectedTemplate, @org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.ReferralDoctorDoc> referralDoctors, @org.jetbrains.annotations.Nullable()
    com.labapp.data.model.ReferralDoctorDoc selectedDoctor, @org.jetbrains.annotations.NotNull()
    java.lang.String patientName, @org.jetbrains.annotations.NotNull()
    java.lang.String patientAge, @org.jetbrains.annotations.NotNull()
    java.lang.String patientSex, @org.jetbrains.annotations.NotNull()
    java.lang.String patientMobile, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> parameterValues, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends com.labapp.data.model.ValueFlag> parameterFlags, int currentStep, boolean isSaving, boolean saveSuccess, @org.jetbrains.annotations.Nullable()
    java.lang.String savedReportId, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}