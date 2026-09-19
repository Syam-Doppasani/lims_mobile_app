package com.labapp.presentation.admin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\tH\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\fH\u00c6\u0003JY\u0010\u001c\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\fH\u00d6\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000f\u00a8\u0006\""}, d2 = {"Lcom/labapp/presentation/admin/UserManagementUiState;", "", "pendingUsers", "", "Lcom/labapp/data/model/UserDoc;", "activeUsers", "templates", "Lcom/labapp/data/model/TemplateDoc;", "isProcessing", "", "approvalSuccess", "error", "", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;ZZLjava/lang/String;)V", "getActiveUsers", "()Ljava/util/List;", "getApprovalSuccess", "()Z", "getError", "()Ljava/lang/String;", "getPendingUsers", "getTemplates", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_adminDebug"})
public final class UserManagementUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.labapp.data.model.UserDoc> pendingUsers = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.labapp.data.model.UserDoc> activeUsers = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.labapp.data.model.TemplateDoc> templates = null;
    private final boolean isProcessing = false;
    private final boolean approvalSuccess = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public UserManagementUiState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.UserDoc> pendingUsers, @org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.UserDoc> activeUsers, @org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.TemplateDoc> templates, boolean isProcessing, boolean approvalSuccess, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.labapp.data.model.UserDoc> getPendingUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.labapp.data.model.UserDoc> getActiveUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.labapp.data.model.TemplateDoc> getTemplates() {
        return null;
    }
    
    public final boolean isProcessing() {
        return false;
    }
    
    public final boolean getApprovalSuccess() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public UserManagementUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.labapp.data.model.UserDoc> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.labapp.data.model.UserDoc> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.labapp.data.model.TemplateDoc> component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.labapp.presentation.admin.UserManagementUiState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.UserDoc> pendingUsers, @org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.UserDoc> activeUsers, @org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.TemplateDoc> templates, boolean isProcessing, boolean approvalSuccess, @org.jetbrains.annotations.Nullable()
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