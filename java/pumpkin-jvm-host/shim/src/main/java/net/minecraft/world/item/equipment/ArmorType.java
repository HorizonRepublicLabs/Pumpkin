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

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorType.getName:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorType.getSerializedName:()Ljava/lang/String;");
    }
}
