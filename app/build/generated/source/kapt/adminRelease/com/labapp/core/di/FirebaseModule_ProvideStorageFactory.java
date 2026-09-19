package com.labapp.core.di;

import com.google.firebase.storage.FirebaseStorage;
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
public final class FirebaseModule_ProvideStorageFactory implements Factory<FirebaseStorage> {
  @Override
  public FirebaseStorage get() {
    return provideStorage();
  }

  public static FirebaseModule_ProvideStorageFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FirebaseStorage provideStorage() {
    return Preconditions.checkNotNullFromProvides(FirebaseModule.INSTANCE.provideStorage());
  }

  private static final class InstanceHolder {
    private static final FirebaseModule_ProvideStorageFactory INSTANCE = new FirebaseModule_ProvideStorageFactory();
  }
}
