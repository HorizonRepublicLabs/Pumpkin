package net.minecraft;

import dev.pumpkin.shim.Unimplemented;

public class CrashReportCategory {

    public CrashReportCategory(String title) {
    }

    public CrashReportCategory setDetail(String key, CrashReportDetail<String> callback) {
        throw Unimplemented.forMember("net/minecraft/CrashReportCategory.setDetail:(Ljava/lang/String;Lnet/minecraft/CrashReportDetail;)Lnet/minecraft/CrashReportCategory;");
    }

    public CrashReportCategory setDetail(String key, Object value) {
        throw Unimplemented.forMember("net/minecraft/CrashReportCategory.setDetail:(Ljava/lang/String;Ljava/lang/Object;)Lnet/minecraft/CrashReportCategory;");
    }

    public record Entry(String key, String value) {

        public Entry(String key, Object rawValue) {
            this((String) null, (String) null);
        }
    }

    public CrashReportCategory() {
    }
}
