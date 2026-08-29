package net.minecraft.world.item.equipment;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum ArmorType implements StringRepresentable {

    HELMET, CHESTPLATE, LEGGINGS, BOOTS, BODY;

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorType.getName:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorType.getSerializedName:()Ljava/lang/String;");
    }
}
