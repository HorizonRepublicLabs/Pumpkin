package net.minecraft.world.level.chunk;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.debug.DebugValueSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.gameevent.GameEventListenerRegistry;
import net.minecraft.world.level.levelgen.blending.BlendingData;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.ticks.LevelChunkTicks;
import net.minecraft.world.ticks.TickContainerAccess;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import dev.pumpkin.shim.Unimplemented;

public class LevelChunk extends ChunkAccess implements DebugValueSource, IAttachmentHolder {

    public LevelChunk(Level level, ChunkPos pos) {
    }

    public LevelChunk(Level level, ChunkPos pos, UpgradeData upgradeData, LevelChunkTicks<Block> blockTicks, LevelChunkTicks<Fluid> fluidTicks, long inhabitedTime, LevelChunkSection[] sections, LevelChunk.PostLoadProcessor postLoad, BlendingData blendingData) {
    }

    public LevelChunk(ServerLevel level, ProtoChunk protoChunk, LevelChunk.PostLoadProcessor postLoad) {
    }

    public void markUnsaved() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.markUnsaved:()V");
    }

    public TickContainerAccess<Block> getBlockTicks() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getBlockTicks:()Lnet/minecraft/world/ticks/TickContainerAccess;");
    }

    public TickContainerAccess<Fluid> getFluidTicks() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getFluidTicks:()Lnet/minecraft/world/ticks/TickContainerAccess;");
    }

    public ChunkAccess.PackedTicks getTicksForSerialization(long currentTick) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getTicksForSerialization:(J)Lnet/minecraft/world/level/chunk/ChunkAccess$PackedTicks;");
    }

    public GameEventListenerRegistry getListenerRegistry(int section) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getListenerRegistry:(I)Lnet/minecraft/world/level/gameevent/GameEventListenerRegistry;");
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public FluidState getFluidState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getFluidState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;");
    }

    public BlockState setBlockState(BlockPos pos, BlockState state, int flags) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.setBlockState:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public void addEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.addEntity:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public BlockEntity getBlockEntity(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getBlockEntity:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public BlockEntity getBlockEntity(BlockPos pos, LevelChunk.EntityCreationType creationType) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getBlockEntity:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/chunk/LevelChunk$EntityCreationType;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public void setBlockEntity(BlockEntity blockEntity) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.setBlockEntity:(Lnet/minecraft/world/level/block/entity/BlockEntity;)V");
    }

    public CompoundTag getBlockEntityNbtForSaving(BlockPos blockPos, HolderLookup.Provider registryAccess) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getBlockEntityNbtForSaving:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public void removeBlockEntity(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.removeBlockEntity:(Lnet/minecraft/core/BlockPos;)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.isEmpty:()Z");
    }

    public Level getLevel() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getLevel:()Lnet/minecraft/world/level/Level;");
    }

    public void registerDebugValues(ServerLevel level, DebugValueSource.Registration registration) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.registerDebugValues:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/debug/DebugValueSource$Registration;)V");
    }

    public ChunkStatus getPersistedStatus() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getPersistedStatus:()Lnet/minecraft/world/level/chunk/status/ChunkStatus;");
    }

    public net.neoforged.neoforge.common.world.LevelChunkAuxiliaryLightManager getAuxLightManager(ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.getAuxLightManager:(Lnet/minecraft/world/level/ChunkPos;)Lnet/neoforged/neoforge/common/world/LevelChunkAuxiliaryLightManager;");
    }

    public final void syncData(net.neoforged.neoforge.attachment.AttachmentType<?> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.syncData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)V");
    }

    private class BoundTickingBlockEntity<T extends BlockEntity> implements TickingBlockEntity {

        private BoundTickingBlockEntity(T blockEntity, BlockEntityTicker<T> ticker) {
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk$BoundTickingBlockEntity.tick:()V");
        }

        public boolean isRemoved() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk$BoundTickingBlockEntity.isRemoved:()Z");
        }

        public BlockPos getPos() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk$BoundTickingBlockEntity.getPos:()Lnet/minecraft/core/BlockPos;");
        }

        public String getType() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk$BoundTickingBlockEntity.getType:()Ljava/lang/String;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk$BoundTickingBlockEntity.toString:()Ljava/lang/String;");
        }

        protected BoundTickingBlockEntity() {
        }
    }

    public enum EntityCreationType {

        IMMEDIATE, QUEUED, CHECK
    }

    public interface PostLoadProcessor {

        void run(LevelChunk levelChunk);
    }

    private static class RebindableTickingBlockEntityWrapper implements TickingBlockEntity {

        private RebindableTickingBlockEntityWrapper(TickingBlockEntity ticker) {
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk$RebindableTickingBlockEntityWrapper.tick:()V");
        }

        public boolean isRemoved() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk$RebindableTickingBlockEntityWrapper.isRemoved:()Z");
        }

        public BlockPos getPos() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk$RebindableTickingBlockEntityWrapper.getPos:()Lnet/minecraft/core/BlockPos;");
        }

        public String getType() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk$RebindableTickingBlockEntityWrapper.getType:()Ljava/lang/String;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk$RebindableTickingBlockEntityWrapper.toString:()Ljava/lang/String;");
        }

        protected RebindableTickingBlockEntityWrapper() {
        }
    }

    public interface UnsavedListener {

        void setUnsaved(ChunkPos chunkPos);
    }

    public LevelChunk() {
    }
}
