package net.minecraft.world.entity;

import com.mojang.serialization.Codec;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class EntityType<T extends Entity> implements EntityTypeTest<Entity, T>, FeatureElement {

    private final Holder.Reference<EntityType<?>> builtInRegistryHolder = null;

    public static final Codec<EntityType<?>> CODEC = null;

    private final TagKey<Block> immuneTo = null;

    private final boolean fireImmune = false;

    private final boolean canSpawnFarFromPlayer = false;

    private final int clientTrackingRange = 0;

    private final int updateInterval = 0;

    private final float spawnDimensionsScale = 0.0F;

    private final FeatureFlagSet requiredFeatures = null;

    private final boolean onlyOpCanSetNbt = false;

    public static Identifier getKey(EntityType<?> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getKey:(Lnet/minecraft/world/entity/EntityType;)Lnet/minecraft/resources/Identifier;");
    }

    public EntityType(EntityType.EntityFactory<T> factory, MobCategory category, boolean serialize, boolean summon, boolean fireImmune, boolean canSpawnFarFromPlayer, TagKey<Block> immuneTo, EntityDimensions dimensions, float spawnDimensionsScale, int clientTrackingRange, int updateInterval, String descriptionId, Optional<ResourceKey<LootTable>> lootTable, FeatureFlagSet requiredFeatures, boolean allowedInPeaceful) {
    }

    public EntityType(EntityType.EntityFactory<T> factory, MobCategory category, boolean serialize, boolean summon, boolean fireImmune, boolean canSpawnFarFromPlayer, TagKey<Block> immuneTo, EntityDimensions dimensions, float spawnDimensionsScale, int clientTrackingRange, int updateInterval, String descriptionId, Optional<ResourceKey<LootTable>> lootTable, FeatureFlagSet requiredFeatures, boolean allowedInPeaceful, final java.util.function.Predicate<EntityType<?>> trackDeltasSupplier, final java.util.function.ToIntFunction<EntityType<?>> trackingRangeSupplier, final java.util.function.ToIntFunction<EntityType<?>> updateIntervalSupplier, boolean onlyOpCanSetNbt) {
    }

    public T spawn(ServerLevel level, ItemStack itemStack, LivingEntity user, BlockPos spawnPos, EntitySpawnReason spawnReason, boolean tryMoveDown, boolean movedUp) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.spawn:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/EntitySpawnReason;ZZ)Lnet/minecraft/world/entity/Entity;");
    }

    public T spawn(ServerLevel level, BlockPos spawnPos, EntitySpawnReason spawnReason) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.spawn:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/EntitySpawnReason;)Lnet/minecraft/world/entity/Entity;");
    }

    public T spawn(ServerLevel level, PostSpawnProcessor<T> postSpawnConfig, BlockPos spawnPos, EntitySpawnReason spawnReason, boolean tryMoveDown, boolean movedUp) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.spawn:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/PostSpawnProcessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/EntitySpawnReason;ZZ)Lnet/minecraft/world/entity/Entity;");
    }

    public T create(ServerLevel level, PostSpawnProcessor<T> postSpawnConfig, BlockPos spawnPos, EntitySpawnReason spawnReason, boolean tryMoveDown, boolean movedUp) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.create:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/PostSpawnProcessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/EntitySpawnReason;ZZ)Lnet/minecraft/world/entity/Entity;");
    }

    public boolean canSerialize() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.canSerialize:()Z");
    }

    public boolean canSummon() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.canSummon:()Z");
    }

    public boolean fireImmune() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.fireImmune:()Z");
    }

    public boolean canSpawnFarFromPlayer() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.canSpawnFarFromPlayer:()Z");
    }

    public MobCategory getCategory() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getCategory:()Lnet/minecraft/world/entity/MobCategory;");
    }

    public Component getDescription() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDescription:()Lnet/minecraft/network/chat/Component;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.toString:()Ljava/lang/String;");
    }

    public Optional<ResourceKey<LootTable>> getDefaultLootTable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDefaultLootTable:()Ljava/util/Optional;");
    }

    public float getWidth() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getWidth:()F");
    }

    public float getHeight() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getHeight:()F");
    }

    public FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public T create(Level level, EntitySpawnReason reason) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.create:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/EntitySpawnReason;)Lnet/minecraft/world/entity/Entity;");
    }

    public T create(Level level, EntitySpawnRequest request) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.create:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/EntitySpawnRequest;)Lnet/minecraft/world/entity/Entity;");
    }

    public static Optional<Entity> create(ValueInput input, Level level, EntitySpawnRequest request) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.create:(Lnet/minecraft/world/level/storage/ValueInput;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/EntitySpawnRequest;)Ljava/util/Optional;");
    }

    public static Optional<Entity> create(EntityType<?> type, ValueInput input, Level level, EntitySpawnReason reason) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.create:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/storage/ValueInput;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/EntitySpawnReason;)Ljava/util/Optional;");
    }

    public EntityDimensions getDimensions() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDimensions:()Lnet/minecraft/world/entity/EntityDimensions;");
    }

    public int clientTrackingRange() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.clientTrackingRange:()I");
    }

    public int updateInterval() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.updateInterval:()I");
    }

    public boolean trackDeltas() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.trackDeltas:()Z");
    }

    public T tryCast(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.tryCast:(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/entity/Entity;");
    }

    public Class<? extends Entity> getBaseClass() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getBaseClass:()Ljava/lang/Class;");
    }

    public Holder.Reference<EntityType<?>> builtInRegistryHolder() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.builtInRegistryHolder:()Lnet/minecraft/core/Holder$Reference;");
    }

    public boolean isAllowedInPeaceful() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.isAllowedInPeaceful:()Z");
    }

    public boolean onlyOpCanSetNbt() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.onlyOpCanSetNbt:()Z");
    }

    public Stream<net.minecraft.tags.TagKey<EntityType<?>>> getTags() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getTags:()Ljava/util/stream/Stream;");
    }

    public static class Builder<T extends Entity> {

        private TagKey<Block> immuneTo;

        private boolean fireImmune;

        private boolean canSpawnFarFromPlayer;

        private int clientTrackingRange;

        private int updateInterval;

        private float spawnDimensionsScale;

        private FeatureFlagSet requiredFeatures;

        private Builder(EntityType.EntityFactory<T> factory, MobCategory category) {
        }

        public static <T extends Entity> EntityType.Builder<T> of(EntityType.EntityFactory<T> factory, MobCategory category) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.of:(Lnet/minecraft/world/entity/EntityType$EntityFactory;Lnet/minecraft/world/entity/MobCategory;)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> sized(float width, float height) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.sized:(FF)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> spawnDimensionsScale(float scale) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.spawnDimensionsScale:(F)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> eyeHeight(float eyeHeight) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.eyeHeight:(F)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> attach(EntityAttachment attachment, float x, float y, float z) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.attach:(Lnet/minecraft/world/entity/EntityAttachment;FFF)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> attach(EntityAttachment attachment, Vec3 point) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.attach:(Lnet/minecraft/world/entity/EntityAttachment;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> noSummon() {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.noSummon:()Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> noSave() {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.noSave:()Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> fireImmune() {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.fireImmune:()Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> immuneTo(TagKey<Block> tag) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.immuneTo:(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> canSpawnFarFromPlayer() {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.canSpawnFarFromPlayer:()Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> clientTrackingRange(int clientChunkRange) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.clientTrackingRange:(I)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> updateInterval(int updateInterval) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.updateInterval:(I)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> requiredFeatures(FeatureFlag... flags) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.requiredFeatures:([Lnet/minecraft/world/flag/FeatureFlag;)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> noLootTable() {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.noLootTable:()Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> notInPeaceful() {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.notInPeaceful:()Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> setUpdateInterval(int interval) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.setUpdateInterval:(I)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> setTrackingRange(int range) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.setTrackingRange:(I)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> setShouldReceiveVelocityUpdates(boolean value) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.setShouldReceiveVelocityUpdates:(Z)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType.Builder<T> setOnlyOpCanSetNbt(boolean onlyOpCanSetNbt) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.setOnlyOpCanSetNbt:(Z)Lnet/minecraft/world/entity/EntityType$Builder;");
        }

        public EntityType<T> build(ResourceKey<EntityType<?>> name) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.build:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/entity/EntityType;");
        }

        public Builder() {
        }
    }

    public interface EntityFactory<T extends Entity> {

        T create(final EntityType<T> entityType, final Level level);
    }

    public EntityType() {
    }
}
