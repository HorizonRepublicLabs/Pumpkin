package net.minecraft.world.item;

import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public enum Rarity implements StringRepresentable, IExtensibleEnum {

    COMMON, UNCOMMON, RARE, EPIC;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/item/Rarity.getSerializedName:()Ljava/lang/String;");
    }
}
