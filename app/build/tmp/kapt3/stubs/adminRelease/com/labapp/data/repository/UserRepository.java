package com.labapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00040\u0003H&J\u001a\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00040\u0003H&J,\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/labapp/data/repository/UserRepository;", "", "getActiveUsers", "Lkotlinx/coroutines/flow/Flow;", "Lcom/labapp/core/util/Result;", "", "Lcom/labapp/data/model/UserDoc;", "getPendingUsers", "setUserRole", "", "targetUserId", "", "role", "approved", "", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_adminRelease"})
public abstract interface UserRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.labapp.core.util.Result<java.util.List<com.labapp.data.model.UserDoc>>> getPendingUsers();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.labapp.core.util.Result<java.util.List<com.labapp.data.model.UserDoc>>> getActiveUsers();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setUserRole(@org.jetbrains.annotations.NotNull()
    java.lang.String targetUserId, @org.jetbrains.annotations.NotNull()
    java.lang.String role, boolean approved, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.labapp.core.util.Result<kotlin.Unit>> $completion);
}