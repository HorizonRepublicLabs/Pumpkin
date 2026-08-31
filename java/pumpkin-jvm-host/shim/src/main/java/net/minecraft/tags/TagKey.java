package net.minecraft.tags;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Registry;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public record TagKey<T>(ResourceKey<? extends Registry<T>> registry, Identifier location) {

    // Pumpkin divergence: inert codec -- throws its key on first use.
    public static <T> Codec<TagKey<T>> codec(ResourceKey<? extends Registry<T>> registryName) {
        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/tags/TagKey.codec:(Lnet/minecraft/resources/ResourceKey;)Lcom/mojang/serialization/Codec;");
    }

    public static <T> StreamCodec<ByteBuf, TagKey<T>> streamCodec(ResourceKey<? extends Registry<T>> registryName) {
        throw Unimplemented.forMember("net/minecraft/tags/TagKey.streamCodec:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: real body. A tag key is its two names -- the record's own
    // canonical constructor is the whole implementation.
    public static <T> TagKey<T> create(ResourceKey<? extends Registry<T>> registry, Identifier location) {
        return new TagKey<>(registry, location);
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/tags/TagKey.toString:()Ljava/lang/String;");
    }
}
