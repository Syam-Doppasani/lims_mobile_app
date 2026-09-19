package com.labapp.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u0006H\u0086B\u00a2\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/labapp/domain/usecase/GetDashboardDataUseCase;", "", "reportRepository", "Lcom/labapp/data/repository/ReportRepository;", "(Lcom/labapp/data/repository/ReportRepository;)V", "formatDate", "", "timestamp", "Lcom/google/firebase/Timestamp;", "invoke", "Lcom/labapp/core/util/Result;", "Lcom/labapp/domain/model/DashboardData;", "technicianId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isThisWeek", "", "isToday", "app_technicianRelease"})
public final class GetDashboardDataUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.data.repository.ReportRepository reportRepository = null;
    
    @javax.inject.Inject()
    public GetDashboardDataUseCase(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.ReportRepository reportRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String technicianId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<com.labapp.domain.model.DashboardData>> $completion) {
        return null;
    }
    
    private final boolean isToday(com.google.firebase.Timestamp timestamp) {
        return false;
    }
    
    private final boolean isThisWeek(com.google.firebase.Timestamp timestamp) {
        return false;
    }
    
    private final java.lang.String formatDate(com.google.firebase.Timestamp timestamp) {
        return null;
    }
}