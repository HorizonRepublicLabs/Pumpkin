package dev.pumpkin.bridge;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.attribute.EnvironmentAttributeReader;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.clock.ClockManager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.crafting.RecipeAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.WritableLevelData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.ticks.LevelTickAccess;
import net.minecraft.world.ticks.ScheduledTick;
import net.minecraft.world.ticks.TickPriority;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.common.extensions.IBlockGetterExtension;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.common.extensions.ILevelReaderExtension;
import dev.pumpkin.shim.Unimplemented;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.item.ItemStack;

/**
 * The {@code Level} a mod's interaction code runs against.
 *
 * <p>One instance per interaction, not per world: it exists to answer the handful of
 * questions a right-click handler asks -- which block entity is here, take this dropped
 * item -- and to refuse everything else with a key naming what to implement next. The
 * interaction bridge reads the recorded drops back out and hands them to the server.
 *
 * <p>Lives in the shim module but outside the generated roots, so a regeneration leaves
 * it alone; regen.sh's wipe list is the authority. The stub block below is generated from
 * the class's own abstract surface and looks it: every entry refuses with a key naming
 * the method, which is what the interaction burndown feeds on.
 */
public final class PumpkinLevel extends net.minecraft.server.level.ServerLevel {

    @Override
    public net.minecraft.world.item.crafting.RecipeManager recipeAccess() {
        // Cucumber's CachedRecipe casts the level to ServerLevel and asks this -- the
        // door recipe-driven machines walk through.
        return PumpkinRecipes.manager();
    }

    private final java.util.List<ItemStack> drops = new java.util.ArrayList<>();

    /** What {@code addFreshEntity} collected: items the mod dropped into the world. */
    public java.util.List<ItemStack> pumpkinDrops() {
        return drops;
    }

    @Override
    public java.util.List<net.minecraft.server.level.ServerPlayer> players() {
        // No one inhabits the stand-in level; packet fan-out asking is the sync slice.
        return java.util.List.of();
    }

    @Override
    public void blockEntityChanged(BlockPos pos) {
        // Accepted and dropped: the tick bridge already serialises a changed entity via
        // its own dirty flag; vanilla's chunk-save bookkeeping has nothing to mark here.
    }

    // The redstone signal at the block the bridge is currently serving, told by the
    // Rust world with each call. One interaction runs at a time on the mod thread, and
    // machines only ask about their own position.
    private static volatile boolean currentSignal;

    static void pumpkinSetSignal(boolean signal) {
        currentSignal = signal;
    }

    @Override
    public RandomSource getRandom() {
        // A real random over java.util.Random: particle offsets and the like want noise,
        // not determinism, and refusing them would stop machines over decoration.
        return PUMPKIN_RANDOM;
    }

    private static final RandomSource PUMPKIN_RANDOM = new RandomSource() {
        private final java.util.Random random = new java.util.Random();

        @Override
        public RandomSource fork() {
            return this;
        }

        @Override
        public net.minecraft.world.level.levelgen.PositionalRandomFactory forkPositional() {
            throw Unimplemented.forMember("net/minecraft/util/RandomSource.forkPositional");
        }

        @Override
        public void setSeed(long seed) {
            random.setSeed(seed);
        }

        @Override
        public int nextInt() {
            return random.nextInt();
        }

        @Override
        public int nextInt(int bound) {
            return random.nextInt(bound);
        }

        @Override
        public long nextLong() {
            return random.nextLong();
        }

        @Override
        public boolean nextBoolean() {
            return random.nextBoolean();
        }

        @Override
        public float nextFloat() {
            return random.nextFloat();
        }

        @Override
        public double nextDouble() {
            return random.nextDouble();
        }

        @Override
        public double nextGaussian() {
            return random.nextGaussian();
        }
    };

    @Override
    public <T extends ParticleOptions> int sendParticles(T particle, double x, double y,
            double z, int count, double dx, double dy, double dz, double speed) {
        // Accepted and dropped, like addParticle: decoration for the sync slice.
        return count;
    }

    @Override
    public boolean hasNeighborSignal(BlockPos pos) {
        return currentSignal;
    }

    @Override
    public BlockEntity getBlockEntity(BlockPos pos) {
        return PumpkinBlockEntities.get(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean addFreshEntity(Entity entity) {
        // A machine handing something back to the player spawns an ItemEntity; catching
        // it here is how the drop reaches the real world. Anything else is behaviour the
        // bridge does not carry yet.
        if (entity instanceof net.minecraft.world.entity.item.ItemEntity item
                && item.pumpkinStack() != null) {
            drops.add(item.pumpkinStack());
            return true;
        }
        throw Unimplemented.forMember(
                "net/minecraft/world/level/Level.addFreshEntity:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    @Override
    public void playSound(Entity except, BlockPos pos, SoundEvent sound, SoundSource source,
            float volume, float pitch) {
        // Accepted and dropped: routing mod sounds to nearby players is its own slice,
        // and stopping an interaction over a click sound helps no one.
    }

    @Override
    public BiomeManager getBiomeManager() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getBiomeManager");
    }

    @Override
    public BlockGetter getChunkForCollisions(int chunkX, int chunkZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getChunkForCollisions");
    }

    @Override
    public BlockPos getHeightmapPos(final Heightmap.Types type, final BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getHeightmapPos");
    }

    @Override
    public BlockState getBlockState(final BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getBlockState");
    }

    @Override
    public ChunkAccess getChunk(final int chunkX, final int chunkZ, final ChunkStatus targetStatus, final boolean loadOrGenerate) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getChunk");
    }

    @Override
    public DimensionType dimensionType() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.dimensionType");
    }

    @Override
    public Entity getEntity(int id) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getEntity");
    }


    @Override
    public FeatureFlagSet enabledFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.enabledFeatures");
    }

    @Override
    public FluidState getFluidState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getFluidState");
    }

    @Override
    public FuelValues fuelValues() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.fuelValues");
    }

    @Override
    public Holder<Biome> getUncachedNoiseBiome(int quartX, int quartY, int quartZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getUncachedNoiseBiome");
    }

    @Override
    public LevelData getLevelData() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getLevelData");
    }

    @Override
    public LevelData.RespawnData getRespawnData() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getRespawnData");
    }


    @Override
    public LevelLightEngine getLightEngine() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getLightEngine");
    }


    @Override
    public List<VoxelShape> getEntityCollisions(final Entity source, final AABB testArea) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getEntityCollisions");
    }

    @Override
    public MapItemSavedData getMapData(MapId id) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getMapData");
    }

    @Override
    public MinecraftServer getServer() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getServer");
    }

    @Override
    public PotionBrewing potionBrewing() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.potionBrewing");
    }

    @Override
    public RegistryAccess registryAccess() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.registryAccess");
    }

    @Override
    public String gatherChunkSourceStats() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.gatherChunkSourceStats");
    }

    @Override
    public TickRateManager tickRateManager() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.tickRateManager");
    }

    @Override
    public WorldBorder getWorldBorder() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getWorldBorder");
    }

    @Override
    public boolean destroyBlock(BlockPos pos, boolean dropResources, Entity breaker, int updateLimit) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.destroyBlock");
    }


    @Override
    public boolean hasChunk(int chunkX, int chunkZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.hasChunk");
    }


    @Override
    public boolean isFluidAtPosition(final BlockPos pos, final Predicate<FluidState> predicate) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.isFluidAtPosition");
    }

    @Override
    public boolean isStateAtPosition(final BlockPos pos, final Predicate<BlockState> predicate) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.isStateAtPosition");
    }

    @Override
    public boolean removeBlock(BlockPos pos, boolean movedByPiston) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.removeBlock");
    }

    @Override
    public boolean setBlock(BlockPos pos, BlockState blockState, int updateFlags, int updateLimit) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.setBlock");
    }

    @Override
    public int getHeight() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getHeight");
    }

    @Override
    public int getHeight(Heightmap.Types type, int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getHeight");
    }

    @Override
    public int getMinY() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getMinY");
    }

    @Override
    public int getSeaLevel() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getSeaLevel");
    }

    @Override
    public int getSkyDarken() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getSkyDarken");
    }

    @Override
    public long nextSubTickCount() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.nextSubTickCount");
    }

    @Override
    public void addParticle(final ParticleOptions particle, final double x, final double y, final double z, final double xd, final double yd, final double zd) {
        // Accepted and dropped: decoration; routing particles to clients is the sync
        // slice, and stopping a craft over sparkles helps no one.
    }

    @Override
    public void destroyBlockProgress(final int id, final BlockPos blockPos, final int progress) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.destroyBlockProgress");
    }

    @Override
    public void explode(final Entity source, final DamageSource damageSource, final ExplosionDamageCalculator damageCalculator, final double x, final double y, final double z, final float r, final boolean fire, final net.minecraft.world.level.Level.ExplosionInteraction interactionType, final ParticleOptions smallExplosionParticles, final ParticleOptions largeExplosionParticles, final WeightedList<ExplosionParticleInfo> blockParticles, final Holder<SoundEvent> explosionSound) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.explode");
    }

    @Override
    public void gameEvent(Holder<GameEvent> gameEvent, Vec3 position, GameEvent.Context context) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.gameEvent");
    }

    @Override
    public void levelEvent(final Entity source, final int type, final BlockPos pos, final int data) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.levelEvent");
    }

    @Override
    public void playSeededSound(final Entity except, final Entity sourceEntity, final Holder<SoundEvent> sound, final SoundSource source, final float volume, final float pitch, final long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.playSeededSound");
    }

    @Override
    public void playSeededSound(final Entity except, final double x, final double y, final double z, final Holder<SoundEvent> sound, final SoundSource source, final float volume, final float pitch, final long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.playSeededSound");
    }

    @Override
    public void sendBlockUpdated(BlockPos pos, BlockState old, BlockState current, int updateFlags) {
        // Accepted and dropped: pushing a mod entity's new state to clients is the sync
        // slice. The server-side state is already correct.
    }

    @Override
    public void setRespawnData(final LevelData.RespawnData respawnData) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.setRespawnData");
    }











    @Override
    public net.minecraft.world.attribute.EnvironmentAttributeSystem environmentAttributes() {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.environmentAttributes");
    }

}
