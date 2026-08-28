package net.minecraft.core;

import net.minecraft.resources.ResourceLocation;

/** Names a registry. Only the registry-key half of vanilla's type is modelled. */
public record ResourceKey<T>(ResourceLocation location) {
    public static <T> ResourceKey<T> createRegistryKey(ResourceLocation location) {
        return new ResourceKey<>(location);
    }
}
