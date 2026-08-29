package net.minecraft.world.level;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import dev.pumpkin.shim.Unimplemented;

public final class NoiseColumn implements BlockColumn {

    public NoiseColumn(int minY, BlockState[] column) {
    }

    public BlockState getBlock(int blockY) {
        throw Unimplemented.forMember("net/minecraft/world/level/NoiseColumn.getBlock:(I)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public void setBlock(int blockY, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/NoiseColumn.setBlock:(ILnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public NoiseColumn() {
    }
}
