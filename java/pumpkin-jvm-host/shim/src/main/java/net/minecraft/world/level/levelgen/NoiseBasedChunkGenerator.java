package net.minecraft.world.level.levelgen;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blending.Blender;
import dev.pumpkin.shim.Unimplemented;

public class NoiseBasedChunkGenerator extends ChunkGenerator {

    public NoiseBasedChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> settings) {
    }

    public CompletableFuture<ChunkAccess> createBiomes(RandomState randomState, Blender blender, StructureManager structureManager, ChunkAccess protoChunk) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.createBiomes:(Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/levelgen/blending/Blender;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/chunk/ChunkAccess;)Ljava/util/concurrent/CompletableFuture;");
    }

    protected MapCodec<? extends ChunkGenerator> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public int getBaseHeight(int x, int z, Heightmap.Types type, LevelHeightAccessor heightAccessor, RandomState randomState) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.getBaseHeight:(IILnet/minecraft/world/level/levelgen/Heightmap$Types;Lnet/minecraft/world/level/LevelHeightAccessor;Lnet/minecraft/world/level/levelgen/RandomState;)I");
    }

    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor heightAccessor, RandomState randomState) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.getBaseColumn:(IILnet/minecraft/world/level/LevelHeightAccessor;Lnet/minecraft/world/level/levelgen/RandomState;)Lnet/minecraft/world/level/NoiseColumn;");
    }

    public void addDebugScreenInfo(List<String> result, RandomState randomState, BlockPos feetPos) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.addDebugScreenInfo:(Ljava/util/List;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/core/BlockPos;)V");
    }

    public void buildSurface(WorldGenRegion region, StructureManager structureManager, RandomState randomState, ChunkAccess protoChunk) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.buildSurface:(Lnet/minecraft/server/level/WorldGenRegion;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/chunk/ChunkAccess;)V");
    }

    public void applyCarvers(WorldGenRegion region, long seed, RandomState randomState, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunk) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.applyCarvers:(Lnet/minecraft/server/level/WorldGenRegion;JLnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/biome/BiomeManager;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/chunk/ChunkAccess;)V");
    }

    public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess centerChunk) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.fillFromNoise:(Lnet/minecraft/world/level/levelgen/blending/Blender;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/chunk/ChunkAccess;)Ljava/util/concurrent/CompletableFuture;");
    }

    public int getGenDepth() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.getGenDepth:()I");
    }

    public int getSeaLevel() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.getSeaLevel:()I");
    }

    public int getMinY() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.getMinY:()I");
    }

    public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseBasedChunkGenerator.spawnOriginalMobs:(Lnet/minecraft/server/level/WorldGenRegion;)V");
    }

    public NoiseBasedChunkGenerator() {
    }
}
