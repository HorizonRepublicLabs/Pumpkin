package net.minecraft.resources;

/** Names a registry. Only the registry-key half of vanilla's type is modelled. */
public record ResourceKey<T>(Identifier location) {
    public static <T> ResourceKey<T> createRegistryKey(Identifier location) {
        return new ResourceKey<>(location);
    }
}
