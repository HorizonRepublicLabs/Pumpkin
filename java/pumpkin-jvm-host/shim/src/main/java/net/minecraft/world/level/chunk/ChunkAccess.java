package net.minecraft.world.level.chunk;

import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blending.BlendingData;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.lighting.ChunkSkyLightSources;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.ticks.SavedTick;
import net.minecraft.world.ticks.TickContainerAccess;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import dev.pumpkin.shim.Unimplemented;

public abstract class ChunkAccess implements LightChunk, StructureAccess, BiomeManager.NoiseBiomeSource, IAttachmentHolder {

    public ChunkAccess(ChunkPos chunkPos, UpgradeData upgradeData, LevelHeightAccessor levelHeightAccessor, PalettedContainerFactory containerFactory, long inhabitedTime, LevelChunkSection[] sections, BlendingData blendingData) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.<init>:(Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/world/level/chunk/UpgradeData;Lnet/minecraft/world/level/LevelHeightAccessor;Lnet/minecraft/world/level/chunk/PalettedContainerFactory;J[Lnet/minecraft/world/level/chunk/LevelChunkSection;Lnet/minecraft/world/level/levelgen/blending/BlendingData;)V");
    }

    public abstract BlockState setBlockState(BlockPos pos, BlockState state, int flags);

    public abstract void setBlockEntity(BlockEntity blockEntity);

    public abstract void addEntity(Entity entity);

    public LevelChunkSection getSection(int sectionIndex) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getSection:(I)Lnet/minecraft/world/level/chunk/LevelChunkSection;");
    }

    public int getHeight(Heightmap.Types type, int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getHeight:(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I");
    }

    public ChunkPos getPos() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getPos:()Lnet/minecraft/world/level/ChunkPos;");
    }

    public StructureStart getStartForStructure(Structure structure) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getStartForStructure:(Lnet/minecraft/world/level/levelgen/structure/Structure;)Lnet/minecraft/world/level/levelgen/structure/StructureStart;");
    }

    public void setStartForStructure(Structure structure, StructureStart structureStart) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.setStartForStructure:(Lnet/minecraft/world/level/levelgen/structure/Structure;Lnet/minecraft/world/level/levelgen/structure/StructureStart;)V");
    }

    public LongSet getReferencesForStructure(Structure structure) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getReferencesForStructure:(Lnet/minecraft/world/level/levelgen/structure/Structure;)Lit/unimi/dsi/fastutil/longs/LongSet;");
    }

    public void addReferenceForStructure(Structure structure, long reference) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.addReferenceForStructure:(Lnet/minecraft/world/level/levelgen/structure/Structure;J)V");
    }

    public Map<Structure, LongSet> getAllReferences() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getAllReferences:()Ljava/util/Map;");
    }

    public void setAllReferences(Map<Structure, LongSet> data) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.setAllReferences:(Ljava/util/Map;)V");
    }

    public abstract ChunkStatus getPersistedStatus();

    public abstract void removeBlockEntity(BlockPos pos);

    public abstract CompoundTag getBlockEntityNbtForSaving(BlockPos blockPos, HolderLookup.Provider registryAccess);

    public final void findBlockLightSources(BiConsumer<BlockPos, BlockState> consumer) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.findBlockLightSources:(Ljava/util/function/BiConsumer;)V");
    }

    public abstract TickContainerAccess<Block> getBlockTicks();

    public abstract TickContainerAccess<Fluid> getFluidTicks();

    public abstract ChunkAccess.PackedTicks getTicksForSerialization(long currentTick);

    public int getMinY() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getMinY:()I");
    }

    public int getHeight() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getHeight:()I");
    }

    public Holder<Biome> getNoiseBiome(int quartX, int quartY, int quartZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getNoiseBiome:(III)Lnet/minecraft/core/Holder;");
    }

    public ChunkSkyLightSources getSkyLightSources() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getSkyLightSources:()Lnet/minecraft/world/level/lighting/ChunkSkyLightSources;");
    }

    public boolean hasAttachments() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.hasAttachments:()Z");
    }

    public boolean hasData(net.neoforged.neoforge.attachment.AttachmentType<?> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.hasData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)Z");
    }

    public <T> T getData(net.neoforged.neoforge.attachment.AttachmentType<T> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)Ljava/lang/Object;");
    }

    public <T> T getExistingDataOrNull(net.neoforged.neoforge.attachment.AttachmentType<T> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getExistingDataOrNull:(Lnet/neoforged/neoforge/attachment/AttachmentType;)Ljava/lang/Object;");
    }

    public <T> T setData(net.neoforged.neoforge.attachment.AttachmentType<T> type, T data) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.setData:(Lnet/neoforged/neoforge/attachment/AttachmentType;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public <T> T removeData(net.neoforged.neoforge.attachment.AttachmentType<T> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.removeData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)Ljava/lang/Object;");
    }

    public net.minecraft.world.level.Level getLevel() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess.getLevel:()Lnet/minecraft/world/level/Level;");
    }

    private record ChunkPathElement(ChunkPos pos) implements ProblemReporter.PathElement {

        public String get() {
            throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkAccess$ChunkPathElement.get:()Ljava/lang/String;");
        }
    }

    public record PackedTicks(List<SavedTick<Block>> blocks, List<SavedTick<Fluid>> fluids) {
    }

    protected ChunkAccess() {
    }
}
