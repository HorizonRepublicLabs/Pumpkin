package net.minecraft.client.multiplayer;

import java.util.List;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.client.ClientClockManager;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.extract.LevelExtractor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.crafting.RecipeAccess;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.entity.LevelCallback;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.WritableLevelData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.ticks.LevelTickAccess;
import dev.pumpkin.shim.Unimplemented;

public class ClientLevel extends Level implements BlockAndTintGetter, CacheSlot.Cleaner<ClientLevel> {

    public void handleBlockChangedAck(int sequence) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.handleBlockChangedAck:(I)V");
    }

    public void onBlockEntityAdded(BlockEntity blockEntity) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.onBlockEntityAdded:(Lnet/minecraft/world/level/block/entity/BlockEntity;)V");
    }

    public boolean setBlock(BlockPos pos, BlockState blockState, int updateFlags, int updateLimit) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.setBlock:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;II)Z");
    }

    public ClientLevel(ClientPacketListener connection, ClientLevel.ClientLevelData levelData, ResourceKey<Level> dimension, Holder<DimensionType> dimensionType, int serverChunkRadius, int serverSimulationDistance, LevelExtractor levelExtractor, boolean isDebug, long biomeZoomSeed, int seaLevel) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.<init>:(Lnet/minecraft/client/multiplayer/ClientPacketListener;Lnet/minecraft/client/multiplayer/ClientLevel$ClientLevelData;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/core/Holder;IILnet/minecraft/client/renderer/extract/LevelExtractor;ZJI)V");
    }

    public boolean shouldTickDeath(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.shouldTickDeath:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public void update() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.update:()V");
    }

    public boolean hasChunk(int chunkX, int chunkZ) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.hasChunk:(II)Z");
    }

    public void addEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.addEntity:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public List<Entity> getPushableEntities(Entity pusher, AABB boundingBox) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getPushableEntities:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;");
    }

    public Entity getEntity(int id) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getEntity:(I)Lnet/minecraft/world/entity/Entity;");
    }

    public void disconnect(Component message) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.disconnect:(Lnet/minecraft/network/chat/Component;)V");
    }

    public CrashReportCategory fillReportDetails(CrashReport report) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.fillReportDetails:(Lnet/minecraft/CrashReport;)Lnet/minecraft/CrashReportCategory;");
    }

    public void playSeededSound(Entity except, double x, double y, double z, Holder<SoundEvent> sound, SoundSource source, float volume, float pitch, long seed) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.playSeededSound:(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;FFJ)V");
    }

    public void playSeededSound(Entity except, Entity sourceEntity, Holder<SoundEvent> sound, SoundSource source, float volume, float pitch, long seed) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.playSeededSound:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;FFJ)V");
    }

    public void playLocalSound(Entity sourceEntity, SoundEvent sound, SoundSource source, float volume, float pitch) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.playLocalSound:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V");
    }

    public void playPlayerSound(SoundEvent sound, SoundSource source, float volume, float pitch) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.playPlayerSound:(Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V");
    }

    public void playLocalSound(double x, double y, double z, SoundEvent sound, SoundSource source, float volume, float pitch, boolean distanceDelay) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.playLocalSound:(DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V");
    }

    public void createFireworks(double x, double y, double z, double xd, double yd, double zd, List<FireworkExplosion> explosions) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.createFireworks:(DDDDDDLjava/util/List;)V");
    }

    public void sendPacketToServer(Packet<?> packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.sendPacketToServer:(Lnet/minecraft/network/protocol/Packet;)V");
    }

    public WorldBorder getWorldBorder() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getWorldBorder:()Lnet/minecraft/world/level/border/WorldBorder;");
    }

    public RecipeAccess recipeAccess() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.recipeAccess:()Lnet/minecraft/world/item/crafting/RecipeAccess;");
    }

    public TickRateManager tickRateManager() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.tickRateManager:()Lnet/minecraft/world/TickRateManager;");
    }

    public ClientClockManager clockManager() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.clockManager:()Lnet/minecraft/client/ClientClockManager;");
    }

    public EnvironmentAttributeSystem environmentAttributes() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.environmentAttributes:()Lnet/minecraft/world/attribute/EnvironmentAttributeSystem;");
    }

    public LevelTickAccess<Block> getBlockTicks() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getBlockTicks:()Lnet/minecraft/world/ticks/LevelTickAccess;");
    }

    public LevelTickAccess<Fluid> getFluidTicks() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getFluidTicks:()Lnet/minecraft/world/ticks/LevelTickAccess;");
    }

    public ClientChunkCache getChunkSource() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getChunkSource:()Lnet/minecraft/client/multiplayer/ClientChunkCache;");
    }

    public MapItemSavedData getMapData(MapId id) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getMapData:(Lnet/minecraft/world/level/saveddata/maps/MapId;)Lnet/minecraft/world/level/saveddata/maps/MapItemSavedData;");
    }

    public Scoreboard getScoreboard() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getScoreboard:()Lnet/minecraft/world/scores/Scoreboard;");
    }

    public void sendBlockUpdated(BlockPos pos, BlockState old, BlockState current, int updateFlags) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.sendBlockUpdated:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;I)V");
    }

    public void setBlocksDirty(BlockPos pos, BlockState oldState, BlockState newState) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.setBlocksDirty:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public void destroyBlockProgress(int id, BlockPos pos, int progress) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.destroyBlockProgress:(ILnet/minecraft/core/BlockPos;I)V");
    }

    public void globalLevelEvent(int type, BlockPos pos, int data) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.globalLevelEvent:(ILnet/minecraft/core/BlockPos;I)V");
    }

    public void levelEvent(Entity source, int type, BlockPos pos, int data) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.levelEvent:(Lnet/minecraft/world/entity/Entity;ILnet/minecraft/core/BlockPos;I)V");
    }

    public void addParticle(ParticleOptions particle, double x, double y, double z, double xd, double yd, double zd) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.addParticle:(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V");
    }

    public void addParticle(ParticleOptions particle, boolean overrideLimiter, boolean alwaysShow, double x, double y, double z, double xd, double yd, double zd) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.addParticle:(Lnet/minecraft/core/particles/ParticleOptions;ZZDDDDDD)V");
    }

    public void addAlwaysVisibleParticle(ParticleOptions particle, double x, double y, double z, double xd, double yd, double zd) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.addAlwaysVisibleParticle:(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V");
    }

    public void addAlwaysVisibleParticle(ParticleOptions particle, boolean overrideLimiter, double x, double y, double z, double xd, double yd, double zd) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.addAlwaysVisibleParticle:(Lnet/minecraft/core/particles/ParticleOptions;ZDDDDDD)V");
    }

    public List<AbstractClientPlayer> players() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.players:()Ljava/util/List;");
    }

    public List<net.neoforged.neoforge.entity.PartEntity<?>> dragonParts() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.dragonParts:()Ljava/util/List;");
    }

    public Holder<Biome> getUncachedNoiseBiome(int quartX, int quartY, int quartZ) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getUncachedNoiseBiome:(III)Lnet/minecraft/core/Holder;");
    }

    public void setSkyFlashTime(int skyFlashTime) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.setSkyFlashTime:(I)V");
    }

    public CardinalLighting cardinalLighting() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.cardinalLighting:()Lnet/minecraft/world/level/CardinalLighting;");
    }

    public int getBlockTint(BlockPos pos, ColorResolver resolver) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getBlockTint:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/ColorResolver;)I");
    }

    public void setRespawnData(LevelData.RespawnData respawnData) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.setRespawnData:(Lnet/minecraft/world/level/storage/LevelData$RespawnData;)V");
    }

    public LevelData.RespawnData getRespawnData() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getRespawnData:()Lnet/minecraft/world/level/storage/LevelData$RespawnData;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.toString:()Ljava/lang/String;");
    }

    public ClientLevel.ClientLevelData getLevelData() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getLevelData:()Lnet/minecraft/client/multiplayer/ClientLevel$ClientLevelData;");
    }

    public void gameEvent(Holder<GameEvent> gameEvent, Vec3 pos, GameEvent.Context context) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.gameEvent:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/level/gameevent/GameEvent$Context;)V");
    }

    protected LevelEntityGetter<Entity> getEntities() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getEntities:()Lnet/minecraft/world/level/entity/LevelEntityGetter;");
    }

    public String gatherChunkSourceStats() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.gatherChunkSourceStats:()Ljava/lang/String;");
    }

    public void addDestroyBlockEffect(BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.addDestroyBlockEffect:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public FeatureFlagSet enabledFeatures() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.enabledFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public PotionBrewing potionBrewing() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.potionBrewing:()Lnet/minecraft/world/item/alchemy/PotionBrewing;");
    }

    public FuelValues fuelValues() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.fuelValues:()Lnet/minecraft/world/level/block/entity/FuelValues;");
    }

    public void explode(Entity source, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, double x, double y, double z, float r, boolean fire, Level.ExplosionInteraction interactionType, ParticleOptions smallExplosionParticles, ParticleOptions largeExplosionParticles, WeightedList<ExplosionParticleInfo> secondaryParticles, Holder<SoundEvent> explosionSound) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.explode:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/util/random/WeightedList;Lnet/minecraft/core/Holder;)V");
    }

    public int getSeaLevel() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getSeaLevel:()I");
    }

    public int getClientLeafTintColor(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getClientLeafTintColor:(Lnet/minecraft/core/BlockPos;)I");
    }

    public void registerForCleaning(CacheSlot<ClientLevel, ?> slot) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.registerForCleaning:(Lnet/minecraft/client/multiplayer/CacheSlot;)V");
    }

    public static class ClientLevelData implements WritableLevelData {

        public ClientLevelData(Difficulty difficulty, boolean hardcore, boolean isFlat) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.<init>:(Lnet/minecraft/world/Difficulty;ZZ)V");
        }

        public LevelData.RespawnData getRespawnData() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.getRespawnData:()Lnet/minecraft/world/level/storage/LevelData$RespawnData;");
        }

        public long getGameTime() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.getGameTime:()J");
        }

        public void setGameTime(long time) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.setGameTime:(J)V");
        }

        public void setSpawn(LevelData.RespawnData respawnData) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.setSpawn:(Lnet/minecraft/world/level/storage/LevelData$RespawnData;)V");
        }

        public boolean isHardcore() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.isHardcore:()Z");
        }

        public Difficulty getDifficulty() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.getDifficulty:()Lnet/minecraft/world/Difficulty;");
        }

        public boolean isDifficultyLocked() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.isDifficultyLocked:()Z");
        }

        public void fillCrashReportCategory(CrashReportCategory category, LevelHeightAccessor levelHeightAccessor) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.fillCrashReportCategory:(Lnet/minecraft/CrashReportCategory;Lnet/minecraft/world/level/LevelHeightAccessor;)V");
        }

        public void setDifficulty(Difficulty difficulty) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.setDifficulty:(Lnet/minecraft/world/Difficulty;)V");
        }

        public void setDifficultyLocked(boolean locked) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$ClientLevelData.setDifficultyLocked:(Z)V");
        }

        public ClientLevelData() {
        }
    }

    private final class EntityCallbacks implements LevelCallback<Entity> {

        public void onCreated(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$EntityCallbacks.onCreated:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onDestroyed(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$EntityCallbacks.onDestroyed:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onTickingStart(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$EntityCallbacks.onTickingStart:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onTickingEnd(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$EntityCallbacks.onTickingEnd:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onTrackingStart(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$EntityCallbacks.onTrackingStart:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onTrackingEnd(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$EntityCallbacks.onTrackingEnd:(Lnet/minecraft/world/entity/Entity;)V");
        }

        public void onSectionChange(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel$EntityCallbacks.onSectionChange:(Lnet/minecraft/world/entity/Entity;)V");
        }

        protected EntityCallbacks() {
        }
    }

    public net.neoforged.neoforge.model.data.ModelDataManager getModelDataManager() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getModelDataManager:()Lnet/neoforged/neoforge/model/data/ModelDataManager;");
    }

    public net.neoforged.neoforge.model.data.ModelData getModelData(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientLevel.getModelData:(Lnet/minecraft/core/BlockPos;)Lnet/neoforged/neoforge/model/data/ModelData;");
    }

    public ClientLevel() {
    }
}
