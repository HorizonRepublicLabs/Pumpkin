package net.minecraft.world.item.crafting;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum CookingBookCategory implements StringRepresentable {

    FOOD, BLOCKS, MISC;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CookingBookCategory.getSerializedName:()Ljava/lang/String;");
    }
}
