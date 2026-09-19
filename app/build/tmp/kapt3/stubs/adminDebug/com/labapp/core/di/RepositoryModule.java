package com.labapp.core.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H\'\u00a8\u0006\u0013"}, d2 = {"Lcom/labapp/core/di/RepositoryModule;", "", "()V", "bindAuthRepository", "Lcom/labapp/data/repository/AuthRepository;", "impl", "Lcom/labapp/data/repository/AuthRepositoryImpl;", "bindReferralDoctorRepository", "Lcom/labapp/data/repository/ReferralDoctorRepository;", "Lcom/labapp/data/repository/ReferralDoctorRepositoryImpl;", "bindReportRepository", "Lcom/labapp/data/repository/ReportRepository;", "Lcom/labapp/data/repository/ReportRepositoryImpl;", "bindTemplateRepository", "Lcom/labapp/data/repository/TemplateRepository;", "Lcom/labapp/data/repository/TemplateRepositoryImpl;", "bindUserRepository", "Lcom/labapp/data/repository/UserRepository;", "Lcom/labapp/data/repository/UserRepositoryImpl;", "app_adminDebug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.labapp.data.repository.AuthRepository bindAuthRepository(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.AuthRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.labapp.data.repository.UserRepository bindUserRepository(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.UserRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.labapp.data.repository.TemplateRepository bindTemplateRepository(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.TemplateRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.labapp.data.repository.ReportRepository bindReportRepository(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.ReportRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.labapp.data.repository.ReferralDoctorRepository bindReferralDoctorRepository(@org.jetbrains.annotations.NotNull()
    com.labapp.data.repository.ReferralDoctorRepositoryImpl impl);
}