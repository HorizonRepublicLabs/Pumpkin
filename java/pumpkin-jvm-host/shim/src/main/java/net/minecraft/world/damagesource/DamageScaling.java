package net.minecraft.world.damagesource;

import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public enum DamageScaling implements StringRepresentable, IExtensibleEnum {

    NEVER, WHEN_CAUSED_BY_LIVING_NON_PLAYER, ALWAYS;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageScaling.getSerializedName:()Ljava/lang/String;");
    }
}
