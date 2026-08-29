package net.minecraft.world.level.levelgen.structure;

import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public class BoundingBox {

    public BoundingBox(BlockPos content) {
    }

    public BoundingBox(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/BoundingBox.toString:()Ljava/lang/String;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/BoundingBox.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/BoundingBox.hashCode:()I");
    }

    public BoundingBox() {
    }
}
