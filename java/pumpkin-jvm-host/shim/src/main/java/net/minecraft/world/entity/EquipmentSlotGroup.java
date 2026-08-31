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

    // Pumpkin divergence: vanilla mapping -- the group containing exactly that slot.
    public static EquipmentSlotGroup bySlot(EquipmentSlot slot) {
        return switch (slot) {
            case MAINHAND -> MAINHAND;
            case OFFHAND -> OFFHAND;
            case FEET -> FEET;
            case LEGS -> LEGS;
            case CHEST -> CHEST;
            case HEAD -> HEAD;
            case BODY -> BODY;
            case SADDLE -> SADDLE;
        };
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
