package net.minecraft.server.level;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.clock.ServerClockManager;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.entity.LevelCallback;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.saveddata.WeatherData;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.LevelTicks;
import dev.pumpkin.shim.Unimplemented;

public class ServerLevel extends Level implements WorldGenLevel, ServerEntityGetter {

    private final List<ServerPlayer> players = null;

    public ServerLevel(MinecraftServer server, Executor executor, LevelStorageSource.LevelStorageAccess levelStorage, ServerLevelData levelData, ResourceKey<Level> dimension, LevelStem levelStem, boolean isDebug, long biomeZoomSeed, List<CustomSpawner> customSpawners, boolean tickTime) {
    }

    public int getNextEntityId() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getNextEntityId:()I");
    }

    public Holder<Biome> getUncachedNoiseBiome(int quartX, int quartY, int quartZ) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getUncachedNoiseBiome:(III)Lnet/minecraft/core/Holder;");
    }

    public ServerClockManager clockManager() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.clockManager:()Lnet/minecraft/world/clock/ServerClockManager;");
    }

    public EnvironmentAttributeSystem environmentAttributes() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.environmentAttributes:()Lnet/minecraft/world/attribute/EnvironmentAttributeSystem;");
    }

    public boolean shouldTickBlocksAt(long chunkPos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.shouldTickBlocksAt:(J)Z");
    }

    public ServerScoreboard getScoreboard() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getScoreboard:()Lnet/minecraft/server/ServerScoreboard;");
    }

    public DifficultyInstance getCurrentDifficultyAt(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getCurrentDifficultyAt:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/DifficultyInstance;");
    }

    public boolean mayInteract(Entity entity, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.mayInteract:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;)Z");
    }

    public <T extends Entity> List<? extends T> getEntities(EntityTypeTest<Entity, T> type, Predicate<? super T> selector) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getEntities:(Lnet/minecraft/world/level/entity/EntityTypeTest;Ljava/util/function/Predicate;)Ljava/util/List;");
    }

    public <T extends Entity> void getEntities(EntityTypeTest<Entity, T> type, Predicate<? super T> selector, List<? super T> result) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getEntities:(Lnet/minecraft/world/level/entity/EntityTypeTest;Ljava/util/function/Predicate;Ljava/util/List;)V");
    }

    public <T extends Entity> void getEntities(EntityTypeTest<Entity, T> type, Predicate<? super T> selector, List<? super T> result, int maxResults) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getEntities:(Lnet/minecraft/world/level/entity/EntityTypeTest;Ljava/util/function/Predicate;Ljava/util/List;I)V");
    }

    public List<ServerPlayer> getPlayers(Predicate<? super ServerPlayer> selector, int maxResults) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getPlayers:(Ljava/util/function/Predicate;I)Ljava/util/List;");
    }

    public boolean addFreshEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.addFreshEntity:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    private boolean addEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.addEntity:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public void destroyBlockProgress(int id, BlockPos blockPos, int progress) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.destroyBlockProgress:(ILnet/minecraft/core/BlockPos;I)V");
    }

    public void playSeededSound(Entity except, double x, double y, double z, Holder<SoundEvent> sound, SoundSource source, float volume, float pitch, long seed) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.playSeededSound:(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;FFJ)V");
    }

    public void playSeededSound(Entity except, Entity sourceEntity, Holder<SoundEvent> sound, SoundSource source, float volume, float pitch, long seed) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.playSeededSound:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;FFJ)V");
    }

    public void globalLevelEvent(int type, BlockPos pos, int data) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.globalLevelEvent:(ILnet/minecraft/core/BlockPos;I)V");
    }

    public void levelEvent(Entity source, int type, BlockPos pos, int data) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.levelEvent:(Lnet/minecraft/world/entity/Entity;ILnet/minecraft/core/BlockPos;I)V");
    }

    public void gameEvent(Holder<GameEvent> gameEvent, Vec3 position, GameEvent.Context context) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.gameEvent:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/level/gameevent/GameEvent$Context;)V");
    }

    public void sendBlockUpdated(BlockPos pos, BlockState old, BlockState current, int updateFlags) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.sendBlockUpdated:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;I)V");
    }

    public void updateNeighborsAt(BlockPos pos, Block sourceBlock) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.updateNeighborsAt:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;)V");
    }

    public void updateNeighborsAt(BlockPos pos, Block sourceBlock, Orientation orientation) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.updateNeighborsAt:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/redstone/Orientation;)V");
    }

    public void updateNeighborsAtExceptFromFacing(BlockPos pos, Block blockObject, Direction skipDirection, Orientation orientation) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.updateNeighborsAtExceptFromFacing:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/redstone/Orientation;)V");
    }

    public void neighborChanged(BlockPos pos, Block changedBlock, Orientation orientation) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.neighborChanged:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/redstone/Orientation;)V");
    }

    public void neighborChanged(BlockState state, BlockPos pos, Block changedBlock, Orientation orientation, boolean movedByPiston) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.neighborChanged:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/redstone/Orientation;Z)V");
    }

    public void broadcastEntityEvent(Entity entity, byte event) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.broadcastEntityEvent:(Lnet/minecraft/world/entity/Entity;B)V");
    }

    public void broadcastDamageEvent(Entity entity, DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.broadcastDamageEvent:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    public ServerChunkCache getChunkSource() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getChunkSource:()Lnet/minecraft/server/level/ServerChunkCache;");
    }

    public void explode(Entity source, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, double x, double y, double z, float r, boolean fire, Level.ExplosionInteraction interactionType, ParticleOptions smallExplosionParticles, ParticleOptions largeExplosionParticles, WeightedList<ExplosionParticleInfo> blockParticles, Holder<SoundEvent> explosionSound) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.explode:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/util/random/WeightedList;Lnet/minecraft/core/Holder;)V");
    }

    public void blockEvent(BlockPos pos, Block block, int b0, int b1) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.blockEvent:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;II)V");
    }

    public LevelTicks<Block> getBlockTicks() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getBlockTicks:()Lnet/minecraft/world/ticks/LevelTicks;");
    }

    public LevelTicks<Fluid> getFluidTicks() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getFluidTicks:()Lnet/minecraft/world/ticks/LevelTicks;");
    }

    public MinecraftServer getServer() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getServer:()Lnet/minecraft/server/MinecraftServer;");
    }

    public <T extends ParticleOptions> int sendParticles(T particle, double x, double y, double z, int count, double xDist, double yDist, double zDist, double speed) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.sendParticles:(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I");
    }

    public <T extends ParticleOptions> int sendParticles(T particle, boolean overrideLimiter, boolean alwaysShow, double x, double y, double z, int count, double xDist, double yDist, double zDist, double speed) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.sendParticles:(Lnet/minecraft/core/particles/ParticleOptions;ZZDDDIDDDD)I");
    }

    public <T extends ParticleOptions> boolean sendParticles(ServerPlayer player, T particle, boolean overrideLimiter, boolean alwaysShow, double x, double y, double z, int count, double xDist, double yDist, double zDist, double speed) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.sendParticles:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/core/particles/ParticleOptions;ZZDDDIDDDD)Z");
    }

    private boolean sendParticles(ServerPlayer player, boolean overrideLimiter, double x, double y, double z, Packet<?> packet) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.sendParticles:(Lnet/minecraft/server/level/ServerPlayer;ZDDDLnet/minecraft/network/protocol/Packet;)Z");
    }

    public Entity getEntity(int id) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getEntity:(I)Lnet/minecraft/world/entity/Entity;");
    }

    public Entity getEntityInAnyDimension(UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getEntityInAnyDimension:(Ljava/util/UUID;)Lnet/minecraft/world/entity/Entity;");
    }

    public Player getPlayerInAnyDimension(UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getPlayerInAnyDimension:(Ljava/util/UUID;)Lnet/minecraft/world/entity/player/Player;");
    }

    public Collection<net.neoforged.neoforge.entity.PartEntity<?>> dragonParts() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.dragonParts:()Ljava/util/Collection;");
    }

    public WorldBorder getWorldBorder() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getWorldBorder:()Lnet/minecraft/world/level/border/WorldBorder;");
    }

    public RecipeManager recipeAccess() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.recipeAccess:()Lnet/minecraft/world/item/crafting/RecipeManager;");
    }

    public TickRateManager tickRateManager() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.tickRateManager:()Lnet/minecraft/world/TickRateManager;");
    }

    public boolean noSave() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.noSave:()Z");
    }

    public MapItemSavedData getMapData(MapId id) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getMapData:(Lnet/minecraft/world/level/saveddata/maps/MapId;)Lnet/minecraft/world/level/saveddata/maps/MapItemSavedData;");
    }

    public void setRespawnData(LevelData.RespawnData respawnData) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.setRespawnData:(Lnet/minecraft/world/level/storage/LevelData$RespawnData;)V");
    }

    public LevelData.RespawnData getRespawnData() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getRespawnData:()Lnet/minecraft/world/level/storage/LevelData$RespawnData;");
    }

    public List<ServerPlayer> players() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.players:()Ljava/util/List;");
    }

    public void updatePOIOnBlockStateChange(BlockPos pos, BlockState oldState, BlockState newState) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.updatePOIOnBlockStateChange:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.toString:()Ljava/lang/String;");
    }

    public long getSeed() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getSeed:()J");
    }

    public WeatherData getWeatherData() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getWeatherData:()Lnet/minecraft/world/level/saveddata/WeatherData;");
    }

    public ServerLevel getLevel() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public LevelEntityGetter<Entity> getEntities() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getEntities:()Lnet/minecraft/world/level/entity/LevelEntityGetter;");
    }

    public void close() throws IOException {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.close:()V");
    }

    public String gatherChunkSourceStats() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.gatherChunkSourceStats:()Ljava/lang/String;");
    }

    public FeatureFlagSet enabledFeatures() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.enabledFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public PotionBrewing potionBrewing() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.potionBrewing:()Lnet/minecraft/world/item/alchemy/PotionBrewing;");
    }

    public FuelValues fuelValues() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.fuelValues:()Lnet/minecraft/world/level/block/entity/FuelValues;");
    }

    public GameRules getGameRules() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getGameRules:()Lnet/minecraft/world/level/gamerules/GameRules;");
    }

    public CrashReportCategory fillReportDetails(CrashReport report) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.fillReportDetails:(Lnet/minecraft/CrashReport;)Lnet/minecraft/CrashReportCategory;");
    }

    public int getSeaLevel() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.getSeaLevel:()I");
    }

    public void onBlockEntityAdded(BlockEntity blockEntity) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.onBlockEntityAdded:(Lnet/minecraft/world/level/block/entity/BlockEntity;)V");
    }

    public boolean isAllowedToEnterPortal(Level toLevel) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.isAllowedToEnterPortal:(Lnet/minecraft/world/level/Level;)Z");
    }

    private final class EntityCallbacks implements LevelCallback<Entity> {

        public void onCreated(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel$EntityCallbacks.onCreated:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onDestroyed(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel$EntityCallbacks.onDestroyed:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onTickingStart(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel$EntityCallbacks.onTickingStart:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onTickingEnd(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel$EntityCallbacks.onTickingEnd:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onTrackingStart(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel$EntityCallbacks.onTrackingStart:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onTrackingEnd(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel$EntityCallbacks.onTrackingEnd:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onSectionChange(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel$EntityCallbacks.onSectionChange:(Lnet/minecraft/world/entity/Entity;)V");
        }

        protected EntityCallbacks() {
        }
    }

    public final void syncData(net.neoforged.neoforge.attachment.AttachmentType<?> type) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.syncData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)V");
    }

    public void invalidateCapabilities(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.invalidateCapabilities:(Lnet/minecraft/core/BlockPos;)V");
    }

    public void invalidateCapabilities(ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerLevel.invalidateCapabilities:(Lnet/minecraft/world/level/ChunkPos;)V");
    }

    public ServerLevel() {
    }
}
