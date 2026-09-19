package com.labapp.presentation.technician;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a<\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u001a*\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0013H\u0007\u001a`\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u000f2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u000f2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u000f2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u001a$\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u001a\u0010\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0007\u001a$\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00010\u000fH\u0007\u00a8\u0006\u001f"}, d2 = {"CreateReportScreen", "", "viewModel", "Lcom/labapp/presentation/technician/CreateReportViewModel;", "onBackClick", "Lkotlin/Function0;", "ParameterValueField", "parameter", "Lcom/labapp/data/model/Parameter;", "value", "", "flag", "Lcom/labapp/data/model/ValueFlag;", "patientSex", "onValueChange", "Lkotlin/Function1;", "StepEnterValues", "uiState", "Lcom/labapp/presentation/technician/CreateReportUiState;", "Lkotlin/Function2;", "StepPatientDetails", "onNameChange", "onAgeChange", "onSexChange", "onMobileChange", "StepReferralDoctor", "onSelect", "Lcom/labapp/data/model/ReferralDoctorDoc;", "StepReview", "StepTestType", "Lcom/labapp/data/model/TemplateDoc;", "app_technicianRelease"})
public final class CreateReportScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void CreateReportScreen(@org.jetbrains.annotations.NotNull()
    com.labapp.presentation.technician.CreateReportViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBackClick) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void StepTestType(@org.jetbrains.annotations.NotNull()
    com.labapp.presentation.technician.CreateReportUiState uiState, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.labapp.data.model.TemplateDoc, kotlin.Unit> onSelect) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void StepReferralDoctor(@org.jetbrains.annotations.NotNull()
    com.labapp.presentation.technician.CreateReportUiState uiState, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.labapp.data.model.ReferralDoctorDoc, kotlin.Unit> onSelect) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StepPatientDetails(@org.jetbrains.annotations.NotNull()
    com.labapp.presentation.technician.CreateReportUiState uiState, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNameChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onAgeChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSexChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onMobileChange) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StepEnterValues(@org.jetbrains.annotations.NotNull()
    com.labapp.presentation.technician.CreateReportUiState uiState, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> onValueChange) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ParameterValueField(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.Parameter parameter, @org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    com.labapp.data.model.ValueFlag flag, @org.jetbrains.annotations.NotNull()
    java.lang.String patientSex, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onValueChange) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StepReview(@org.jetbrains.annotations.NotNull()
    com.labapp.presentation.technician.CreateReportUiState uiState) {
    }
}