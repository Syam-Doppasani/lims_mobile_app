package com.labapp.domain.usecase;

import com.google.firebase.auth.FirebaseAuth;
import com.labapp.data.repository.ReferralDoctorRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class EnsureSelfDoctorUseCase_Factory implements Factory<EnsureSelfDoctorUseCase> {
  private final Provider<ReferralDoctorRepository> doctorRepositoryProvider;

  private final Provider<FirebaseAuth> authProvider;

  public EnsureSelfDoctorUseCase_Factory(
      Provider<ReferralDoctorRepository> doctorRepositoryProvider,
      Provider<FirebaseAuth> authProvider) {
    this.doctorRepositoryProvider = doctorRepositoryProvider;
    this.authProvider = authProvider;
  }

  @Override
  public EnsureSelfDoctorUseCase get() {
    return newInstance(doctorRepositoryProvider.get(), authProvider.get());
  }

  public static EnsureSelfDoctorUseCase_Factory create(
      Provider<ReferralDoctorRepository> doctorRepositoryProvider,
      Provider<FirebaseAuth> authProvider) {
    return new EnsureSelfDoctorUseCase_Factory(doctorRepositoryProvider, authProvider);
  }

  public static EnsureSelfDoctorUseCase newInstance(ReferralDoctorRepository doctorRepository,
      FirebaseAuth auth) {
    return new EnsureSelfDoctorUseCase(doctorRepository, auth);
  }
}
