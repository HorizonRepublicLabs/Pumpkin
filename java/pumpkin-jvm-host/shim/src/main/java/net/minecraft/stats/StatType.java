package net.minecraft.stats;

import java.util.Iterator;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public class StatType<T> implements Iterable<Stat<T>> {

    public StatType(Registry<T> registry, Component displayName) {
        throw Unimplemented.forMember("net/minecraft/stats/StatType.<init>:(Lnet/minecraft/core/Registry;Lnet/minecraft/network/chat/Component;)V");
    }

    public StreamCodec<RegistryFriendlyByteBuf, Stat<T>> streamCodec() {
        throw Unimplemented.forMember("net/minecraft/stats/StatType.streamCodec:()Lnet/minecraft/network/codec/StreamCodec;");
    }

    public boolean contains(T key) {
        throw Unimplemented.forMember("net/minecraft/stats/StatType.contains:(Ljava/lang/Object;)Z");
    }

    public Stat<T> get(T argument, StatFormatter formatter) {
        throw Unimplemented.forMember("net/minecraft/stats/StatType.get:(Ljava/lang/Object;Lnet/minecraft/stats/StatFormatter;)Lnet/minecraft/stats/Stat;");
    }

    public Iterator<Stat<T>> iterator() {
        throw Unimplemented.forMember("net/minecraft/stats/StatType.iterator:()Ljava/util/Iterator;");
    }

    public Stat<T> get(T argument) {
        throw Unimplemented.forMember("net/minecraft/stats/StatType.get:(Ljava/lang/Object;)Lnet/minecraft/stats/Stat;");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/stats/StatType.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public StatType() {
    }
}
