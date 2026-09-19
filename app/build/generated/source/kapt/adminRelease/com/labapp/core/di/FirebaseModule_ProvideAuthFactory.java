package com.labapp.core.di;

import com.google.firebase.auth.FirebaseAuth;
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
public final class FirebaseModule_ProvideAuthFactory implements Factory<FirebaseAuth> {
  @Override
  public FirebaseAuth get() {
    return provideAuth();
  }

  public static FirebaseModule_ProvideAuthFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FirebaseAuth provideAuth() {
    return Preconditions.checkNotNullFromProvides(FirebaseModule.INSTANCE.provideAuth());
  }

  private static final class InstanceHolder {
    private static final FirebaseModule_ProvideAuthFactory INSTANCE = new FirebaseModule_ProvideAuthFactory();
  }
}
