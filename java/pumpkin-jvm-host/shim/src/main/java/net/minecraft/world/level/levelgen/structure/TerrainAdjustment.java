package net.minecraft.world.level.levelgen.structure;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum TerrainAdjustment implements StringRepresentable {

    NONE, BURY, BEARD_THIN, BEARD_BOX, ENCAPSULATE;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/TerrainAdjustment.getSerializedName:()Ljava/lang/String;");
    }
}
