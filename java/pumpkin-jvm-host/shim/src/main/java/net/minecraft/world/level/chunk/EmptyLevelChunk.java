package net.minecraft.world.level.chunk;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.FullChunkStatus;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import dev.pumpkin.shim.Unimplemented;

public class EmptyLevelChunk extends LevelChunk {

    public EmptyLevelChunk(Level level, ChunkPos pos, Holder<Biome> biome) {
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public BlockState setBlockState(BlockPos pos, BlockState state, int flags) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.setBlockState:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public FluidState getFluidState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.getFluidState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;");
    }

    public int getLightEmission(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.getLightEmission:(Lnet/minecraft/core/BlockPos;)I");
    }

    public BlockEntity getBlockEntity(BlockPos pos, LevelChunk.EntityCreationType creationType) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.getBlockEntity:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/chunk/LevelChunk$EntityCreationType;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public void addAndRegisterBlockEntity(BlockEntity blockEntity) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.addAndRegisterBlockEntity:(Lnet/minecraft/world/level/block/entity/BlockEntity;)V");
    }

    public void setBlockEntity(BlockEntity blockEntity) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.setBlockEntity:(Lnet/minecraft/world/level/block/entity/BlockEntity;)V");
    }

    public void removeBlockEntity(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.removeBlockEntity:(Lnet/minecraft/core/BlockPos;)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.isEmpty:()Z");
    }

    public boolean isYSpaceEmpty(int yStartInclusive, int yEndInclusive) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.isYSpaceEmpty:(II)Z");
    }

    public FullChunkStatus getFullStatus() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.getFullStatus:()Lnet/minecraft/server/level/FullChunkStatus;");
    }

    public Holder<Biome> getNoiseBiome(int quartX, int quartY, int quartZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/EmptyLevelChunk.getNoiseBiome:(III)Lnet/minecraft/core/Holder;");
    }

    public EmptyLevelChunk() {
    }
}
