package com.labapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\t\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u000bJ\"\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000e0\u00030\r2\u0006\u0010\u000f\u001a\u00020\nH&J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\u0011"}, d2 = {"Lcom/labapp/data/repository/ReferralDoctorRepository;", "", "createDoctor", "Lcom/labapp/core/util/Result;", "", "doctor", "Lcom/labapp/data/model/ReferralDoctorDoc;", "(Lcom/labapp/data/model/ReferralDoctorDoc;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDoctor", "doctorId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDoctorsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "technicianId", "getSelfDoctor", "app_technicianDebug"})
public abstract interface ReferralDoctorRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.labapp.core.util.Result<java.util.List<com.labapp.data.model.ReferralDoctorDoc>>> getDoctorsFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String technicianId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSelfDoctor(@org.jetbrains.annotations.NotNull()
    java.lang.String technicianId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.data.model.ReferralDoctorDoc> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createDoctor(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.ReferralDoctorDoc doctor, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteDoctor(@org.jetbrains.annotations.NotNull()
    java.lang.String doctorId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<kotlin.Unit>> $completion);
}