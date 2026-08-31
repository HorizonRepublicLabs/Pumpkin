package net.neoforged.bus.api;

import java.util.function.Consumer;

/**
 * The mod event bus.
 *
 * <p>Hand-written, not generated: the event bus is published as a separate NeoForge
 * artifact whose sources are not in the decompiled tree. On the generator's "no source
 * found" list; do not delete it as un-regenerable.
 *
 * <p>An interface, as in NeoForge, and that is load-bearing rather than stylistic. It was
 * a class here for one slice, on the reasoning that a second implementation would be dead
 * weight. But a mod's call site carries the shape it was compiled against: every call the
 * two real mods make on the bus is an {@code invokeinterface}, and an {@code
 * invokeinterface} whose owner turns out to be a class is an {@code
 * IncompatibleClassChangeError} at the first call -- which for {@code bus.register(this)}
 * is the first line of a mod's constructor. Linkage resolves either way, so nothing but a
 * check for the mismatch would have found it. The implementation is {@link
 * dev.pumpkin.shim.PumpkinEventBus}.
 */
public interface IEventBus {
    /**
     * Registers {@code handler} for events of exactly {@code type} or a subtype.
     *
     * <p>NeoForge's own overload, and the one Pumpkin's code and the test mod call: it
     * says the event type outright instead of recovering it from a lambda.
     */
    <T extends Event> void addListener(Class<T> type, Consumer<T> handler);

    /** Priority variants: Pumpkin's bus keeps registration order; the priority is noted
     * and dropped -- with one mod family per bus there is nothing to order against. */
    default <T extends Event> void addListener(EventPriority priority, Class<T> type, Consumer<T> handler) {
        addListener(type, handler);
    }

    default <T extends Event> void addListener(EventPriority priority, Consumer<T> handler) {
        addListener(handler);
    }

    default <T extends Event> void addListener(EventPriority priority, boolean receiveCancelled, Class<T> type, Consumer<T> handler) {
        addListener(type, handler);
    }

    /** NeoForge's one-argument form, which infers the event type from the consumer. */
    <T extends Event> void addListener(Consumer<T> handler);

    /** Dispatches {@code event} to every matching listener and returns it. */
    <T extends Event> T post(T event);

    /** Registers every {@code @SubscribeEvent} method on {@code target} as a listener. */
    void register(Object target);
}
