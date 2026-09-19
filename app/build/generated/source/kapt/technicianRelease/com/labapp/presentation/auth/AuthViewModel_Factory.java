package com.labapp.presentation.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.labapp.data.repository.AuthRepository;
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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public AuthViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(authRepositoryProvider.get(), firebaseAuthProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<FirebaseAuth> firebaseAuthProvider) {
    return new AuthViewModel_Factory(authRepositoryProvider, firebaseAuthProvider);
  }

  public static AuthViewModel newInstance(AuthRepository authRepository,
      FirebaseAuth firebaseAuth) {
    return new AuthViewModel(authRepository, firebaseAuth);
  }
}
