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

    /**
     * Registers an item copied from a vanilla template.
     *
     * <p>The new item takes the template's whole definition -- components, stack size --
     * verbatim. Item behaviour beyond existing (use handlers, tool logic) is a future
     * slice, the same boundary the block natives draw around drops and hooks.
     *
     * @param id       namespaced id, e.g. {@code testmod:ruby}
     * @param template vanilla item whose definition is copied, e.g. {@code stone}
     * @return the assigned item id
     * @throws IllegalStateException if the template is unknown, registration failed, or
     *                               the registries are frozen
     */
    public static native int registerItem(String id, String template);

    /**
     * Registers an item with the properties its mod declared.
     *
     * <p>The plain overload keeps the template's definition wholesale; this one lets a
     * mod's {@code stacksTo(...)} and {@code durability(...)} actually arrive, and links a
     * block item to the block it places. {@code -1} means "not set" for the two ints and
     * leaves the template's component in place; a real stack size or durability is never
     * negative.
     *
     * @param maxStackSize declared stack size, or {@code -1} when the mod did not say
     * @param maxDamage    declared durability, or {@code -1}
     * @param blockId      namespaced id of the block this item places, or {@code null}
     *                     for an item that places nothing
     */
    public static native int registerItemWithProperties(String id, String template,
            int maxStackSize, int maxDamage, String blockId);

    /**
     * Registers a block entity type.
     *
     * <p>An id and a name are all Pumpkin needs for the type to survive the protocol.
     * Concrete behaviour -- ticking, inventory, save data -- is a future slice.
     *
     * @param id namespaced id, e.g. {@code testmod:pedestal}
     * @return the assigned block entity type id
     * @throws IllegalStateException if registration failed or the registries are frozen
     */
    public static native int registerBlockEntityType(String id);
}
