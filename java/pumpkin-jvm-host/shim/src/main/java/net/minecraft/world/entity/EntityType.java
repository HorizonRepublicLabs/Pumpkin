package net.minecraft.world.entity;

import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.loot.LootTable;
import dev.pumpkin.shim.Unimplemented;

public class EntityType<T extends Entity> implements EntityTypeTest<Entity, T>, FeatureElement {

    public static Identifier getKey(EntityType<?> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getKey:(Lnet/minecraft/world/entity/EntityType;)Lnet/minecraft/resources/Identifier;");
    }

    public EntityType(EntityType.EntityFactory<T> factory, MobCategory category, boolean serialize, boolean summon, boolean fireImmune, boolean canSpawnFarFromPlayer, TagKey<Block> immuneTo, EntityDimensions dimensions, float spawnDimensionsScale, int clientTrackingRange, int updateInterval, String descriptionId, Optional<ResourceKey<LootTable>> lootTable, FeatureFlagSet requiredFeatures, boolean allowedInPeaceful) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.<init>:(Lnet/minecraft/world/entity/EntityType$EntityFactory;Lnet/minecraft/world/entity/MobCategory;ZZZZLnet/minecraft/tags/TagKey;Lnet/minecraft/world/entity/EntityDimensions;FIILjava/lang/String;Ljava/util/Optional;Lnet/minecraft/world/flag/FeatureFlagSet;Z)V");
    }

    public EntityType(EntityType.EntityFactory<T> factory, MobCategory category, boolean serialize, boolean summon, boolean fireImmune, boolean canSpawnFarFromPlayer, TagKey<Block> immuneTo, EntityDimensions dimensions, float spawnDimensionsScale, int clientTrackingRange, int updateInterval, String descriptionId, Optional<ResourceKey<LootTable>> lootTable, FeatureFlagSet requiredFeatures, boolean allowedInPeaceful, final java.util.function.Predicate<EntityType<?>> trackDeltasSupplier, final java.util.function.ToIntFunction<EntityType<?>> trackingRangeSupplier, final java.util.function.ToIntFunction<EntityType<?>> updateIntervalSupplier, boolean onlyOpCanSetNbt) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.<init>:(Lnet/minecraft/world/entity/EntityType$EntityFactory;Lnet/minecraft/world/entity/MobCategory;ZZZZLnet/minecraft/tags/TagKey;Lnet/minecraft/world/entity/EntityDimensions;FIILjava/lang/String;Ljava/util/Optional;Lnet/minecraft/world/flag/FeatureFlagSet;ZLjava/util/function/Predicate;Ljava/util/function/ToIntFunction;Ljava/util/function/ToIntFunction;Z)V");
    }

    public T create(ServerLevel level, PostSpawnProcessor<T> postSpawnConfig, BlockPos spawnPos, EntitySpawnReason spawnReason, boolean tryMoveDown, boolean movedUp) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.create:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/PostSpawnProcessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/EntitySpawnReason;ZZ)Lnet/minecraft/world/entity/Entity;");
    }

    public Component getDescription() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDescription:()Lnet/minecraft/network/chat/Component;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.toString:()Ljava/lang/String;");
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

    public T tryCast(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.tryCast:(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/entity/Entity;");
    }

    public Class<? extends Entity> getBaseClass() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getBaseClass:()Ljava/lang/Class;");
    }

    public Stream<net.minecraft.tags.TagKey<EntityType<?>>> getTags() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getTags:()Ljava/util/stream/Stream;");
    }

    public static class Builder<T extends Entity> {

        private Builder(EntityType.EntityFactory<T> factory, MobCategory category) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.<init>:(Lnet/minecraft/world/entity/EntityType$EntityFactory;Lnet/minecraft/world/entity/MobCategory;)V");
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
