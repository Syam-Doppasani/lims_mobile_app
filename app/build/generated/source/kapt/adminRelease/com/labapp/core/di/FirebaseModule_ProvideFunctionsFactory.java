package com.labapp.core.di;

import com.google.firebase.functions.FirebaseFunctions;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class FirebaseModule_ProvideFunctionsFactory implements Factory<FirebaseFunctions> {
  @Override
  public FirebaseFunctions get() {
    return provideFunctions();
  }

  public static FirebaseModule_ProvideFunctionsFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FirebaseFunctions provideFunctions() {
    return Preconditions.checkNotNullFromProvides(FirebaseModule.INSTANCE.provideFunctions());
  }

  private static final class InstanceHolder {
    private static final FirebaseModule_ProvideFunctionsFactory INSTANCE = new FirebaseModule_ProvideFunctionsFactory();
  }
}
