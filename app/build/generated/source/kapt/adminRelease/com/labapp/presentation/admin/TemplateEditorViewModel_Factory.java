package com.labapp.presentation.admin;

import com.labapp.data.repository.TemplateRepository;
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
public final class TemplateEditorViewModel_Factory implements Factory<TemplateEditorViewModel> {
  private final Provider<TemplateRepository> templateRepositoryProvider;

  public TemplateEditorViewModel_Factory(Provider<TemplateRepository> templateRepositoryProvider) {
    this.templateRepositoryProvider = templateRepositoryProvider;
  }

  @Override
  public TemplateEditorViewModel get() {
    return newInstance(templateRepositoryProvider.get());
  }

  public static TemplateEditorViewModel_Factory create(
      Provider<TemplateRepository> templateRepositoryProvider) {
    return new TemplateEditorViewModel_Factory(templateRepositoryProvider);
  }

  public static TemplateEditorViewModel newInstance(TemplateRepository templateRepository) {
    return new TemplateEditorViewModel(templateRepository);
  }
}
