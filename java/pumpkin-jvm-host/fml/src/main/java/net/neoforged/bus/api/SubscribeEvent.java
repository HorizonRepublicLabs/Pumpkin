package net.neoforged.bus.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as an event handler for {@link IEventBus#register(Object)}.
 *
 * <p>Hand-written, not generated: the event bus is published as a separate NeoForge artifact
 * whose sources are not in the decompiled tree. On the generator's "no source found" list;
 * do not delete it as un-regenerable.
 *
 * <p>Retention is runtime because that is the whole point — {@code register} finds these by
 * reflection at load time, and a mod's handlers are invisible without it.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubscribeEvent {
}
