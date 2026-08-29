package net.minecraft;

public class CrashReportCategory {

    public CrashReportCategory(String title) {
    }

    public record Entry(String key, String value) {

        public Entry(String key, Object rawValue) {
            this((String) null, (String) null);
        }
    }

    public CrashReportCategory() {
    }
}
