package com.labapp.domain.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010&\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B{\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b\u0012\u001a\b\u0002\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\f0\u000b\u0012\u001a\b\u0002\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\f0\u000b\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\u0015\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\bH\u00c6\u0003J\u001b\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\f0\u000bH\u00c6\u0003J\u001b\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\f0\u000bH\u00c6\u0003J\u007f\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b2\u001a\b\u0002\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\f0\u000b2\u001a\b\u0002\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\f0\u000bH\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001J\t\u0010%\u001a\u00020\tH\u00d6\u0001R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R#\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R#\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014\u00a8\u0006&"}, d2 = {"Lcom/labapp/domain/model/DashboardData;", "", "todayCount", "", "thisWeekCount", "thisMonthCount", "uniquePatients", "reportsPerDay", "", "", "testTypeBreakdown", "", "", "topReferralDoctors", "(IIIILjava/util/Map;Ljava/util/List;Ljava/util/List;)V", "getReportsPerDay", "()Ljava/util/Map;", "getTestTypeBreakdown", "()Ljava/util/List;", "getThisMonthCount", "()I", "getThisWeekCount", "getTodayCount", "getTopReferralDoctors", "getUniquePatients", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_adminRelease"})
public final class DashboardData {
    private final int todayCount = 0;
    private final int thisWeekCount = 0;
    private final int thisMonthCount = 0;
    private final int uniquePatients = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Integer> reportsPerDay = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.util.Map.Entry<java.lang.String, java.lang.Integer>> testTypeBreakdown = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.util.Map.Entry<java.lang.String, java.lang.Integer>> topReferralDoctors = null;
    
    public DashboardData(int todayCount, int thisWeekCount, int thisMonthCount, int uniquePatients, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Integer> reportsPerDay, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.Map.Entry<java.lang.String, java.lang.Integer>> testTypeBreakdown, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.Map.Entry<java.lang.String, java.lang.Integer>> topReferralDoctors) {
        super();
    }
    
    public final int getTodayCount() {
        return 0;
    }
    
    public final int getThisWeekCount() {
        return 0;
    }
    
    public final int getThisMonthCount() {
        return 0;
    }
    
    public final int getUniquePatients() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Integer> getReportsPerDay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.Map.Entry<java.lang.String, java.lang.Integer>> getTestTypeBreakdown() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.Map.Entry<java.lang.String, java.lang.Integer>> getTopReferralDoctors() {
        return null;
    }
    
    public DashboardData() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Integer> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.Map.Entry<java.lang.String, java.lang.Integer>> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.Map.Entry<java.lang.String, java.lang.Integer>> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.labapp.domain.model.DashboardData copy(int todayCount, int thisWeekCount, int thisMonthCount, int uniquePatients, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Integer> reportsPerDay, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.Map.Entry<java.lang.String, java.lang.Integer>> testTypeBreakdown, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.Map.Entry<java.lang.String, java.lang.Integer>> topReferralDoctors) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}