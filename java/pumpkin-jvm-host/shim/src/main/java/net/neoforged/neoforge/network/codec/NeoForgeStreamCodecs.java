package net.neoforged.neoforge.network.codec;

import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public final class NeoForgeStreamCodecs {

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    public static <B, V> StreamCodec<B, V> lazy(Supplier<StreamCodec<B, V>> streamCodecSupplier) {
        return dev.pumpkin.shim.Stubs.of(net.minecraft.network.codec.StreamCodec.class, "net/neoforged/neoforge/network/codec/NeoForgeStreamCodecs.lazy:(Ljava/util/function/Supplier;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    private static class LazyStreamCodec<B, V> implements StreamCodec<B, V> {

        public LazyStreamCodec(Supplier<StreamCodec<B, V>> streamCodecSupplier) {
        }

        public void encode(B buf, V value) {
            throw Unimplemented.forMember("net/neoforged/neoforge/network/codec/NeoForgeStreamCodecs$LazyStreamCodec.encode:(Ljava/lang/Object;Ljava/lang/Object;)V");
        }

        public V decode(B buf) {
            throw Unimplemented.forMember("net/neoforged/neoforge/network/codec/NeoForgeStreamCodecs$LazyStreamCodec.decode:(Ljava/lang/Object;)Ljava/lang/Object;");
        }

        protected LazyStreamCodec() {
        }
    }

    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.
    public static <B extends FriendlyByteBuf, V extends Enum<V>> StreamCodec<B, V> enumCodec(Class<V> enumClass) {
        return dev.pumpkin.shim.Stubs.of(net.minecraft.network.codec.StreamCodec.class, "net/neoforged/neoforge/network/codec/NeoForgeStreamCodecs.enumCodec:(Ljava/lang/Class;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    public NeoForgeStreamCodecs() {
    }
}
