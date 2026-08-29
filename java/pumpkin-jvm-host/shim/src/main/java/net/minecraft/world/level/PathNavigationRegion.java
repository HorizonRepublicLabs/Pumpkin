package net.minecraft.world.level;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public class PathNavigationRegion implements CollisionGetter {

    public PathNavigationRegion(Level level, BlockPos start, BlockPos end) {
    }

    private ChunkAccess getChunk(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/PathNavigationRegion.getChunk:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/chunk/ChunkAccess;");
    }

    public WorldBorder getWorldBorder() {
        throw Unimplemented.forMember("net/minecraft/world/level/PathNavigationRegion.getWorldBorder:()Lnet/minecraft/world/level/border/WorldBorder;");
    }

    public BlockGetter getChunkForCollisions(int chunkX, int chunkZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/PathNavigationRegion.getChunkForCollisions:(II)Lnet/minecraft/world/level/BlockGetter;");
    }

    public List<VoxelShape> getEntityCollisions(Entity source, AABB testArea) {
        throw Unimplemented.forMember("net/minecraft/world/level/PathNavigationRegion.getEntityCollisions:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;");
    }

    public BlockEntity getBlockEntity(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/PathNavigationRegion.getBlockEntity:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/PathNavigationRegion.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public FluidState getFluidState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/PathNavigationRegion.getFluidState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;");
    }

    public int getMinY() {
        throw Unimplemented.forMember("net/minecraft/world/level/PathNavigationRegion.getMinY:()I");
    }

    public int getHeight() {
        throw Unimplemented.forMember("net/minecraft/world/level/PathNavigationRegion.getHeight:()I");
    }

    public PathNavigationRegion() {
    }
}
