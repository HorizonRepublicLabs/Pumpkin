package net.minecraft.world.level.gameevent;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public interface PositionSourceType<T extends PositionSource> {

    MapCodec<T> codec();

    StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec();

    static <S extends PositionSourceType<T>, T extends PositionSource> S register(String name, S serializer) {
        throw Unimplemented.forMember("net/minecraft/world/level/gameevent/PositionSourceType.register:(Ljava/lang/String;Lnet/minecraft/world/level/gameevent/PositionSourceType;)Lnet/minecraft/world/level/gameevent/PositionSourceType;");
    }
}
