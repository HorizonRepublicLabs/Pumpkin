package net.minecraft.world.level.levelgen.structure.templatesystem;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum LiquidSettings implements StringRepresentable {

    IGNORE_WATERLOGGING, APPLY_WATERLOGGING;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/LiquidSettings.getSerializedName:()Ljava/lang/String;");
    }
}
