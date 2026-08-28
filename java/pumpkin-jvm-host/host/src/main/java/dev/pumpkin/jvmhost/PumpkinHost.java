package dev.pumpkin.jvmhost;

/**
 * The calls Java makes into Pumpkin.
 *
 * <p>Bound with {@code RegisterNatives} while the VM starts, not with
 * {@code System.loadLibrary}: Pumpkin is the executable, not a library the JVM can find by
 * name.
 */
public final class PumpkinHost {
    private PumpkinHost() {
    }

    /**
     * Registers a block copied from a vanilla template.
     *
     * @param id       namespaced id, e.g. {@code testmod:ruby_block}
     * @param template vanilla block whose definition is copied, e.g. {@code stone}
     * @return the assigned block id
     * @throws IllegalStateException if registration failed or the registries are frozen
     */
    public static native int registerBlock(String id, String template);
}
