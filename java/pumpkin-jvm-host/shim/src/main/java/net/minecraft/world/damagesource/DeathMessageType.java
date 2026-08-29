package net.minecraft.world.damagesource;

import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public enum DeathMessageType implements StringRepresentable, IExtensibleEnum {

    DEFAULT, FALL_VARIANTS, INTENTIONAL_GAME_DESIGN;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DeathMessageType.getSerializedName:()Ljava/lang/String;");
    }
}
