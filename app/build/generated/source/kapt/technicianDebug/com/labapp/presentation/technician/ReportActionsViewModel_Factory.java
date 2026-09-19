package com.labapp.presentation.technician;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ReportActionsViewModel_Factory implements Factory<ReportActionsViewModel> {
  private final Provider<Context> appContextProvider;

  public ReportActionsViewModel_Factory(Provider<Context> appContextProvider) {
    this.appContextProvider = appContextProvider;
  }

  @Override
  public ReportActionsViewModel get() {
    return newInstance(appContextProvider.get());
  }

  public static ReportActionsViewModel_Factory create(Provider<Context> appContextProvider) {
    return new ReportActionsViewModel_Factory(appContextProvider);
  }

  public static ReportActionsViewModel newInstance(Context appContext) {
    return new ReportActionsViewModel(appContext);
  }
}
