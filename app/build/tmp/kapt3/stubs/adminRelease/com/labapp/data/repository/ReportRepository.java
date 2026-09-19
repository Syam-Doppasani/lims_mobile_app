package com.labapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010\rJ\"\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\u00030\u000f2\u0006\u0010\n\u001a\u00020\u0004H&J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\u0006\u0010\u0012\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/labapp/data/repository/ReportRepository;", "", "createReport", "Lcom/labapp/core/util/Result;", "", "report", "Lcom/labapp/data/model/ReportDoc;", "(Lcom/labapp/data/model/ReportDoc;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReports", "", "technicianId", "fromTimestamp", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReportsFlow", "Lkotlinx/coroutines/flow/Flow;", "resetReportStatus", "", "reportId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_adminRelease"})
public abstract interface ReportRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createReport(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.ReportDoc report, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<java.lang.String>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.labapp.core.util.Result<java.util.List<com.labapp.data.model.ReportDoc>>> getReportsFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String technicianId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReports(@org.jetbrains.annotations.NotNull()
    java.lang.String technicianId, long fromTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.labapp.data.model.ReportDoc>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resetReportStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String reportId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<kotlin.Unit>> $completion);
}