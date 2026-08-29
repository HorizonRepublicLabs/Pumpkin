package net.minecraft.world.damagesource;

import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public enum DamageEffects implements StringRepresentable, IExtensibleEnum {

    HURT,
    THORNS,
    DROWNING,
    BURNING,
    POKING,
    FREEZING;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageEffects.getSerializedName:()Ljava/lang/String;");
    }
}
