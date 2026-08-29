package net.minecraft.world.level;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.clock.ClockManager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.crafting.RecipeAccess;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.WritableLevelData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.scores.Scoreboard;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public abstract class Level extends net.neoforged.neoforge.attachment.AttachmentHolder implements LevelAccessor, AutoCloseable, ILevelExtension {

    private final boolean isClientSide = false;

    private final RegistryAccess registryAccess = Stubs.of(RegistryAccess.class, "net/minecraft/core/RegistryAccess");

    private final DamageSources damageSources = null;

    protected Level(WritableLevelData levelData, ResourceKey<Level> dimension, RegistryAccess registryAccess, Holder<DimensionType> dimensionTypeRegistration, boolean isClientSide, boolean isDebug, long biomeZoomSeed, int maxChainedNeighborUpdates) {
    }

    public boolean isClientSide() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.isClientSide:()Z");
    }

    public MinecraftServer getServer() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getServer:()Lnet/minecraft/server/MinecraftServer;");
    }

    public ChunkAccess getChunk(int chunkX, int chunkZ, ChunkStatus status, boolean loadOrGenerate) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getChunk:(IILnet/minecraft/world/level/chunk/status/ChunkStatus;Z)Lnet/minecraft/world/level/chunk/ChunkAccess;");
    }

    public boolean setBlock(BlockPos pos, BlockState blockState, int updateFlags) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.setBlock:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z");
    }

    public boolean setBlock(BlockPos pos, BlockState blockState, int updateFlags, int updateLimit) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.setBlock:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;II)Z");
    }

    public boolean removeBlock(BlockPos pos, boolean movedByPiston) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.removeBlock:(Lnet/minecraft/core/BlockPos;Z)Z");
    }

    public boolean destroyBlock(BlockPos pos, boolean dropResources, Entity breaker, int updateLimit) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.destroyBlock:(Lnet/minecraft/core/BlockPos;ZLnet/minecraft/world/entity/Entity;I)Z");
    }

    public boolean setBlockAndUpdate(BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.setBlockAndUpdate:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    public abstract void sendBlockUpdated(BlockPos pos, BlockState old, BlockState current, int updateFlags);

    public void neighborShapeChanged(Direction direction, BlockPos pos, BlockPos neighborPos, BlockState neighborState, int updateFlags, int updateLimit) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.neighborShapeChanged:(Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;II)V");
    }

    public int getHeight(Heightmap.Types type, int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getHeight:(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I");
    }

    public LevelLightEngine getLightEngine() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getLightEngine:()Lnet/minecraft/world/level/lighting/LevelLightEngine;");
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public FluidState getFluidState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getFluidState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;");
    }

    public void playSound(Entity except, BlockPos pos, SoundEvent sound, SoundSource source, float volume, float pitch) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.playSound:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V");
    }

    public abstract void playSeededSound(final Entity except, final double x, final double y, final double z, final Holder<SoundEvent> sound, final SoundSource source, final float volume, final float pitch, final long seed);

    public void playSeededSound(Entity except, double x, double y, double z, SoundEvent sound, SoundSource source, float volume, float pitch, long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.playSeededSound:(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFJ)V");
    }

    public abstract void playSeededSound(final Entity except, final Entity sourceEntity, final Holder<SoundEvent> sound, final SoundSource source, final float volume, final float pitch, final long seed);

    public void playSound(Entity except, double x, double y, double z, SoundEvent sound, SoundSource source) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.playSound:(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;)V");
    }

    public void playSound(Entity except, double x, double y, double z, SoundEvent sound, SoundSource source, float volume, float pitch) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.playSound:(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V");
    }

    public void playSound(Entity except, double x, double y, double z, Holder<SoundEvent> sound, SoundSource source, float volume, float pitch) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.playSound:(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;FF)V");
    }

    public void playSound(Entity except, Entity sourceEntity, SoundEvent sound, SoundSource source, float volume, float pitch) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.playSound:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V");
    }

    public void addParticle(ParticleOptions particle, double x, double y, double z, double xd, double yd, double zd) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.addParticle:(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V");
    }

    public void addParticle(ParticleOptions particle, boolean overrideLimiter, boolean alwaysShow, double x, double y, double z, double xd, double yd, double zd) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.addParticle:(Lnet/minecraft/core/particles/ParticleOptions;ZZDDDDDD)V");
    }

    public abstract void explode(final Entity source, final DamageSource damageSource, final ExplosionDamageCalculator damageCalculator, final double x, final double y, final double z, final float r, final boolean fire, final Level.ExplosionInteraction interactionType, final ParticleOptions smallExplosionParticles, final ParticleOptions largeExplosionParticles, final WeightedList<ExplosionParticleInfo> blockParticles, final Holder<SoundEvent> explosionSound);

    public abstract String gatherChunkSourceStats();

    public BlockEntity getBlockEntity(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getBlockEntity:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public void setBlockEntity(BlockEntity blockEntity) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.setBlockEntity:(Lnet/minecraft/world/level/block/entity/BlockEntity;)V");
    }

    public void removeBlockEntity(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.removeBlockEntity:(Lnet/minecraft/core/BlockPos;)V");
    }

    public abstract void setRespawnData(final LevelData.RespawnData respawnData);

    public abstract LevelData.RespawnData getRespawnData();

    public void close() throws IOException {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.close:()V");
    }

    public BlockGetter getChunkForCollisions(int chunkX, int chunkZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getChunkForCollisions:(II)Lnet/minecraft/world/level/BlockGetter;");
    }

    public List<Entity> getEntities(Entity except, AABB bb, Predicate<? super Entity> selector) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getEntities:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;");
    }

    public <T extends Entity> List<T> getEntities(EntityTypeTest<Entity, T> type, AABB bb, Predicate<? super T> selector) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getEntities:(Lnet/minecraft/world/level/entity/EntityTypeTest;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;");
    }

    public abstract Entity getEntity(int id);

    public Entity getEntity(UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getEntity:(Ljava/util/UUID;)Lnet/minecraft/world/entity/Entity;");
    }

    public abstract Collection<? extends net.neoforged.neoforge.entity.PartEntity<?>> dragonParts();

    public void blockEntityChanged(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.blockEntityChanged:(Lnet/minecraft/core/BlockPos;)V");
    }

    public boolean mayInteract(Entity entity, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.mayInteract:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;)Z");
    }

    public LevelData getLevelData() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getLevelData:()Lnet/minecraft/world/level/storage/LevelData;");
    }

    public abstract TickRateManager tickRateManager();

    public boolean isRainingAt(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.isRainingAt:(Lnet/minecraft/core/BlockPos;)Z");
    }

    public abstract MapItemSavedData getMapData(MapId id);

    public abstract void destroyBlockProgress(final int id, final BlockPos blockPos, final int progress);

    public abstract Scoreboard getScoreboard();

    public int getSkyDarken() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getSkyDarken:()I");
    }

    public DimensionType dimensionType() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.dimensionType:()Lnet/minecraft/world/level/dimension/DimensionType;");
    }

    public RandomSource getRandom() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getRandom:()Lnet/minecraft/util/RandomSource;");
    }

    public boolean isStateAtPosition(BlockPos pos, Predicate<BlockState> predicate) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.isStateAtPosition:(Lnet/minecraft/core/BlockPos;Ljava/util/function/Predicate;)Z");
    }

    public boolean isFluidAtPosition(BlockPos pos, Predicate<FluidState> predicate) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.isFluidAtPosition:(Lnet/minecraft/core/BlockPos;Ljava/util/function/Predicate;)Z");
    }

    public abstract RecipeAccess recipeAccess();

    public BiomeManager getBiomeManager() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getBiomeManager:()Lnet/minecraft/world/level/biome/BiomeManager;");
    }

    public double getMaxEntityRadius() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getMaxEntityRadius:()D");
    }

    public double increaseMaxEntityRadius(double value) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.increaseMaxEntityRadius:(D)D");
    }

    protected abstract LevelEntityGetter<Entity> getEntities();

    public long nextSubTickCount() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.nextSubTickCount:()J");
    }

    public RegistryAccess registryAccess() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.registryAccess:()Lnet/minecraft/core/RegistryAccess;");
    }

    public DamageSources damageSources() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.damageSources:()Lnet/minecraft/world/damagesource/DamageSources;");
    }

    public abstract ClockManager clockManager();

    public abstract EnvironmentAttributeSystem environmentAttributes();

    public abstract PotionBrewing potionBrewing();

    public abstract FuelValues fuelValues();

    public enum ExplosionInteraction implements StringRepresentable {

        NONE, BLOCK, MOB, TNT, TRIGGER;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/Level$ExplosionInteraction.getSerializedName:()Ljava/lang/String;");
        }
    }

    public Level() {
    }
}
