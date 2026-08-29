package net.neoforged.bus.api;

import dev.pumpkin.shim.Unimplemented;

/**
 * Hand-written, not generated: NeoForge publishes the event bus as a separate artifact
 * whose sources are not in the decompiled tree, so the generator has nothing to read.
 * It appears on the generator's "no source found" list; do not delete it as un-regenerable.
 *
 * <p>Both methods are {@code default} rather than abstract, so that a generated event
 * class which declares neither still compiles. Cancellation is real behaviour and nothing
 * implements it yet, so they throw rather than answer "not cancelled".
 */
public interface ICancellableEvent {
    default boolean isCanceled() {
        throw Unimplemented.forMember("net/neoforged/bus/api/ICancellableEvent.isCanceled:()Z");
    }

    default void setCanceled(boolean canceled) {
        throw Unimplemented.forMember("net/neoforged/bus/api/ICancellableEvent.setCanceled:(Z)V");
    }
}
