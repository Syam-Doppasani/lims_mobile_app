package com.labapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\u00a6@\u00a2\u0006\u0002\u0010\tJ\"\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\b0\u00030\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001a\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\b0\u00030\u000bH&J:\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\u0013\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/labapp/data/repository/TemplateRepository;", "", "assignTemplatesToUser", "Lcom/labapp/core/util/Result;", "", "technicianId", "", "templateIds", "", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAssignedTemplatesFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/labapp/data/model/TemplateDoc;", "getTemplatesFlow", "saveTemplate", "name", "testType", "parameters", "Lcom/labapp/data/model/Parameter;", "status", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_adminRelease"})
public abstract interface TemplateRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.labapp.core.util.Result<java.util.List<com.labapp.data.model.TemplateDoc>>> getTemplatesFlow();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.labapp.core.util.Result<java.util.List<com.labapp.data.model.TemplateDoc>>> getAssignedTemplatesFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String technicianId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveTemplate(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String testType, @org.jetbrains.annotations.NotNull()
    java.util.List<com.labapp.data.model.Parameter> parameters, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object assignTemplatesToUser(@org.jetbrains.annotations.NotNull()
    java.lang.String technicianId, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> templateIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<kotlin.Unit>> $completion);
}