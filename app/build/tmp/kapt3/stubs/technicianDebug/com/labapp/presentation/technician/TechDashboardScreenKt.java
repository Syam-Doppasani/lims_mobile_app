package com.labapp.presentation.technician;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010&\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\u001c\u0010\n\u001a\u00020\u00012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fH\u0007\u001a\u001e\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0007\u001a\"\u0010\u0013\u001a\u00020\u00012\u0018\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\u00160\u0015H\u0007\u00a8\u0006\u0017"}, d2 = {"MetricCard", "", "title", "", "count", "modifier", "Landroidx/compose/ui/Modifier;", "MetricsSection", "data", "Lcom/labapp/domain/model/DashboardData;", "ReportsLineChart", "reportsPerDay", "", "", "TechDashboardScreen", "viewModel", "Lcom/labapp/presentation/technician/TechDashboardViewModel;", "onNavigateToCreateReport", "Lkotlin/Function0;", "TopDoctorsSection", "topDoctors", "", "", "app_technicianDebug"})
public final class TechDashboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void TechDashboardScreen(@org.jetbrains.annotations.NotNull()
    com.labapp.presentation.technician.TechDashboardViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToCreateReport) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MetricsSection(@org.jetbrains.annotations.NotNull()
    com.labapp.domain.model.DashboardData data) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void MetricCard(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String count, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ReportsLineChart(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Integer> reportsPerDay) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TopDoctorsSection(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.Map.Entry<java.lang.String, java.lang.Integer>> topDoctors) {
    }
}