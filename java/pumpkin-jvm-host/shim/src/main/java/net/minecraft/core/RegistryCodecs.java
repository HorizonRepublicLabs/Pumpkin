package net.minecraft.core;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class RegistryCodecs {

    public static <E> Codec<HolderSet<E>> homogeneousList(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec) {
        throw Unimplemented.forMember("net/minecraft/core/RegistryCodecs.homogeneousList:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");
    }

    public static <E> Codec<HolderSet<E>> homogeneousList(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec, boolean alwaysUseList) {
        throw Unimplemented.forMember("net/minecraft/core/RegistryCodecs.homogeneousList:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Z)Lcom/mojang/serialization/Codec;");
    }

    public static <E> Codec<HolderSet<E>> homogeneousList(ResourceKey<? extends Registry<E>> registryKey) {
        throw Unimplemented.forMember("net/minecraft/core/RegistryCodecs.homogeneousList:(Lnet/minecraft/resources/ResourceKey;)Lcom/mojang/serialization/Codec;");
    }

    public static <E> Codec<HolderSet<E>> homogeneousList(ResourceKey<? extends Registry<E>> registryKey, boolean alwaysUseList) {
        throw Unimplemented.forMember("net/minecraft/core/RegistryCodecs.homogeneousList:(Lnet/minecraft/resources/ResourceKey;Z)Lcom/mojang/serialization/Codec;");
    }

    public RegistryCodecs() {
    }
}
