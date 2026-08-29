package dev.pumpkin.shimgen;

/**
 * How {@link Pruner} treats a {@code net.minecraft}/{@code net.neoforged} type when
 * generating its shim.
 *
 * <ul>
 *   <li>{@link #VALUE} — an enum, a record, or (rarely) a class whose every field is a
 *       {@code static final} primitive or {@code String} constant. Copied whole: an
 *       enum's ordinals are serialised, so dropping a constant would shift every
 *       ordinal after it.
 *   <li>{@link #HOLDER} — a class of nothing but {@code static final} fields with
 *       initializers and no instance methods, e.g. {@code Items}, {@code Registries}.
 *       Its initializers call real registry code that cannot exist in the shim, so
 *       touching the class must fail loudly rather than yield {@code null}.
 *   <li>{@link #HANDLE} — everything else. Only what the mods actually call survives;
 *       every surviving body throws.
 * </ul>
 */
public enum Treatment {
    VALUE,
    HANDLE,
    HOLDER
}
