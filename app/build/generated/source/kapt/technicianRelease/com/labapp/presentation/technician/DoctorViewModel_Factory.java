package com.labapp.presentation.technician;

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
public final class DoctorViewModel_Factory implements Factory<DoctorViewModel> {
  private final Provider<ReferralDoctorRepository> doctorRepositoryProvider;

  private final Provider<FirebaseAuth> authProvider;

  public DoctorViewModel_Factory(Provider<ReferralDoctorRepository> doctorRepositoryProvider,
      Provider<FirebaseAuth> authProvider) {
    this.doctorRepositoryProvider = doctorRepositoryProvider;
    this.authProvider = authProvider;
  }

  @Override
  public DoctorViewModel get() {
    return newInstance(doctorRepositoryProvider.get(), authProvider.get());
  }

  public static DoctorViewModel_Factory create(
      Provider<ReferralDoctorRepository> doctorRepositoryProvider,
      Provider<FirebaseAuth> authProvider) {
    return new DoctorViewModel_Factory(doctorRepositoryProvider, authProvider);
  }

  public static DoctorViewModel newInstance(ReferralDoctorRepository doctorRepository,
      FirebaseAuth auth) {
    return new DoctorViewModel(doctorRepository, auth);
  }
}
