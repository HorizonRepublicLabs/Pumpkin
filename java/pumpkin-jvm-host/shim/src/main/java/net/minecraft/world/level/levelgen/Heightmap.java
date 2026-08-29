package net.minecraft.world.level.levelgen;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import dev.pumpkin.shim.Unimplemented;

public class Heightmap {

    public Heightmap(ChunkAccess chunk, Heightmap.Types heightmapType) {
    }

    public boolean update(int localX, int localY, int localZ, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/Heightmap.update:(IIILnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    public enum Types implements StringRepresentable {

        WORLD_SURFACE_WG,
        WORLD_SURFACE,
        OCEAN_FLOOR_WG,
        OCEAN_FLOOR,
        MOTION_BLOCKING,
        MOTION_BLOCKING_NO_LEAVES;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/Heightmap$Types.getSerializedName:()Ljava/lang/String;");
        }
    }

    public enum Usage {

        WORLDGEN, LIVE_WORLD, CLIENT
    }

    public Heightmap() {
    }
}
