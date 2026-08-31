package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.attribute.EnvironmentAttributeReader;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.common.extensions.ILevelReaderExtension;
import dev.pumpkin.shim.Unimplemented;

public interface LevelReader extends BlockAndLightGetter, CollisionGetter, SignalGetter, BiomeManager.NoiseBiomeSource, ILevelReaderExtension {

    ChunkAccess getChunk(final int chunkX, final int chunkZ, final ChunkStatus targetStatus, final boolean loadOrGenerate);

    boolean hasChunk(int chunkX, int chunkZ);

    int getHeight(Heightmap.Types type, int x, int z);

    default int getHeight(Heightmap.Types type, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.getHeight:(Lnet/minecraft/world/level/levelgen/Heightmap$Types;Lnet/minecraft/core/BlockPos;)I");
    }

    int getSkyDarken();

    BiomeManager getBiomeManager();

    default Holder<Biome> getBiome(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.getBiome:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/Holder;");
    }

    default Holder<Biome> getNoiseBiome(int quartX, int quartY, int quartZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.getNoiseBiome:(III)Lnet/minecraft/core/Holder;");
    }

    Holder<Biome> getUncachedNoiseBiome(int quartX, int quartY, int quartZ);

    boolean isClientSide();

    int getSeaLevel();

    DimensionType dimensionType();

    default int getMinY() {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.getMinY:()I");
    }

    default int getHeight() {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.getHeight:()I");
    }

    default BlockPos getHeightmapPos(Heightmap.Types type, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.getHeightmapPos:(Lnet/minecraft/world/level/levelgen/Heightmap$Types;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/BlockPos;");
    }

    default boolean isEmptyBlock(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.isEmptyBlock:(Lnet/minecraft/core/BlockPos;)Z");
    }

    default ChunkAccess getChunk(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.getChunk:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/chunk/ChunkAccess;");
    }

    default ChunkAccess getChunk(int chunkX, int chunkZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.getChunk:(II)Lnet/minecraft/world/level/chunk/ChunkAccess;");
    }

    default ChunkAccess getChunk(int chunkX, int chunkZ, ChunkStatus status) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.getChunk:(IILnet/minecraft/world/level/chunk/status/ChunkStatus;)Lnet/minecraft/world/level/chunk/ChunkAccess;");
    }

    default BlockGetter getChunkForCollisions(int chunkX, int chunkZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.getChunkForCollisions:(II)Lnet/minecraft/world/level/BlockGetter;");
    }

    default boolean hasChunksAt(BlockPos pos0, BlockPos pos1) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.hasChunksAt:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Z");
    }

    default boolean hasChunksAt(int x0, int y0, int z0, int x1, int y1, int z1) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.hasChunksAt:(IIIIII)Z");
    }

    default boolean hasChunksAt(int x0, int z0, int x1, int z1) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelReader.hasChunksAt:(IIII)Z");
    }

    RegistryAccess registryAccess();

    FeatureFlagSet enabledFeatures();

    EnvironmentAttributeReader environmentAttributes();
}
