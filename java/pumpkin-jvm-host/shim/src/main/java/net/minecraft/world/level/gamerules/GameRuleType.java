package net.minecraft.world.level.gamerules;

import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public enum GameRuleType implements StringRepresentable, IExtensibleEnum {

    INT, BOOL;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/gamerules/GameRuleType.getSerializedName:()Ljava/lang/String;");
    }
}
