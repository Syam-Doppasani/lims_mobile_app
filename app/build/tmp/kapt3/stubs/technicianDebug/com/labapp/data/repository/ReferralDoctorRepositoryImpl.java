package com.labapp.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\"\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00110\u00060\u00102\u0006\u0010\u0012\u001a\u00020\rH\u0016J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/labapp/data/repository/ReferralDoctorRepositoryImpl;", "Lcom/labapp/data/repository/ReferralDoctorRepository;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "createDoctor", "Lcom/labapp/core/util/Result;", "", "doctor", "Lcom/labapp/data/model/ReferralDoctorDoc;", "(Lcom/labapp/data/model/ReferralDoctorDoc;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDoctor", "doctorId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDoctorsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "technicianId", "getSelfDoctor", "app_technicianDebug"})
public final class ReferralDoctorRepositoryImpl implements com.labapp.data.repository.ReferralDoctorRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    
    @javax.inject.Inject()
    public ReferralDoctorRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.labapp.core.util.Result<java.util.List<com.labapp.data.model.ReferralDoctorDoc>>> getDoctorsFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String technicianId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getSelfDoctor(@org.jetbrains.annotations.NotNull()
    java.lang.String technicianId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.data.model.ReferralDoctorDoc> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createDoctor(@org.jetbrains.annotations.NotNull()
    com.labapp.data.model.ReferralDoctorDoc doctor, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteDoctor(@org.jetbrains.annotations.NotNull()
    java.lang.String doctorId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<kotlin.Unit>> $completion) {
        return null;
    }
}