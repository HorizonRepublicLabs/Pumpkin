package net.minecraft.world.level.chunk;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import dev.pumpkin.shim.Unimplemented;

public abstract class ChunkGenerator {

    public ChunkGenerator(BiomeSource biomeSource) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkGenerator.<init>:(Lnet/minecraft/world/level/biome/BiomeSource;)V");
    }

    public ChunkGenerator(BiomeSource biomeSource, Function<Holder<Biome>, BiomeGenerationSettings> generationSettingsGetter) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/ChunkGenerator.<init>:(Lnet/minecraft/world/level/biome/BiomeSource;Ljava/util/function/Function;)V");
    }

    protected abstract MapCodec<? extends ChunkGenerator> codec();

    public abstract void applyCarvers(WorldGenRegion region, long seed, final RandomState randomState, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunk);

    public abstract void buildSurface(final WorldGenRegion level, final StructureManager structureManager, final RandomState randomState, ChunkAccess protoChunk);

    public abstract void spawnOriginalMobs(WorldGenRegion worldGenRegion);

    public abstract int getGenDepth();

    public abstract CompletableFuture<ChunkAccess> fillFromNoise(final Blender blender, final RandomState randomState, final StructureManager structureManager, final ChunkAccess centerChunk);

    public abstract int getSeaLevel();

    public abstract int getMinY();

    public abstract int getBaseHeight(int x, int z, final Heightmap.Types type, final LevelHeightAccessor heightAccessor, final RandomState randomState);

    public abstract NoiseColumn getBaseColumn(final int x, final int z, final LevelHeightAccessor heightAccessor, final RandomState randomState);

    public abstract void addDebugScreenInfo(final List<String> result, final RandomState randomState, final BlockPos feetPos);

    protected ChunkGenerator() {
    }
}
