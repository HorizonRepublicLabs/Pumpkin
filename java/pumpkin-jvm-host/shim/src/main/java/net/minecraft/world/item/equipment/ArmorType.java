package net.minecraft.world.item.equipment;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;
import dev.pumpkin.shim.Unimplemented;

public enum ArmorType implements StringRepresentable {

    HELMET, CHESTPLATE, LEGGINGS, BOOTS, BODY;

    public int getDurability(int multiplier) {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorType.getDurability:(I)I");
    }

    public EquipmentSlot getSlot() {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorType.getSlot:()Lnet/minecraft/world/entity/EquipmentSlot;");
    }

    // Pumpkin divergence: vanilla body -- the lowercase constant name.
    public String getName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }

    public String getSerializedName() {
        return getName();
    }
}
