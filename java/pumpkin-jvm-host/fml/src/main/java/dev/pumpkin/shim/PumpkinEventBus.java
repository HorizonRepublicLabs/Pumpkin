package dev.pumpkin.shim;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
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
        // A Class target subscribes its static methods; an instance subscribes its instance
        // methods. NeoForge distinguishes the two the same way, and mods use both.
        boolean staticTarget = target instanceof Class<?>;
        Class<?> owner = staticTarget ? (Class<?>) target : target.getClass();

        // Walk the hierarchy: a mod may put handlers on a base class and register the
        // subclass. getDeclaredMethods sees only one level, so climbing is required.
        for (Class<?> type = owner; type != null && type != Object.class; type = type.getSuperclass()) {
            for (Method method : type.getDeclaredMethods()) {
                if (!method.isAnnotationPresent(SubscribeEvent.class)) {
                    continue;
                }
                if (Modifier.isStatic(method.getModifiers()) != staticTarget) {
                    continue;
                }
                subscribe(method, staticTarget ? null : target);
            }
        }
    }

    /**
     * Wires one {@code @SubscribeEvent} method into the bus.
     *
     * <p>Rejects a handler whose shape cannot work rather than skipping it. A method carrying
     * the annotation is a statement of intent, so one that takes the wrong arguments is a
     * mistake worth naming — silently ignoring it produces a mod whose events never fire and
     * nothing to explain why.
     */
    private void subscribe(Method method, Object receiver) {
        if (method.getParameterCount() != 1) {
            throw new IllegalArgumentException(
                    "@SubscribeEvent method " + method + " must take exactly one event argument");
        }
        Class<?> parameter = method.getParameterTypes()[0];
        if (!Event.class.isAssignableFrom(parameter)) {
            throw new IllegalArgumentException(
                    "@SubscribeEvent method " + method + " takes " + parameter.getName()
                            + ", which is not an Event");
        }

        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        Class<Event> eventType = (Class<Event>) parameter;
        addListener(eventType, event -> invoke(method, receiver, event));
    }

    /** Invokes a handler, unwrapping the reflection layer so the mod's own failure surfaces. */
    private static void invoke(Method method, Object receiver, Event event) {
        try {
            method.invoke(receiver, event);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            if (cause instanceof RuntimeException runtime) {
                throw runtime;
            }
            if (cause instanceof Error error) {
                throw error;
            }
            throw new IllegalStateException("handler " + method + " failed", cause);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("handler " + method + " is not callable", e);
        }
    }
}
