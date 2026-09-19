package com.labapp.presentation.admin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0018J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001f\u001a\u00020\u0007H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006 "}, d2 = {"Lcom/labapp/presentation/admin/TemplateEditorViewModel;", "Landroidx/lifecycle/ViewModel;", "templateRepository", "Lcom/labapp/data/repository/TemplateRepository;", "(Lcom/labapp/data/repository/TemplateRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/labapp/presentation/admin/TemplateEditorUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addParameter", "", "clearError", "moveParameterUp", "index", "", "removeParameter", "saveTemplate", "publish", "", "updateName", "name", "", "updateParameter", "updated", "Lcom/labapp/presentation/admin/ParameterInput;", "updateTestType", "type", "validateTemplate", "state", "app_adminDebug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TemplateEditorViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.data.repository.TemplateRepository templateRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.labapp.presentation.admin.TemplateEditorUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.admin.TemplateEditorUiState> uiState = null;
    
    @javax.inject.Inject()
    public TemplateEditorViewModel(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.TemplateRepository templateRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.admin.TemplateEditorUiState> getUiState() {
        return null;
    }
    
    public final void updateName(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    public final void updateTestType(@org.jetbrains.annotations.NotNull()
    java.lang.String type) {
    }
    
    public final void addParameter() {
    }
    
    public final void updateParameter(int index, @org.jetbrains.annotations.NotNull()
    com.labapp.presentation.admin.ParameterInput updated) {
    }
    
    public final void removeParameter(int index) {
    }
    
    public final void moveParameterUp(int index) {
    }
    
    public final void saveTemplate(boolean publish) {
    }
    
    private final java.lang.String validateTemplate(com.labapp.presentation.admin.TemplateEditorUiState state) {
        return null;
    }
    
    public final void clearError() {
    }
}