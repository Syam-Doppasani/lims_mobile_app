package com.labapp.presentation.technician;

import com.google.firebase.auth.FirebaseAuth;
import com.labapp.data.repository.ReportRepository;
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
public final class ReportListViewModel_Factory implements Factory<ReportListViewModel> {
  private final Provider<ReportRepository> reportRepositoryProvider;

  private final Provider<FirebaseAuth> authProvider;

  public ReportListViewModel_Factory(Provider<ReportRepository> reportRepositoryProvider,
      Provider<FirebaseAuth> authProvider) {
    this.reportRepositoryProvider = reportRepositoryProvider;
    this.authProvider = authProvider;
  }

  @Override
  public ReportListViewModel get() {
    return newInstance(reportRepositoryProvider.get(), authProvider.get());
  }

  public static ReportListViewModel_Factory create(
      Provider<ReportRepository> reportRepositoryProvider, Provider<FirebaseAuth> authProvider) {
    return new ReportListViewModel_Factory(reportRepositoryProvider, authProvider);
  }

  public static ReportListViewModel newInstance(ReportRepository reportRepository,
      FirebaseAuth auth) {
    return new ReportListViewModel(reportRepository, auth);
  }
}
