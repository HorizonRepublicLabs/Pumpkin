package dev.pumpkin.shimgen;

/**
 * How {@link Pruner} treats a {@code net.minecraft}/{@code net.neoforged} type when
 * generating its shim.
 *
 * <ul>
 *   <li>{@link #VALUE} — an enum or a record, and nothing else. Reduced to its shape
 *       rather than copied whole: an enum's ordinals are serialised and a record is a
 *       pure data carrier, so every constant and every component is kept, in order,
 *       while the bodies around them are stubbed like anything else. There used to be a
 *       third arm here — a class of {@code static final} primitive or {@code String}
 *       constants, copied verbatim — and it is gone; see {@link Pruner#treatmentOf} for
 *       why keeping real bodies reopens the closure the generator depends on.
 *   <li>{@link #HOLDER} — a class of nothing but {@code static final} fields with
 *       initializers and no instance methods, e.g. {@code Items}, {@code Registries}.
 *       Pruned exactly like {@link #HANDLE}, and then given a throwing static
 *       initializer <em>if</em> some kept {@code final} field lost a real initializer:
 *       that field now reads a default literal instead of the registry value it named,
 *       and the class must fail loudly rather than hand out {@code null}. A holder whose
 *       fields were all pruned away has no such value to misstate and gets no
 *       initializer; see {@code Pruner.pruneHolder}.
 *   <li>{@link #HANDLE} — everything else. Only what the mods actually call survives;
 *       every surviving body throws.
 * </ul>
 */
public enum Treatment {
    VALUE,
    HANDLE,
    HOLDER
}
