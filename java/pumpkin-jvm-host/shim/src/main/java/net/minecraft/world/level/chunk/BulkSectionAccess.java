package net.minecraft.world.level.chunk;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class BulkSectionAccess implements AutoCloseable {

    public BulkSectionAccess(LevelAccessor level) {
    }

    public LevelChunkSection getSection(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/BulkSectionAccess.getSection:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/chunk/LevelChunkSection;");
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/BulkSectionAccess.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/BulkSectionAccess.close:()V");
    }

    public BulkSectionAccess() {
    }
}
