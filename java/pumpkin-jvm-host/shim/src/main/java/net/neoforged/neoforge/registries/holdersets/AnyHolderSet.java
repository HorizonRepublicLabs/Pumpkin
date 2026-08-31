package net.neoforged.neoforge.registries.holdersets;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public record AnyHolderSet<T>(ResourceKey<? extends Registry<T>> registryKey, HolderLookup<T> registryLookup) implements ICustomHolderSet<T> {

    public AnyHolderSet(HolderLookup.RegistryLookup<T> registryLookup) {
        this((ResourceKey<? extends Registry<T>>) null, (HolderLookup<T>) null);
    }

    public HolderSetType type() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.type:()Lnet/neoforged/neoforge/registries/holdersets/HolderSetType;");
    }

    public Iterator<Holder<T>> iterator() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.iterator:()Ljava/util/Iterator;");
    }

    public Stream<Holder<T>> stream() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.stream:()Ljava/util/stream/Stream;");
    }

    public int size() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.size:()I");
    }

    public Either<TagKey<T>, List<Holder<T>>> unwrap() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.unwrap:()Lcom/mojang/datafixers/util/Either;");
    }

    public Optional<Holder<T>> getRandomElement(RandomSource random) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.getRandomElement:(Lnet/minecraft/util/RandomSource;)Ljava/util/Optional;");
    }

    public Holder<T> get(int i) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.get:(I)Lnet/minecraft/core/Holder;");
    }

    public boolean contains(Holder<T> holder) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.contains:(Lnet/minecraft/core/Holder;)Z");
    }

    public boolean canSerializeIn(HolderOwner<T> holderOwner) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.canSerializeIn:(Lnet/minecraft/core/HolderOwner;)Z");
    }

    public Optional<TagKey<T>> unwrapKey() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.unwrapKey:()Ljava/util/Optional;");
    }

    public boolean isBound() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.isBound:()Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet.toString:()Ljava/lang/String;");
    }

    public static class Type implements HolderSetType {

        public <T> MapCodec<? extends ICustomHolderSet<T>> makeCodec(ResourceKey<? extends Registry<T>> registryKey, Codec<Holder<T>> holderCodec, boolean forceList) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet$Type.makeCodec:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Z)Lcom/mojang/serialization/MapCodec;");
        }

        public <T> StreamCodec<RegistryFriendlyByteBuf, ? extends ICustomHolderSet<T>> makeStreamCodec(ResourceKey<? extends Registry<T>> registryKey) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/AnyHolderSet$Type.makeStreamCodec:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/network/codec/StreamCodec;");
        }

        public Type() {
        }
    }
}
