package com.labapp.data.repository;

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
public final class ReferralDoctorRepositoryImpl_Factory implements Factory<ReferralDoctorRepositoryImpl> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public ReferralDoctorRepositoryImpl_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public ReferralDoctorRepositoryImpl get() {
    return newInstance(firestoreProvider.get());
  }

  public static ReferralDoctorRepositoryImpl_Factory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new ReferralDoctorRepositoryImpl_Factory(firestoreProvider);
  }

  public static ReferralDoctorRepositoryImpl newInstance(FirebaseFirestore firestore) {
    return new ReferralDoctorRepositoryImpl(firestore);
  }
}
