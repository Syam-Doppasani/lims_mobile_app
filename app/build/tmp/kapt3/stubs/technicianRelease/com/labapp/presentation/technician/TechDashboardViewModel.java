package com.labapp.presentation.technician;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u0011R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0013"}, d2 = {"Lcom/labapp/presentation/technician/TechDashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "getDashboardDataUseCase", "Lcom/labapp/domain/usecase/GetDashboardDataUseCase;", "ensureSelfDoctorUseCase", "Lcom/labapp/domain/usecase/EnsureSelfDoctorUseCase;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/labapp/domain/usecase/GetDashboardDataUseCase;Lcom/labapp/domain/usecase/EnsureSelfDoctorUseCase;Lcom/google/firebase/auth/FirebaseAuth;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/labapp/presentation/technician/TechDashboardUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "ensureSelfDoctorCreated", "", "loadDashboardData", "app_technicianRelease"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TechDashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.domain.usecase.GetDashboardDataUseCase getDashboardDataUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.domain.usecase.EnsureSelfDoctorUseCase ensureSelfDoctorUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.labapp.presentation.technician.TechDashboardUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.technician.TechDashboardUiState> uiState = null;
    
    @javax.inject.Inject()
    public TechDashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.labapp.domain.usecase.GetDashboardDataUseCase getDashboardDataUseCase, @org.jetbrains.annotations.NotNull()
    com.labapp.domain.usecase.EnsureSelfDoctorUseCase ensureSelfDoctorUseCase, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.technician.TechDashboardUiState> getUiState() {
        return null;
    }
    
    private final void ensureSelfDoctorCreated() {
    }
    
    public final void loadDashboardData() {
    }
}