package net.minecraft.world.entity;

import java.util.Iterator;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum EquipmentSlotGroup implements StringRepresentable, Iterable<EquipmentSlot> {

    ANY,
    MAINHAND,
    OFFHAND,
    HAND,
    FEET,
    LEGS,
    CHEST,
    HEAD,
    ARMOR,
    BODY,
    SADDLE;

    public static EquipmentSlotGroup bySlot(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlotGroup.bySlot:(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/entity/EquipmentSlotGroup;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlotGroup.getSerializedName:()Ljava/lang/String;");
    }

    public boolean test(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlotGroup.test:(Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    public Iterator<EquipmentSlot> iterator() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlotGroup.iterator:()Ljava/util/Iterator;");
    }
}
