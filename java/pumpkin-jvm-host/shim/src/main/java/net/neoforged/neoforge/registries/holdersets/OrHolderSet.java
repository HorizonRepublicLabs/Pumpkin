package net.neoforged.neoforge.registries.holdersets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Set;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class OrHolderSet<T> extends CompositeHolderSet<T> {

    public OrHolderSet(List<HolderSet<T>> values) {
    }

    public OrHolderSet(HolderSet<T>... values) {
    }

    public HolderSetType type() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/OrHolderSet.type:()Lnet/neoforged/neoforge/registries/holdersets/HolderSetType;");
    }

    protected Set<Holder<T>> createSet() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/OrHolderSet.createSet:()Ljava/util/Set;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/OrHolderSet.toString:()Ljava/lang/String;");
    }

    public static class Type implements HolderSetType {

        public <T> MapCodec<? extends ICustomHolderSet<T>> makeCodec(ResourceKey<? extends Registry<T>> registryKey, Codec<Holder<T>> holderCodec, boolean forceList) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/OrHolderSet$Type.makeCodec:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Z)Lcom/mojang/serialization/MapCodec;");
        }

        public <T> StreamCodec<RegistryFriendlyByteBuf, ? extends ICustomHolderSet<T>> makeStreamCodec(ResourceKey<? extends Registry<T>> registryKey) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/OrHolderSet$Type.makeStreamCodec:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/network/codec/StreamCodec;");
        }

        public Type() {
        }
    }

    public OrHolderSet() {
    }
}
