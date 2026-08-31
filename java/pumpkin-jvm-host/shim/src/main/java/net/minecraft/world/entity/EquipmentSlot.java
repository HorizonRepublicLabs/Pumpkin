package net.minecraft.world.entity;

import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public enum EquipmentSlot implements StringRepresentable {

    MAINHAND,
    OFFHAND,
    FEET,
    LEGS,
    CHEST,
    HEAD,
    BODY,
    SADDLE;

    public static final List<EquipmentSlot> VALUES = null;

    public static final StreamCodec<ByteBuf, EquipmentSlot> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public EquipmentSlot.Type getType() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlot.getType:()Lnet/minecraft/world/entity/EquipmentSlot$Type;");
    }

    public int getIndex() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlot.getIndex:()I");
    }

    public int getIndex(int base) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlot.getIndex:(I)I");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlot.getId:()I");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlot.getName:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlot.getSerializedName:()Ljava/lang/String;");
    }

    public enum Type {

        HAND, HUMANOID_ARMOR, ANIMAL_ARMOR, SADDLE
    }
}
