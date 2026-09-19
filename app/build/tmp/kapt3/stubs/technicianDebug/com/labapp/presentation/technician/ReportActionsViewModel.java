package com.labapp.presentation.technician;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0082@\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011J\u001e\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0082@\u00a2\u0006\u0002\u0010\u001eJ\u000e\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020!2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/labapp/presentation/technician/ReportActionsViewModel;", "Landroidx/lifecycle/ViewModel;", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_actionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/labapp/presentation/technician/ReportActionState;", "actionState", "Lkotlinx/coroutines/flow/StateFlow;", "getActionState", "()Lkotlinx/coroutines/flow/StateFlow;", "httpClient", "Lokhttp3/OkHttpClient;", "buildFileName", "", "report", "Lcom/labapp/data/model/ReportDoc;", "consumeState", "", "downloadToStorage", "fetchPdfToCache", "Ljava/io/File;", "(Lcom/labapp/data/model/ReportDoc;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchSystemPrint", "file", "launchWhatsAppShare", "printReport", "saveToDownloads", "source", "(Ljava/io/File;Lcom/labapp/data/model/ReportDoc;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shareViaWhatsApp", "tryStartActivity", "", "intent", "Landroid/content/Intent;", "validatePdfReady", "Companion", "app_technicianDebug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ReportActionsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context appContext = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.labapp.presentation.technician.ReportActionState> _actionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.technician.ReportActionState> actionState = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient httpClient = null;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String MIME_PDF = "application/pdf";
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String CACHE_DIR = "reports";
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> WHATSAPP_PACKAGES = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.labapp.presentation.technician.ReportActionsViewModel.Companion Companion = null;
    
    @javax.inject.Inject()
    public ReportActionsViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.labapp.presentation.technician.ReportActionState> getActionState() {
        return null;
    }
    
    public final void consumeState() {
    }
    
    public final void downloadToStorage(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.ReportDoc report) {
    }
    
    public final void printReport(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.ReportDoc report) {
    }
    
    public final void shareViaWhatsApp(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.ReportDoc report) {
    }
    
    private final boolean validatePdfReady(com.labapp.data.model.ReportDoc report) {
        return false;
    }
    
    private final java.lang.Object fetchPdfToCache(com.labapp.data.model.ReportDoc report, kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    private final java.lang.Object saveToDownloads(java.io.File source, com.labapp.data.model.ReportDoc report, kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final void launchSystemPrint(java.io.File file, com.labapp.data.model.ReportDoc report) {
    }
    
    private final void launchWhatsAppShare(java.io.File file, com.labapp.data.model.ReportDoc report) {
    }
    
    private final boolean tryStartActivity(android.content.Intent intent) {
        return false;
    }
    
    private final java.lang.String buildFileName(com.labapp.data.model.ReportDoc report) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/labapp/presentation/technician/ReportActionsViewModel$Companion;", "", "()V", "CACHE_DIR", "", "MIME_PDF", "WHATSAPP_PACKAGES", "", "getWHATSAPP_PACKAGES", "()Ljava/util/List;", "app_technicianDebug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getWHATSAPP_PACKAGES() {
            return null;
        }
    }
}