package net.minecraft.world.item;

import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public enum ItemDisplayContext implements StringRepresentable, IExtensibleEnum {

    NONE,
    THIRD_PERSON_LEFT_HAND,
    THIRD_PERSON_RIGHT_HAND,
    FIRST_PERSON_LEFT_HAND,
    FIRST_PERSON_RIGHT_HAND,
    HEAD,
    GUI,
    GROUND,
    FIXED,
    ON_SHELF;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemDisplayContext.getSerializedName:()Ljava/lang/String;");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemDisplayContext.getId:()B");
    }
}
