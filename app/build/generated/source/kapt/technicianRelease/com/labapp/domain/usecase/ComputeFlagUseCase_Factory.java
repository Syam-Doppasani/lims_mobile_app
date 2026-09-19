package com.labapp.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class ComputeFlagUseCase_Factory implements Factory<ComputeFlagUseCase> {
  @Override
  public ComputeFlagUseCase get() {
    return newInstance();
  }

  public static ComputeFlagUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ComputeFlagUseCase newInstance() {
    return new ComputeFlagUseCase();
  }

  private static final class InstanceHolder {
    private static final ComputeFlagUseCase_Factory INSTANCE = new ComputeFlagUseCase_Factory();
  }
}
