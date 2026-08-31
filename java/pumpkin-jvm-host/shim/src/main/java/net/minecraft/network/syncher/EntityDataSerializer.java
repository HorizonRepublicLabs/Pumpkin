package net.minecraft.network.syncher;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public interface EntityDataSerializer<T> {

    StreamCodec<? super RegistryFriendlyByteBuf, T> codec();

    T copy(T value);

    static <T> EntityDataSerializer<T> forValueType(StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        throw Unimplemented.forMember("net/minecraft/network/syncher/EntityDataSerializer.forValueType:(Lnet/minecraft/network/codec/StreamCodec;)Lnet/minecraft/network/syncher/EntityDataSerializer;");
    }

    interface ForValueType<T> extends EntityDataSerializer<T> {

        default T copy(T value) {
            throw Unimplemented.forMember("net/minecraft/network/syncher/EntityDataSerializer$ForValueType.copy:(Ljava/lang/Object;)Ljava/lang/Object;");
        }
    }
}
