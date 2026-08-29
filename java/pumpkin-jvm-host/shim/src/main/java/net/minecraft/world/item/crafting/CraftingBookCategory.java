package net.minecraft.world.item.crafting;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum CraftingBookCategory implements StringRepresentable {

    BUILDING, REDSTONE, EQUIPMENT, MISC;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingBookCategory.getSerializedName:()Ljava/lang/String;");
    }

    private int id() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingBookCategory.id:()I");
    }
}
