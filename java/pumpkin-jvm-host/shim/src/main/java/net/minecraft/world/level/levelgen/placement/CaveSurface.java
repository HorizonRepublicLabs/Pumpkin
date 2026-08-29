package net.minecraft.world.level.levelgen.placement;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum CaveSurface implements StringRepresentable {

    CEILING, FLOOR;

    public int getY() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/CaveSurface.getY:()I");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/CaveSurface.getSerializedName:()Ljava/lang/String;");
    }
}
