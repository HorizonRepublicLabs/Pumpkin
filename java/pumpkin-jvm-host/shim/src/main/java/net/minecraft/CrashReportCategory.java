package net.minecraft;

import dev.pumpkin.shim.Unimplemented;

public class CrashReportCategory {

    public CrashReportCategory(String title) {
        throw Unimplemented.forMember("net/minecraft/CrashReportCategory.<init>:(Ljava/lang/String;)V");
    }

    public record Entry(String key, String value) {

        public Entry(String key, Object rawValue) {
            this((String) null, (String) null);
            throw Unimplemented.forMember("net/minecraft/CrashReportCategory$Entry.<init>:(Ljava/lang/String;Ljava/lang/Object;)V");
        }
    }

    protected CrashReportCategory() {
    }
}
