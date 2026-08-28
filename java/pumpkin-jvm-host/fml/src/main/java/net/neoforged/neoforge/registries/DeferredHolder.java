package net.neoforged.neoforge.registries;

import java.util.function.Supplier;
import net.minecraft.resources.Identifier;

/**
 * A registered object plus the id it got. Resolves only after registration has run.
 *
 * <p>Real NeoForge's {@code DeferredHolder<R, T extends R>} also implements {@code Holder<R>};
 * that is left out here since it drags in {@code net.minecraft.core.Holder}'s transitive
 * surface for no gain in this slice — mod code names this class, not the interface.
 *
 * @param <R> the base registry type, e.g. {@code Block}
 * @param <T> the concrete type held, e.g. a mod's block subclass
 */
public final class DeferredHolder<R, T extends R> implements Supplier<T> {
    private final Identifier id;
    private final Supplier<T> factory;
    private T value;

    DeferredHolder(Identifier id, Supplier<T> factory) {
        this.id = id;
        this.factory = factory;
    }

    public Identifier getId() {
        return id;
    }

    @Override
    public T get() {
        if (value == null) {
            value = factory.get();
        }
        return value;
    }
}
