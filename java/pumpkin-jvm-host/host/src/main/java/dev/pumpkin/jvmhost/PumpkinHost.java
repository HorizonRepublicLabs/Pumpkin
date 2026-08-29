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

    /**
     * Registers a block with the properties its mod declared.
     *
     * <p>The plain overload keeps the template's values wholesale; this one lets a mod's
     * {@code strength(...)} and {@code requiresCorrectToolForDrops()} actually arrive.
     * {@code NaN} means "not set" for the two floats and leaves the template's value in
     * place -- a real hardness is never negative, but NaN cannot be produced by accident
     * from arithmetic on declared values, which -1 could.
     *
     * @param destroyTime         hardness, or {@code Float.NaN} when the mod did not say
     * @param explosionResistance blast resistance, or {@code Float.NaN}
     * @param requiresTool        whether drops need the right tool
     */
    public static native int registerBlockWithProperties(String id, String template,
            float destroyTime, float explosionResistance, boolean requiresTool);
}
