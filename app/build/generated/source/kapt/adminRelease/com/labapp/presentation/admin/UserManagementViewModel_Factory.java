package com.labapp.presentation.admin;

import com.labapp.data.repository.TemplateRepository;
import com.labapp.data.repository.UserRepository;
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
public final class UserManagementViewModel_Factory implements Factory<UserManagementViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<TemplateRepository> templateRepositoryProvider;

  public UserManagementViewModel_Factory(Provider<UserRepository> userRepositoryProvider,
      Provider<TemplateRepository> templateRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
    this.templateRepositoryProvider = templateRepositoryProvider;
  }

  @Override
  public UserManagementViewModel get() {
    return newInstance(userRepositoryProvider.get(), templateRepositoryProvider.get());
  }

  public static UserManagementViewModel_Factory create(
      Provider<UserRepository> userRepositoryProvider,
      Provider<TemplateRepository> templateRepositoryProvider) {
    return new UserManagementViewModel_Factory(userRepositoryProvider, templateRepositoryProvider);
  }

  public static UserManagementViewModel newInstance(UserRepository userRepository,
      TemplateRepository templateRepository) {
    return new UserManagementViewModel(userRepository, templateRepository);
  }
}
