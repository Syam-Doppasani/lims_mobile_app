package com.labapp.presentation.technician;

import com.google.firebase.auth.FirebaseAuth;
import com.labapp.domain.usecase.EnsureSelfDoctorUseCase;
import com.labapp.domain.usecase.GetDashboardDataUseCase;
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
public final class TechDashboardViewModel_Factory implements Factory<TechDashboardViewModel> {
  private final Provider<GetDashboardDataUseCase> getDashboardDataUseCaseProvider;

  private final Provider<EnsureSelfDoctorUseCase> ensureSelfDoctorUseCaseProvider;

  private final Provider<FirebaseAuth> authProvider;

  public TechDashboardViewModel_Factory(
      Provider<GetDashboardDataUseCase> getDashboardDataUseCaseProvider,
      Provider<EnsureSelfDoctorUseCase> ensureSelfDoctorUseCaseProvider,
      Provider<FirebaseAuth> authProvider) {
    this.getDashboardDataUseCaseProvider = getDashboardDataUseCaseProvider;
    this.ensureSelfDoctorUseCaseProvider = ensureSelfDoctorUseCaseProvider;
    this.authProvider = authProvider;
  }

  @Override
  public TechDashboardViewModel get() {
    return newInstance(getDashboardDataUseCaseProvider.get(), ensureSelfDoctorUseCaseProvider.get(), authProvider.get());
  }

  public static TechDashboardViewModel_Factory create(
      Provider<GetDashboardDataUseCase> getDashboardDataUseCaseProvider,
      Provider<EnsureSelfDoctorUseCase> ensureSelfDoctorUseCaseProvider,
      Provider<FirebaseAuth> authProvider) {
    return new TechDashboardViewModel_Factory(getDashboardDataUseCaseProvider, ensureSelfDoctorUseCaseProvider, authProvider);
  }

  public static TechDashboardViewModel newInstance(GetDashboardDataUseCase getDashboardDataUseCase,
      EnsureSelfDoctorUseCase ensureSelfDoctorUseCase, FirebaseAuth auth) {
    return new TechDashboardViewModel(getDashboardDataUseCase, ensureSelfDoctorUseCase, auth);
  }
}
