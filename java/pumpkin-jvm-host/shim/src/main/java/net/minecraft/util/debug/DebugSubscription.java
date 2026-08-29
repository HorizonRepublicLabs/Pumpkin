package net.minecraft.util.debug;

import java.util.Optional;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public class DebugSubscription<T> {

    public DebugSubscription(StreamCodec<? super RegistryFriendlyByteBuf, T> valueStreamCodec, int expireAfterTicks) {
        throw Unimplemented.forMember("net/minecraft/util/debug/DebugSubscription.<init>:(Lnet/minecraft/network/codec/StreamCodec;I)V");
    }

    public DebugSubscription(StreamCodec<? super RegistryFriendlyByteBuf, T> valueStreamCodec) {
        throw Unimplemented.forMember("net/minecraft/util/debug/DebugSubscription.<init>:(Lnet/minecraft/network/codec/StreamCodec;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/util/debug/DebugSubscription.toString:()Ljava/lang/String;");
    }

    public record Event<T>(DebugSubscription<T> subscription, T value) {
    }

    public record Update<T>(DebugSubscription<T> subscription, Optional<T> value) {
    }

    public DebugSubscription() {
    }
}
