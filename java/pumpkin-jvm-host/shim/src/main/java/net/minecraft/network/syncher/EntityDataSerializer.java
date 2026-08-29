package net.minecraft.network.syncher;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public interface EntityDataSerializer<T> {

    StreamCodec<? super RegistryFriendlyByteBuf, T> codec();

    T copy(T value);

    interface ForValueType<T> extends EntityDataSerializer<T> {

        default T copy(T value) {
            throw Unimplemented.forMember("net/minecraft/network/syncher/EntityDataSerializer$ForValueType.copy:(Ljava/lang/Object;)Ljava/lang/Object;");
        }
    }
}
