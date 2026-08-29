package net.minecraft.world.item;

import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public enum ItemUseAnimation implements StringRepresentable, IExtensibleEnum {

    NONE,
    EAT,
    DRINK,
    BLOCK,
    BOW,
    TRIDENT,
    CROSSBOW,
    SPYGLASS,
    TOOT_HORN,
    BRUSH,
    BUNDLE,
    SPEAR;

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemUseAnimation.getId:()I");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemUseAnimation.getSerializedName:()Ljava/lang/String;");
    }
}
