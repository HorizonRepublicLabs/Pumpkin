package net.neoforged.neoforge.registries;

import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;

/** A registered object plus the id it got. Resolves only after registration has run. */
public final class DeferredHolder<T> implements Supplier<T> {
    private final ResourceLocation id;
    private final Supplier<T> factory;
    private T value;

    DeferredHolder(ResourceLocation id, Supplier<T> factory) {
        this.id = id;
        this.factory = factory;
    }

    public ResourceLocation getId() {
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
