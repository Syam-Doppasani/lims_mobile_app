package com.labapp.presentation.technician;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011J\u0006\u0010\u0014\u001a\u00020\u000fJ\u000e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u000fJ\u0006\u0010\u0019\u001a\u00020\u000fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001a"}, d2 = {"Lcom/labapp/presentation/technician/DoctorViewModel;", "Landroidx/lifecycle/ViewModel;", "doctorRepository", "Lcom/labapp/data/repository/ReferralDoctorRepository;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/labapp/data/repository/ReferralDoctorRepository;Lcom/google/firebase/auth/FirebaseAuth;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/labapp/presentation/technician/DoctorUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addDoctor", "", "name", "", "mobile", "specialisation", "clearError", "deleteDoctor", "doctor", "Lcom/labapp/data/model/ReferralDoctorDoc;", "loadDoctors", "resetSaveSuccess", "app_technicianRelease"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DoctorViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.data.repository.ReferralDoctorRepository doctorRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.labapp.presentation.technician.DoctorUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.technician.DoctorUiState> uiState = null;
    
    @javax.inject.Inject()
    public DoctorViewModel(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.ReferralDoctorRepository doctorRepository, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.technician.DoctorUiState> getUiState() {
        return null;
    }
    
    public final void loadDoctors() {
    }
    
    public final void addDoctor(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String mobile, @org.jetbrains.annotations.NotNull()
    java.lang.String specialisation) {
    }
    
    public final void deleteDoctor(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.ReferralDoctorDoc doctor) {
    }
    
    public final void clearError() {
    }
    
    public final void resetSaveSuccess() {
    }
}