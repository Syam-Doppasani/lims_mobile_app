package com.labapp.domain.usecase;

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
public final class GetDashboardDataUseCase_Factory implements Factory<GetDashboardDataUseCase> {
  private final Provider<ReportRepository> reportRepositoryProvider;

  public GetDashboardDataUseCase_Factory(Provider<ReportRepository> reportRepositoryProvider) {
    this.reportRepositoryProvider = reportRepositoryProvider;
  }

  @Override
  public GetDashboardDataUseCase get() {
    return newInstance(reportRepositoryProvider.get());
  }

  public static GetDashboardDataUseCase_Factory create(
      Provider<ReportRepository> reportRepositoryProvider) {
    return new GetDashboardDataUseCase_Factory(reportRepositoryProvider);
  }

  public static GetDashboardDataUseCase newInstance(ReportRepository reportRepository) {
    return new GetDashboardDataUseCase(reportRepository);
  }
}
