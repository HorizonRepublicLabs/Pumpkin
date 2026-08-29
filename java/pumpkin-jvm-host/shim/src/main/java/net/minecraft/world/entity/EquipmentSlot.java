package net.minecraft.world.entity;

import net.minecraft.util.StringRepresentable;
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

    public EquipmentSlot.Type getType() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlot.getType:()Lnet/minecraft/world/entity/EquipmentSlot$Type;");
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
