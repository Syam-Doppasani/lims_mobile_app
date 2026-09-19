package com.labapp.presentation.admin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013J\u0006\u0010\u0014\u001a\u00020\u000fJ\b\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/labapp/presentation/admin/UserManagementViewModel;", "Landroidx/lifecycle/ViewModel;", "userRepository", "Lcom/labapp/data/repository/UserRepository;", "templateRepository", "Lcom/labapp/data/repository/TemplateRepository;", "(Lcom/labapp/data/repository/UserRepository;Lcom/labapp/data/repository/TemplateRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/labapp/presentation/admin/UserManagementUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "approveUser", "", "userId", "", "assignedTemplateIds", "", "clearError", "loadActiveUsers", "loadPendingUsers", "loadTemplates", "app_adminRelease"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class UserManagementViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.data.repository.TemplateRepository templateRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.labapp.presentation.admin.UserManagementUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.admin.UserManagementUiState> uiState = null;
    
    @javax.inject.Inject()
    public UserManagementViewModel(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.TemplateRepository templateRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.admin.UserManagementUiState> getUiState() {
        return null;
    }
    
    private final void loadPendingUsers() {
    }
    
    private final void loadActiveUsers() {
    }
    
    private final void loadTemplates() {
    }
    
    public final void approveUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> assignedTemplateIds) {
    }
    
    public final void clearError() {
    }
}