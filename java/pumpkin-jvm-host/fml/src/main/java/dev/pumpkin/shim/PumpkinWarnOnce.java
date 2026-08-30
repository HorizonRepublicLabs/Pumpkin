package dev.pumpkin.shim;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A warning that fires once per key.
 *
 * <p>For facts worth saying but not repeating: an ingredient that matches by tag inside a
 * recipe checked twenty times a second would otherwise flood the log with the same line.
 */
public final class PumpkinWarnOnce {
    private PumpkinWarnOnce() {
    }

    private static final Set<String> SAID = ConcurrentHashMap.newKeySet();

    public static void warn(String key, String message) {
        if (SAID.add(key)) {
            System.err.println("[pumpkin] " + message);
        }
    }
}
