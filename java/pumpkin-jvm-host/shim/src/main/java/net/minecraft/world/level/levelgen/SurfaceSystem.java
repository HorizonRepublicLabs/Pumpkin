package net.minecraft.world.level.levelgen;

import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class SurfaceSystem {

    public SurfaceSystem(RandomState randomState, BlockState defaultBlock, int seaLevel, PositionalRandomFactory noiseRandom) {
    }

    public int getSeaLevel() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceSystem.getSeaLevel:()I");
    }

    public SurfaceSystem() {
    }
}
