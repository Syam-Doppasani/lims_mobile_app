package com.labapp.presentation.technician;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J:\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J=\u0010\u0012\u001a\u00020\b2\u0010\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0015\u0018\u00010\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0018H\u0016\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/labapp/presentation/technician/CachedPdfPrintAdapter;", "Landroid/print/PrintDocumentAdapter;", "pdfFile", "Ljava/io/File;", "documentName", "", "(Ljava/io/File;Ljava/lang/String;)V", "onLayout", "", "oldAttributes", "Landroid/print/PrintAttributes;", "newAttributes", "cancellationSignal", "Landroid/os/CancellationSignal;", "callback", "Landroid/print/PrintDocumentAdapter$LayoutResultCallback;", "extras", "Landroid/os/Bundle;", "onWrite", "pages", "", "Landroid/print/PageRange;", "destination", "Landroid/os/ParcelFileDescriptor;", "Landroid/print/PrintDocumentAdapter$WriteResultCallback;", "([Landroid/print/PageRange;Landroid/os/ParcelFileDescriptor;Landroid/os/CancellationSignal;Landroid/print/PrintDocumentAdapter$WriteResultCallback;)V", "app_technicianRelease"})
public final class CachedPdfPrintAdapter extends android.print.PrintDocumentAdapter {
    @org.jetbrains.annotations.NotNull()
    private final java.io.File pdfFile = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String documentName = null;
    
    public CachedPdfPrintAdapter(@org.jetbrains.annotations.NotNull()
    java.io.File pdfFile, @org.jetbrains.annotations.NotNull()
    java.lang.String documentName) {
        super();
    }
    
    @java.lang.Override()
    public void onLayout(@org.jetbrains.annotations.Nullable()
    android.print.PrintAttributes oldAttributes, @org.jetbrains.annotations.Nullable()
    android.print.PrintAttributes newAttributes, @org.jetbrains.annotations.Nullable()
    android.os.CancellationSignal cancellationSignal, @org.jetbrains.annotations.Nullable()
    android.print.PrintDocumentAdapter.LayoutResultCallback callback, @org.jetbrains.annotations.Nullable()
    android.os.Bundle extras) {
    }
    
    @java.lang.Override()
    public void onWrite(@org.jetbrains.annotations.Nullable()
    android.print.PageRange[] pages, @org.jetbrains.annotations.Nullable()
    android.os.ParcelFileDescriptor destination, @org.jetbrains.annotations.Nullable()
    android.os.CancellationSignal cancellationSignal, @org.jetbrains.annotations.Nullable()
    android.print.PrintDocumentAdapter.WriteResultCallback callback) {
    }
}