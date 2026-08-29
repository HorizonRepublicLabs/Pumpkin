package net.minecraft.tags;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public record TagKey<T>(ResourceKey<? extends Registry<T>> registry, Identifier location) {

    public static <T> TagKey<T> create(ResourceKey<? extends Registry<T>> registry, Identifier location) {
        throw Unimplemented.forMember("net/minecraft/tags/TagKey.create:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagKey;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/tags/TagKey.toString:()Ljava/lang/String;");
    }
}
