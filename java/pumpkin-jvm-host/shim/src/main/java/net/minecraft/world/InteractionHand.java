package net.minecraft.world;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.EquipmentSlot;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public enum InteractionHand {

    MAIN_HAND, OFF_HAND;

    public static final StreamCodec<ByteBuf, InteractionHand> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public EquipmentSlot asEquipmentSlot() {
        throw Unimplemented.forMember("net/minecraft/world/InteractionHand.asEquipmentSlot:()Lnet/minecraft/world/entity/EquipmentSlot;");
    }
}
