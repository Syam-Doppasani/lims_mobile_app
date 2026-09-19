package com.labapp.presentation.admin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007\u001a\u001e\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u001a\u001e\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u00a8\u0006\u000e"}, d2 = {"ActiveUserItem", "", "user", "Lcom/labapp/data/model/UserDoc;", "templates", "", "Lcom/labapp/data/model/TemplateDoc;", "PendingUserItem", "onApproveClick", "Lkotlin/Function0;", "UserManagementScreen", "viewModel", "Lcom/labapp/presentation/admin/UserManagementViewModel;", "onBackClick", "app_adminRelease"})
public final class UserManagementScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void UserManagementScreen(@org.jetbrains.annotations.NotNull()
    com.labapp.presentation.admin.UserManagementViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBackClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PendingUserItem(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.UserDoc user, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onApproveClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ActiveUserItem(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.UserDoc user, @org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.TemplateDoc> templates) {
    }
}