package net.minecraft;

import dev.pumpkin.shim.Unimplemented;

public class CrashReport {

    public CrashReport(String title, Throwable t) {
    }

    public CrashReportCategory addCategory(String name) {
        throw Unimplemented.forMember("net/minecraft/CrashReport.addCategory:(Ljava/lang/String;)Lnet/minecraft/CrashReportCategory;");
    }

    public CrashReportCategory addCategory(String name, int nestedOffset) {
        throw Unimplemented.forMember("net/minecraft/CrashReport.addCategory:(Ljava/lang/String;I)Lnet/minecraft/CrashReportCategory;");
    }

    public static CrashReport forThrowable(Throwable t, String title) {
        throw Unimplemented.forMember("net/minecraft/CrashReport.forThrowable:(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/CrashReport;");
    }

    public CrashReport() {
    }
}
