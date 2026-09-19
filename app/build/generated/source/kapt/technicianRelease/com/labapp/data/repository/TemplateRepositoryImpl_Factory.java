package com.labapp.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class TemplateRepositoryImpl_Factory implements Factory<TemplateRepositoryImpl> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<FirebaseAuth> authProvider;

  public TemplateRepositoryImpl_Factory(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseAuth> authProvider) {
    this.firestoreProvider = firestoreProvider;
    this.authProvider = authProvider;
  }

  @Override
  public TemplateRepositoryImpl get() {
    return newInstance(firestoreProvider.get(), authProvider.get());
  }

  public static TemplateRepositoryImpl_Factory create(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseAuth> authProvider) {
    return new TemplateRepositoryImpl_Factory(firestoreProvider, authProvider);
  }

  public static TemplateRepositoryImpl newInstance(FirebaseFirestore firestore, FirebaseAuth auth) {
    return new TemplateRepositoryImpl(firestore, auth);
  }
}
