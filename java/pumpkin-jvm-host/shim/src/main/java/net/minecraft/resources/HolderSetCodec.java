package net.minecraft.resources;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import dev.pumpkin.shim.Unimplemented;

public class HolderSetCodec<E> implements Codec<HolderSet<E>> {

    public static <E> Codec<HolderSet<E>> create(ResourceKey<? extends Registry<E>> registryKey, Codec<Holder<E>> elementCodec, boolean alwaysUseList) {
        throw Unimplemented.forMember("net/minecraft/resources/HolderSetCodec.create:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Z)Lcom/mojang/serialization/Codec;");
    }

    private HolderSetCodec(ResourceKey<? extends Registry<E>> registryKey, Codec<Holder<E>> elementCodec, boolean alwaysUseList) {
    }

    public <T> DataResult<Pair<HolderSet<E>, T>> decode(DynamicOps<T> ops, T input) {
        throw Unimplemented.forMember("net/minecraft/resources/HolderSetCodec.decode:(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public <T> DataResult<T> encode(HolderSet<E> input, DynamicOps<T> ops, T prefix) {
        throw Unimplemented.forMember("net/minecraft/resources/HolderSetCodec.encode:(Lnet/minecraft/core/HolderSet;Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public HolderSetCodec() {
    }
}
