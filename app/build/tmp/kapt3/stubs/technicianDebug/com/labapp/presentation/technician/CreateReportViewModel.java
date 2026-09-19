package com.labapp.presentation.technician;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\u0006\u0010\u0019\u001a\u00020\u0017J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fJ\u0016\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"J\u0006\u0010$\u001a\u00020\u0017J\u0006\u0010%\u001a\u00020\u0017J\u000e\u0010&\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020\"J\u000e\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\"J\u000e\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\"J\u000e\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\"J\u0012\u0010.\u001a\u0004\u0018\u00010\"2\u0006\u0010/\u001a\u000200H\u0002R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u00061"}, d2 = {"Lcom/labapp/presentation/technician/CreateReportViewModel;", "Landroidx/lifecycle/ViewModel;", "templateRepository", "Lcom/labapp/data/repository/TemplateRepository;", "doctorRepository", "Lcom/labapp/data/repository/ReferralDoctorRepository;", "reportRepository", "Lcom/labapp/data/repository/ReportRepository;", "authRepository", "Lcom/labapp/data/repository/AuthRepository;", "computeFlagUseCase", "Lcom/labapp/domain/usecase/ComputeFlagUseCase;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/labapp/data/repository/TemplateRepository;Lcom/labapp/data/repository/ReferralDoctorRepository;Lcom/labapp/data/repository/ReportRepository;Lcom/labapp/data/repository/AuthRepository;Lcom/labapp/domain/usecase/ComputeFlagUseCase;Lcom/google/firebase/auth/FirebaseAuth;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/labapp/presentation/technician/CreateReportUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearError", "", "loadData", "nextStep", "onDoctorSelected", "doctor", "Lcom/labapp/data/model/ReferralDoctorDoc;", "onTestTypeSelected", "template", "Lcom/labapp/data/model/TemplateDoc;", "onValueChanged", "parameterId", "", "rawValue", "prevStep", "submitReport", "updatePatientAge", "age", "updatePatientMobile", "mobile", "updatePatientName", "name", "updatePatientSex", "sex", "validateStep", "step", "", "app_technicianDebug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CreateReportViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.data.repository.TemplateRepository templateRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.data.repository.ReferralDoctorRepository doctorRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.data.repository.ReportRepository reportRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.domain.usecase.ComputeFlagUseCase computeFlagUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.labapp.presentation.technician.CreateReportUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.technician.CreateReportUiState> uiState = null;
    
    @javax.inject.Inject()
    public CreateReportViewModel(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.TemplateRepository templateRepository, @org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.ReferralDoctorRepository doctorRepository, @org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.ReportRepository reportRepository, @org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.labapp.domain.usecase.ComputeFlagUseCase computeFlagUseCase, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.technician.CreateReportUiState> getUiState() {
        return null;
    }
    
    private final void loadData() {
    }
    
    public final void nextStep() {
    }
    
    public final void prevStep() {
    }
    
    public final void onTestTypeSelected(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.TemplateDoc template) {
    }
    
    public final void onDoctorSelected(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.ReferralDoctorDoc doctor) {
    }
    
    public final void updatePatientName(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void updatePatientAge(@org.jetbrains.annotations.NotNull()
    java.lang.String age) {
    }
    
    public final void updatePatientSex(@org.jetbrains.annotations.NotNull()
    java.lang.String sex) {
    }
    
    public final void updatePatientMobile(@org.jetbrains.annotations.NotNull()
    java.lang.String mobile) {
    }
    
    public final void onValueChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String parameterId, @org.jetbrains.annotations.NotNull()
    java.lang.String rawValue) {
    }
    
    private final java.lang.String validateStep(int step) {
        return null;
    }
    
    public final void submitReport() {
    }
    
    public final void clearError() {
    }
}