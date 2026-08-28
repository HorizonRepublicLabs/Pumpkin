package net.neoforged.bus.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * The mod event bus.
 *
 * <p>A class in vanilla NeoForge, an interface here would need a second implementation for
 * no gain. Dispatch is by runtime type and is not thread-safe: everything on it runs on the
 * mod thread.
 */
public class IEventBus {
    private record Listener<T extends Event>(Class<?> type, Consumer<T> handler) {
    }

    private final List<Listener<? extends Event>> listeners = new ArrayList<>();

    public <T extends Event> void addListener(Class<T> type, Consumer<T> handler) {
        listeners.add(new Listener<>(type, handler));
    }

    @SuppressWarnings("unchecked")
    public void post(Event event) {
        for (Listener<? extends Event> listener : List.copyOf(listeners)) {
            if (listener.type().isInstance(event)) {
                ((Consumer<Event>) listener.handler()).accept(event);
            }
        }
    }
}
