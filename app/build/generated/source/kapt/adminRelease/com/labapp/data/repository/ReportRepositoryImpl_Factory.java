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
public final class ReportRepositoryImpl_Factory implements Factory<ReportRepositoryImpl> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public ReportRepositoryImpl_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public ReportRepositoryImpl get() {
    return newInstance(firestoreProvider.get());
  }

  public static ReportRepositoryImpl_Factory create(Provider<FirebaseFirestore> firestoreProvider) {
    return new ReportRepositoryImpl_Factory(firestoreProvider);
  }

  public static ReportRepositoryImpl newInstance(FirebaseFirestore firestore) {
    return new ReportRepositoryImpl(firestore);
  }
}
