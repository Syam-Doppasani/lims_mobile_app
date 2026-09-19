package com.labapp.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086B\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/labapp/domain/usecase/EnsureSelfDoctorUseCase;", "", "doctorRepository", "Lcom/labapp/data/repository/ReferralDoctorRepository;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/labapp/data/repository/ReferralDoctorRepository;Lcom/google/firebase/auth/FirebaseAuth;)V", "invoke", "Lcom/labapp/core/util/Result;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_adminDebug"})
public final class EnsureSelfDoctorUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.labapp.data.repository.ReferralDoctorRepository doctorRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    
    @javax.inject.Inject()
    public EnsureSelfDoctorUseCase(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.ReferralDoctorRepository doctorRepository, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<kotlin.Unit>> $completion) {
        return null;
    }
}