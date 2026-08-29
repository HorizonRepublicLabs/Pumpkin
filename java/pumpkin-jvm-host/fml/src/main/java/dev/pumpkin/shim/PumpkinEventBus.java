package dev.pumpkin.shim;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;

/**
 * Pumpkin's implementation of {@link IEventBus}.
 *
 * <p>Dispatch is by runtime type and is not thread-safe: everything on this bus runs on
 * the mod thread.
 *
 * <p>Two of the four methods throw. That is not an oversight to be filled in later without
 * thought: both need information this bus does not have. {@link #addListener(Consumer)}
 * needs the consumer's erased type argument, which NeoForge recovers by reading the
 * lambda's implementation method out of the class file. {@link #register(Object)} needs
 * {@code @SubscribeEvent}, which this repo does not have. Registering nothing in either
 * case would leave a mod believing its handlers are live, which is the silent-wrong-value
 * failure the whole shim is built to avoid.
 */
public final class PumpkinEventBus implements IEventBus {
    private record Listener<T extends Event>(Class<?> type, Consumer<T> handler) {
    }

    private final List<Listener<? extends Event>> listeners = new ArrayList<>();

    @Override
    public <T extends Event> void addListener(Class<T> type, Consumer<T> handler) {
        listeners.add(new Listener<>(type, handler));
    }

    @Override
    public <T extends Event> void addListener(Consumer<T> handler) {
        throw Unimplemented.forMember("net/neoforged/bus/api/IEventBus.addListener:(Ljava/util/function/Consumer;)V");
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Event> T post(T event) {
        for (Listener<? extends Event> listener : List.copyOf(listeners)) {
            if (listener.type().isInstance(event)) {
                ((Consumer<Event>) listener.handler()).accept(event);
            }
        }
        return event;
    }

    @Override
    public void register(Object target) {
        throw Unimplemented.forMember("net/neoforged/bus/api/IEventBus.register:(Ljava/lang/Object;)V");
    }
}
