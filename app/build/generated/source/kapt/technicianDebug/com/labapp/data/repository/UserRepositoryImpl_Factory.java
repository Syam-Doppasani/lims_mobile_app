package com.labapp.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.functions.FirebaseFunctions;
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
public final class UserRepositoryImpl_Factory implements Factory<UserRepositoryImpl> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<FirebaseFunctions> functionsProvider;

  public UserRepositoryImpl_Factory(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseFunctions> functionsProvider) {
    this.firestoreProvider = firestoreProvider;
    this.functionsProvider = functionsProvider;
  }

  @Override
  public UserRepositoryImpl get() {
    return newInstance(firestoreProvider.get(), functionsProvider.get());
  }

  public static UserRepositoryImpl_Factory create(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseFunctions> functionsProvider) {
    return new UserRepositoryImpl_Factory(firestoreProvider, functionsProvider);
  }

  public static UserRepositoryImpl newInstance(FirebaseFirestore firestore,
      FirebaseFunctions functions) {
    return new UserRepositoryImpl(firestore, functions);
  }
}
