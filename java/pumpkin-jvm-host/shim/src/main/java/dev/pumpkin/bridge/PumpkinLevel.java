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

    // Mods walk getChunkSource().chunkMap.playerMap to ask who watches a chunk; the
    // stand-in cache answers through the same fields, with nobody watching.
    private final net.minecraft.server.level.ServerChunkCache pumpkinChunkSource =
            new net.minecraft.server.level.ServerChunkCache();

    @Override
    public net.minecraft.server.level.ServerChunkCache getChunkSource() {
        return pumpkinChunkSource;
    }

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
        // Cucumber's setChangedFast marks through the level instead of the entity, which
        // left tick progress invisible to the serialisation gate. Forward the mark to
        // the entity's own dirty flag so the next tick persists it.
        BlockEntity entity = PumpkinBlockEntities.get(pos.getX(), pos.getY(), pos.getZ());
        if (entity != null) {
            // Flag-only: a mod's setChanged override may mark the chunk dirty again,
            // and that loop never ends.
            entity.pumpkinMarkChanged();
        }
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

    private final java.util.List<String> sounds = new java.util.ArrayList<>();

    /** Sounds the mod played this interaction, as {@code name:vol:pitch:x:y:z}. */
    public java.util.List<String> pumpkinDrainSounds() {
        java.util.List<String> drained = new java.util.ArrayList<>(sounds);
        sounds.clear();
        return drained;
    }

    @Override
    public void playSound(Entity except, BlockPos pos, SoundEvent sound, SoundSource source,
            float volume, float pitch) {
        // A mod-registered sound has a name a modded client can resolve; it rides the
        // reply and plays for real. Vanilla sound constants are unmapped stubs (or null)
        // in this shim and stay dropped -- a wrong sound is worse than none.
        String name = sound == null ? null
                : net.neoforged.neoforge.registries.DeferredHolder
                        .pumpkinResolveName("minecraft:sound_event", sound);
        if (name != null && pos != null) {
            sounds.add(name + ":" + volume + ":" + pitch + ":" + pos.getX() + ":"
                    + pos.getY() + ":" + pos.getZ());
        }
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

    // Pumpkin divergence: one shared stand-in chunk; the only fact mods want from it
    // here is markUnsaved, which the bridge's own persistence makes a no-op.
    private static final net.minecraft.world.level.chunk.LevelChunk PUMPKIN_CHUNK =
            new net.minecraft.world.level.chunk.LevelChunk(null, null);

    @Override
    public ChunkAccess getChunk(final int chunkX, final int chunkZ, final ChunkStatus targetStatus, final boolean loadOrGenerate) {
        return PUMPKIN_CHUNK;
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
    // Pumpkin divergence: no fluid snapshot travels over the bridge yet, so every
    // position reads as fluidless -- true for machine rooms; lava-fed passive
    // generation needs the fluid snapshot slice.
    public FluidState getFluidState(BlockPos pos) {
        return new FluidState(net.minecraft.world.level.material.Fluids.EMPTY, null, null);
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
    // Pumpkin divergence: an inert access whose default methods (lookupOrThrow and
    // kin carry real bodies) run; only truly-absent members throw, by name.
    public RegistryAccess registryAccess() {
        return dev.pumpkin.shim.Stubs.of(net.minecraft.core.RegistryAccess.class,
                "net/minecraft/core/RegistryAccess via Level.registryAccess");
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
    // Pumpkin divergence: truthful for this stand-in -- the bridge only runs for
    // interactions on loaded blocks, so the neighborhood the mod asks about is loaded.
    public boolean hasChunk(int chunkX, int chunkZ) {
        return true;
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
    // Pumpkin divergence: the overworld's real height range -- Pumpkin only routes
    // overworld interactions through this level today.
    public int getHeight() {
        return 384;
    }

    @Override
    public int getHeight(Heightmap.Types type, int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/Level.getHeight");
    }

    @Override
    public int getMinY() {
        return -64;
    }

    @Override
    // Pumpkin divergence: the overworld's real sea level.
    public int getSeaLevel() {
        return 63;
    }

    // Pumpkin divergence: the vanilla fuel table, served whole.
    @Override
    public net.minecraft.world.level.block.entity.FuelValues fuelValues() {
        return net.minecraft.world.level.block.entity.FuelValues.pumpkinVanilla();
    }

    // Pumpkin divergence: the stand-in fronts the overworld, the one dimension the
    // bridge routes today.
    @Override
    public net.minecraft.resources.ResourceKey<net.minecraft.world.level.Level> dimension() {
        return net.minecraft.resources.ResourceKey.create(
                net.minecraft.resources.ResourceKey.createRegistryKey(
                        net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "dimension")),
                net.minecraft.resources.Identifier.withDefaultNamespace("overworld"));
    }

    // Pumpkin divergence: the real biome temperature at the ticking position, told by
    // the Rust side per tick; ambient-heat maths read it through getBiome below.
    private static volatile double pumpkinBiomeTemperature = 0.8;

    static void pumpkinSetBiomeTemperature(double temperature) {
        pumpkinBiomeTemperature = temperature;
    }

    public static double pumpkinBiomeTemperature() {
        return pumpkinBiomeTemperature;
    }

    // Pumpkin divergence: which neighbors of the ticking machine hold a real vanilla
    // inventory, told by the Rust side per tick. Bit order = Direction ordinals.
    private static volatile long pumpkinContainerCenter = Long.MIN_VALUE;
    private static volatile int pumpkinContainerMask;

    static void pumpkinSetContainerNeighbors(int x, int y, int z, int mask) {
        pumpkinContainerCenter = net.minecraft.core.BlockPos.asLong(x, y, z);
        pumpkinContainerMask = mask;
    }

    /** Whether the Rust world holds a vanilla inventory at this position. */
    public static boolean pumpkinIsVanillaContainer(net.minecraft.core.BlockPos pos) {
        long center = pumpkinContainerCenter;
        if (center == Long.MIN_VALUE) {
            return false;
        }
        net.minecraft.core.BlockPos machine = net.minecraft.core.BlockPos.of(center);
        for (net.minecraft.core.Direction direction : net.minecraft.core.Direction.values()) {
            if ((pumpkinContainerMask & (1 << direction.ordinal())) != 0
                    && machine.relative(direction).equals(pos)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public net.minecraft.core.Holder<net.minecraft.world.level.biome.Biome> getBiome(BlockPos pos) {
        return net.minecraft.core.Holder.Reference.pumpkinOf(
                net.minecraft.resources.ResourceKey.create(
                        net.minecraft.resources.ResourceKey.createRegistryKey(
                                net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "worldgen/biome")),
                        net.minecraft.resources.Identifier.withDefaultNamespace("plains")),
                net.minecraft.world.level.biome.Biome.pumpkinAmbient());
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

    // ---- random-tick context -------------------------------------------------------
    // A random tick hands the mod a light level and a small neighborhood of states
    // (the crop, the soil square below it, the row around it). getBlockState answers
    // from that snapshot and fails loudly outside it -- a position the bridge did not
    // send is a gap to widen, not a stone to invent. setBlock records what the mod
    // wrote so the bridge can carry the new state back to the server.

    private int pumpkinBrightness;
    private java.util.Map<String, Integer> pumpkinBrightnessMap = java.util.Map.of();
    private Integer pumpkinScheduledDelay;
    private java.util.Map<String, net.minecraft.world.level.block.state.BlockState> pumpkinSnapshot =
            java.util.Map.of();
    private final java.util.Map<String, net.minecraft.world.level.block.state.BlockState> pumpkinWrites =
            new java.util.HashMap<>();

    void pumpkinSetRandomTickContext(int brightness,
            java.util.Map<String, net.minecraft.world.level.block.state.BlockState> snapshot) {
        pumpkinSetRandomTickContext(brightness, java.util.Map.of(), snapshot);
    }

    // A scheduled tick can touch a whole column (a growth accelerator forcing the crop
    // above), so light arrives per position where the caller measured it; positions the
    // map does not name fall back to the scalar.
    void pumpkinSetRandomTickContext(int brightness, java.util.Map<String, Integer> perPosition,
            java.util.Map<String, net.minecraft.world.level.block.state.BlockState> snapshot) {
        pumpkinBrightness = brightness;
        pumpkinBrightnessMap = perPosition;
        pumpkinSnapshot = snapshot;
        pumpkinScheduledDelay = null;
        pumpkinWrites.clear();
    }

    void pumpkinClearRandomTickContext() {
        pumpkinSnapshot = java.util.Map.of();
        pumpkinBrightnessMap = java.util.Map.of();
        pumpkinScheduledDelay = null;
        pumpkinWrites.clear();
    }

    /** Every state the mod wrote this call, keyed {@code x,y,z}. */
    java.util.Map<String, net.minecraft.world.level.block.state.BlockState> pumpkinWrites() {
        return pumpkinWrites;
    }

    /** The block-tick delay the mod asked for this call, or null. */
    Integer pumpkinScheduledDelay() {
        return pumpkinScheduledDelay;
    }

    @Override
    public void scheduleTick(BlockPos pos, net.minecraft.world.level.block.Block type,
            int tickDelay) {
        pumpkinScheduledDelay = tickDelay;
    }

    @Override
    public void scheduleTick(BlockPos pos, net.minecraft.world.level.block.Block type,
            int tickDelay, net.minecraft.world.ticks.TickPriority priority) {
        pumpkinScheduledDelay = tickDelay;
    }

    net.minecraft.world.level.block.state.BlockState pumpkinWrittenState(int x, int y, int z) {
        return pumpkinWrites.get(x + "," + y + "," + z);
    }

    // The bridge measures light at the ticked position; a mod asking about another
    // position gets the same answer, which is the best fact this level holds.
    public int getRawBrightness(BlockPos pos, int amount) {
        Integer at = pumpkinBrightnessMap.get(pos.getX() + "," + pos.getY() + "," + pos.getZ());
        return at != null ? at : pumpkinBrightness;
    }

    @Override
    public net.minecraft.world.level.block.state.BlockState getBlockState(BlockPos pos) {
        String key = pos.getX() + "," + pos.getY() + "," + pos.getZ();
        net.minecraft.world.level.block.state.BlockState written = pumpkinWrites.get(key);
        if (written != null) {
            return written;
        }
        net.minecraft.world.level.block.state.BlockState state = pumpkinSnapshot.get(key);
        if (state == null) {
            throw dev.pumpkin.shim.Unimplemented.forMember(
                    "dev/pumpkin/bridge/PumpkinLevel.getBlockState outside the snapshot: " + key);
        }
        return state;
    }

    public boolean setBlock(BlockPos pos, net.minecraft.world.level.block.state.BlockState state,
            int updateFlags) {
        pumpkinWrites.put(pos.getX() + "," + pos.getY() + "," + pos.getZ(), state);
        return true;
    }

    @Override
    public boolean setBlock(BlockPos pos, net.minecraft.world.level.block.state.BlockState state,
            int updateFlags, int updateLimit) {
        return setBlock(pos, state, updateFlags);
    }
}
