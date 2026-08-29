package net.minecraft;

import dev.pumpkin.shim.Unimplemented;

public class ReportedException extends RuntimeException {

    public ReportedException(CrashReport report) {
        throw Unimplemented.forMember("net/minecraft/ReportedException.<init>:(Lnet/minecraft/CrashReport;)V");
    }

    public CrashReport getReport() {
        throw Unimplemented.forMember("net/minecraft/ReportedException.getReport:()Lnet/minecraft/CrashReport;");
    }

    public Throwable getCause() {
        throw Unimplemented.forMember("net/minecraft/ReportedException.getCause:()Ljava/lang/Throwable;");
    }

    public String getMessage() {
        throw Unimplemented.forMember("net/minecraft/ReportedException.getMessage:()Ljava/lang/String;");
    }

    protected ReportedException() {
    }
}
