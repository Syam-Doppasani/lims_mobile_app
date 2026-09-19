package com.labapp.presentation.technician;

import com.google.firebase.auth.FirebaseAuth;
import com.labapp.data.repository.AuthRepository;
import com.labapp.data.repository.ReferralDoctorRepository;
import com.labapp.data.repository.ReportRepository;
import com.labapp.data.repository.TemplateRepository;
import com.labapp.domain.usecase.ComputeFlagUseCase;
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
public final class CreateReportViewModel_Factory implements Factory<CreateReportViewModel> {
  private final Provider<TemplateRepository> templateRepositoryProvider;

  private final Provider<ReferralDoctorRepository> doctorRepositoryProvider;

  private final Provider<ReportRepository> reportRepositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<ComputeFlagUseCase> computeFlagUseCaseProvider;

  private final Provider<FirebaseAuth> authProvider;

  public CreateReportViewModel_Factory(Provider<TemplateRepository> templateRepositoryProvider,
      Provider<ReferralDoctorRepository> doctorRepositoryProvider,
      Provider<ReportRepository> reportRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider,
      Provider<ComputeFlagUseCase> computeFlagUseCaseProvider,
      Provider<FirebaseAuth> authProvider) {
    this.templateRepositoryProvider = templateRepositoryProvider;
    this.doctorRepositoryProvider = doctorRepositoryProvider;
    this.reportRepositoryProvider = reportRepositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
    this.computeFlagUseCaseProvider = computeFlagUseCaseProvider;
    this.authProvider = authProvider;
  }

  @Override
  public CreateReportViewModel get() {
    return newInstance(templateRepositoryProvider.get(), doctorRepositoryProvider.get(), reportRepositoryProvider.get(), authRepositoryProvider.get(), computeFlagUseCaseProvider.get(), authProvider.get());
  }

  public static CreateReportViewModel_Factory create(
      Provider<TemplateRepository> templateRepositoryProvider,
      Provider<ReferralDoctorRepository> doctorRepositoryProvider,
      Provider<ReportRepository> reportRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider,
      Provider<ComputeFlagUseCase> computeFlagUseCaseProvider,
      Provider<FirebaseAuth> authProvider) {
    return new CreateReportViewModel_Factory(templateRepositoryProvider, doctorRepositoryProvider, reportRepositoryProvider, authRepositoryProvider, computeFlagUseCaseProvider, authProvider);
  }

  public static CreateReportViewModel newInstance(TemplateRepository templateRepository,
      ReferralDoctorRepository doctorRepository, ReportRepository reportRepository,
      AuthRepository authRepository, ComputeFlagUseCase computeFlagUseCase, FirebaseAuth auth) {
    return new CreateReportViewModel(templateRepository, doctorRepository, reportRepository, authRepository, computeFlagUseCase, auth);
  }
}
