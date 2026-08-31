package net.minecraft.core;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class RegistryCodecs {

    // Pumpkin divergence: inert codec -- throws its key on first use.
    public static <E> Codec<HolderSet<E>> homogeneousList(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec) {
        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/RegistryCodecs.homogeneousList:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first use.
    public static <E> Codec<HolderSet<E>> homogeneousList(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec, boolean alwaysUseList) {
        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/RegistryCodecs.homogeneousList:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Z)Lcom/mojang/serialization/Codec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first use.
    public static <E> Codec<HolderSet<E>> homogeneousList(ResourceKey<? extends Registry<E>> registryKey) {
        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/RegistryCodecs.homogeneousList:(Lnet/minecraft/resources/ResourceKey;)Lcom/mojang/serialization/Codec;");
    }

    // Pumpkin divergence: inert codec -- throws its key on first use.
    public static <E> Codec<HolderSet<E>> homogeneousList(ResourceKey<? extends Registry<E>> registryKey, boolean alwaysUseList) {
        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/RegistryCodecs.homogeneousList:(Lnet/minecraft/resources/ResourceKey;Z)Lcom/mojang/serialization/Codec;");
    }

    public RegistryCodecs() {
    }
}
