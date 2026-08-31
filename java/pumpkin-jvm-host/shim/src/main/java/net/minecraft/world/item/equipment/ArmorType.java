package net.minecraft.world.item.equipment;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;
import dev.pumpkin.shim.Unimplemented;

public enum ArmorType implements StringRepresentable {

    HELMET, CHESTPLATE, LEGGINGS, BOOTS, BODY;

    public int getDurability(int multiplier) {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorType.getDurability:(I)I");
    }

    // Pumpkin divergence: vanilla mapping.
    public EquipmentSlot getSlot() {
        return switch (this) {
            case HELMET -> EquipmentSlot.HEAD;
            case CHESTPLATE -> EquipmentSlot.CHEST;
            case LEGGINGS -> EquipmentSlot.LEGS;
            case BOOTS -> EquipmentSlot.FEET;
            case BODY -> EquipmentSlot.BODY;
        };
    }

    // Pumpkin divergence: vanilla body -- the lowercase constant name.
    public String getName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }

    public String getSerializedName() {
        return getName();
    }
}
