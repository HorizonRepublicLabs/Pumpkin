package net.minecraft.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public record TagKey<T>(ResourceKey<? extends Registry<T>> registry, Identifier location) {

    // Pumpkin divergence: real body. A tag key is its two names -- the record's own
    // canonical constructor is the whole implementation.
    public static <T> TagKey<T> create(ResourceKey<? extends Registry<T>> registry, Identifier location) {
        return new TagKey<>(registry, location);
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/tags/TagKey.toString:()Ljava/lang/String;");
    }
}
