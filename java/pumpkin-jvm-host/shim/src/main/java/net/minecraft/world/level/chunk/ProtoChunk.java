package net.minecraft.world.level.chunk;

import it.unimi.dsi.fastutil.shorts.ShortList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.BelowZeroRetrogen;
import net.minecraft.world.level.levelgen.blending.BlendingData;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.ticks.ProtoChunkTicks;
import net.minecraft.world.ticks.TickContainerAccess;
import dev.pumpkin.shim.Unimplemented;

public class ProtoChunk extends ChunkAccess {

    public ProtoChunk(ChunkPos chunkPos, UpgradeData upgradeData, LevelHeightAccessor levelHeightAccessor, PalettedContainerFactory containerFactory, BlendingData blendingData) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.<init>:(Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/world/level/chunk/UpgradeData;Lnet/minecraft/world/level/LevelHeightAccessor;Lnet/minecraft/world/level/chunk/PalettedContainerFactory;Lnet/minecraft/world/level/levelgen/blending/BlendingData;)V");
    }

    public ProtoChunk(ChunkPos chunkPos, UpgradeData upgradeData, LevelChunkSection[] sections, ProtoChunkTicks<Block> blockTicks, ProtoChunkTicks<Fluid> fluidTicks, LevelHeightAccessor levelHeightAccessor, PalettedContainerFactory containerFactory, BlendingData blendingData) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.<init>:(Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/world/level/chunk/UpgradeData;[Lnet/minecraft/world/level/chunk/LevelChunkSection;Lnet/minecraft/world/ticks/ProtoChunkTicks;Lnet/minecraft/world/ticks/ProtoChunkTicks;Lnet/minecraft/world/level/LevelHeightAccessor;Lnet/minecraft/world/level/chunk/PalettedContainerFactory;Lnet/minecraft/world/level/levelgen/blending/BlendingData;)V");
    }

    public TickContainerAccess<Block> getBlockTicks() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getBlockTicks:()Lnet/minecraft/world/ticks/TickContainerAccess;");
    }

    public TickContainerAccess<Fluid> getFluidTicks() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getFluidTicks:()Lnet/minecraft/world/ticks/TickContainerAccess;");
    }

    public ChunkAccess.PackedTicks getTicksForSerialization(long currentTick) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getTicksForSerialization:(J)Lnet/minecraft/world/level/chunk/ChunkAccess$PackedTicks;");
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public FluidState getFluidState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getFluidState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;");
    }

    public BlockState setBlockState(BlockPos pos, BlockState state, int flags) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.setBlockState:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public void setBlockEntity(BlockEntity blockEntity) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.setBlockEntity:(Lnet/minecraft/world/level/block/entity/BlockEntity;)V");
    }

    public BlockEntity getBlockEntity(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getBlockEntity:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public void addEntity(CompoundTag tag) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.addEntity:(Lnet/minecraft/nbt/CompoundTag;)V");
    }

    public void addEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.addEntity:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void setStartForStructure(Structure structure, StructureStart structureStart) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.setStartForStructure:(Lnet/minecraft/world/level/levelgen/structure/Structure;Lnet/minecraft/world/level/levelgen/structure/StructureStart;)V");
    }

    public List<CompoundTag> getEntities() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getEntities:()Ljava/util/List;");
    }

    public ChunkStatus getPersistedStatus() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getPersistedStatus:()Lnet/minecraft/world/level/chunk/status/ChunkStatus;");
    }

    public Holder<Biome> getNoiseBiome(int quartX, int quartY, int quartZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getNoiseBiome:(III)Lnet/minecraft/core/Holder;");
    }

    public void markPosForPostProcessing(BlockPos blockPos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.markPosForPostProcessing:(Lnet/minecraft/core/BlockPos;)V");
    }

    public void addPackedPostProcess(ShortList packedOffsets, int sectionIndex) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.addPackedPostProcess:(Lit/unimi/dsi/fastutil/shorts/ShortList;I)V");
    }

    public CompoundTag getBlockEntityNbtForSaving(BlockPos blockPos, HolderLookup.Provider registryAccess) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getBlockEntityNbtForSaving:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public void removeBlockEntity(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.removeBlockEntity:(Lnet/minecraft/core/BlockPos;)V");
    }

    public BelowZeroRetrogen getBelowZeroRetrogen() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getBelowZeroRetrogen:()Lnet/minecraft/world/level/levelgen/BelowZeroRetrogen;");
    }

    public LevelHeightAccessor getHeightAccessorForGeneration() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ProtoChunk.getHeightAccessorForGeneration:()Lnet/minecraft/world/level/LevelHeightAccessor;");
    }

    protected ProtoChunk() {
    }
}
