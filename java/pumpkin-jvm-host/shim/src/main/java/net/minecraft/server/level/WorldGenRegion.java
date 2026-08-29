package net.minecraft.server.level;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StaticCache2D;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.attribute.EnvironmentAttributeReader;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.chunk.status.ChunkStep;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.LevelTickAccess;
import dev.pumpkin.shim.Unimplemented;

public class WorldGenRegion implements WorldGenLevel {

    public WorldGenRegion(ServerLevel level, StaticCache2D<GenerationChunkHolder> cache, ChunkStep generatingStep, ChunkAccess center) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.<init>:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/StaticCache2D;Lnet/minecraft/world/level/chunk/status/ChunkStep;Lnet/minecraft/world/level/chunk/ChunkAccess;)V");
    }

    public void setCurrentlyGenerating(Supplier<String> currentlyGenerating) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.setCurrentlyGenerating:(Ljava/util/function/Supplier;)V");
    }

    public ChunkAccess getChunk(int chunkX, int chunkZ) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getChunk:(II)Lnet/minecraft/world/level/chunk/ChunkAccess;");
    }

    public ChunkAccess getChunk(int chunkX, int chunkZ, ChunkStatus targetStatus, boolean loadOrGenerate) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getChunk:(IILnet/minecraft/world/level/chunk/status/ChunkStatus;Z)Lnet/minecraft/world/level/chunk/ChunkAccess;");
    }

    public boolean hasChunk(int chunkX, int chunkZ) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.hasChunk:(II)Z");
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public FluidState getFluidState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getFluidState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;");
    }

    public Player getNearestPlayer(double x, double y, double z, double maxDist, Predicate<Entity> predicate) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getNearestPlayer:(DDDDLjava/util/function/Predicate;)Lnet/minecraft/world/entity/player/Player;");
    }

    public int getSkyDarken() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getSkyDarken:()I");
    }

    public BiomeManager getBiomeManager() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getBiomeManager:()Lnet/minecraft/world/level/biome/BiomeManager;");
    }

    public Holder<Biome> getUncachedNoiseBiome(int quartX, int quartY, int quartZ) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getUncachedNoiseBiome:(III)Lnet/minecraft/core/Holder;");
    }

    public LevelLightEngine getLightEngine() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getLightEngine:()Lnet/minecraft/world/level/lighting/LevelLightEngine;");
    }

    public boolean destroyBlock(BlockPos pos, boolean dropResources, Entity breaker, int updateLimit) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.destroyBlock:(Lnet/minecraft/core/BlockPos;ZLnet/minecraft/world/entity/Entity;I)Z");
    }

    public BlockEntity getBlockEntity(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getBlockEntity:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public boolean ensureCanWrite(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.ensureCanWrite:(Lnet/minecraft/core/BlockPos;)Z");
    }

    public boolean setBlock(BlockPos pos, BlockState blockState, int updateFlags, int updateLimit) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.setBlock:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;II)Z");
    }

    public boolean addFreshEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.addFreshEntity:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public boolean removeBlock(BlockPos pos, boolean movedByPiston) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.removeBlock:(Lnet/minecraft/core/BlockPos;Z)Z");
    }

    public WorldBorder getWorldBorder() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getWorldBorder:()Lnet/minecraft/world/level/border/WorldBorder;");
    }

    public boolean isClientSide() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.isClientSide:()Z");
    }

    public ServerLevel getLevel() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public RegistryAccess registryAccess() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.registryAccess:()Lnet/minecraft/core/RegistryAccess;");
    }

    public FeatureFlagSet enabledFeatures() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.enabledFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public LevelData getLevelData() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getLevelData:()Lnet/minecraft/world/level/storage/LevelData;");
    }

    public DifficultyInstance getCurrentDifficultyAt(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getCurrentDifficultyAt:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/DifficultyInstance;");
    }

    public MinecraftServer getServer() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getServer:()Lnet/minecraft/server/MinecraftServer;");
    }

    public ChunkSource getChunkSource() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getChunkSource:()Lnet/minecraft/world/level/chunk/ChunkSource;");
    }

    public long getSeed() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getSeed:()J");
    }

    public LevelTickAccess<Block> getBlockTicks() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getBlockTicks:()Lnet/minecraft/world/ticks/LevelTickAccess;");
    }

    public LevelTickAccess<Fluid> getFluidTicks() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getFluidTicks:()Lnet/minecraft/world/ticks/LevelTickAccess;");
    }

    public int getSeaLevel() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getSeaLevel:()I");
    }

    public RandomSource getRandom() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getRandom:()Lnet/minecraft/util/RandomSource;");
    }

    public int getHeight(Heightmap.Types type, int x, int z) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getHeight:(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I");
    }

    public void playSound(Entity except, BlockPos pos, SoundEvent sound, SoundSource source, float volume, float pitch) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.playSound:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V");
    }

    public void addParticle(ParticleOptions particle, double x, double y, double z, double xd, double yd, double zd) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.addParticle:(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V");
    }

    public void levelEvent(Entity source, int type, BlockPos pos, int data) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.levelEvent:(Lnet/minecraft/world/entity/Entity;ILnet/minecraft/core/BlockPos;I)V");
    }

    public void gameEvent(Holder<GameEvent> gameEvent, Vec3 position, GameEvent.Context context) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.gameEvent:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/level/gameevent/GameEvent$Context;)V");
    }

    public DimensionType dimensionType() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.dimensionType:()Lnet/minecraft/world/level/dimension/DimensionType;");
    }

    public boolean isStateAtPosition(BlockPos pos, Predicate<BlockState> predicate) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.isStateAtPosition:(Lnet/minecraft/core/BlockPos;Ljava/util/function/Predicate;)Z");
    }

    public boolean isFluidAtPosition(BlockPos pos, Predicate<FluidState> predicate) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.isFluidAtPosition:(Lnet/minecraft/core/BlockPos;Ljava/util/function/Predicate;)Z");
    }

    public <T extends Entity> List<T> getEntities(EntityTypeTest<Entity, T> type, AABB bb, Predicate<? super T> selector) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getEntities:(Lnet/minecraft/world/level/entity/EntityTypeTest;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;");
    }

    public List<Entity> getEntities(Entity except, AABB bb, Predicate<? super Entity> selector) {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getEntities:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;");
    }

    public List<Player> players() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.players:()Ljava/util/List;");
    }

    public int getMinY() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getMinY:()I");
    }

    public int getHeight() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.getHeight:()I");
    }

    public long nextSubTickCount() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.nextSubTickCount:()J");
    }

    public EnvironmentAttributeReader environmentAttributes() {
        throw Unimplemented.forMember("net/minecraft/server/level/WorldGenRegion.environmentAttributes:()Lnet/minecraft/world/attribute/EnvironmentAttributeReader;");
    }

    protected WorldGenRegion() {
    }
}
