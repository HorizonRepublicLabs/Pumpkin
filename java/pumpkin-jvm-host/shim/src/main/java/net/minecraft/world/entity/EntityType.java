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

    public static final Codec<EntityType<?>> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.world.entity.EntityType.CODEC");

    // Pumpkin divergence: NeoForge access-transforms this field public.
    public TagKey<Block> immuneTo;

    boolean fireImmune = false;

    boolean canSpawnFarFromPlayer;

    // Pumpkin divergence: NeoForge access-transforms these fields public; the
    // initial values are the vanilla builder defaults.
    public int clientTrackingRange = 5;

    public int updateInterval = 3;

    public float spawnDimensionsScale = 1.0F;

    private final FeatureFlagSet requiredFeatures = null;

    private final boolean onlyOpCanSetNbt = false;

    public static Identifier getKey(EntityType<?> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getKey:(Lnet/minecraft/world/entity/EntityType;)Lnet/minecraft/resources/Identifier;");
    }

    public EntityType(EntityType.EntityFactory<T> factory, MobCategory category, boolean serialize, boolean summon, boolean fireImmune, boolean canSpawnFarFromPlayer, TagKey<Block> immuneTo, EntityDimensions dimensions, float spawnDimensionsScale, int clientTrackingRange, int updateInterval, String descriptionId, Optional<ResourceKey<LootTable>> lootTable, FeatureFlagSet requiredFeatures, boolean allowedInPeaceful) {
        this.pumpkinCategory = category;
    }

    public EntityType(EntityType.EntityFactory<T> factory, MobCategory category, boolean serialize, boolean summon, boolean fireImmune, boolean canSpawnFarFromPlayer, TagKey<Block> immuneTo, EntityDimensions dimensions, float spawnDimensionsScale, int clientTrackingRange, int updateInterval, String descriptionId, Optional<ResourceKey<LootTable>> lootTable, FeatureFlagSet requiredFeatures, boolean allowedInPeaceful, final java.util.function.Predicate<EntityType<?>> trackDeltasSupplier, final java.util.function.ToIntFunction<EntityType<?>> trackingRangeSupplier, final java.util.function.ToIntFunction<EntityType<?>> updateIntervalSupplier, boolean onlyOpCanSetNbt) {
        this.pumpkinCategory = category;
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

    // Pumpkin divergence: truthful -- vanilla's flag is Builder.noSave(); the builder
    // records it and build() carries it here.
    public boolean pumpkinSerialize = true;

    public boolean canSerialize() {
        return pumpkinSerialize;
    }

    public boolean pumpkinSummon = true;

    public boolean canSummon() {
        return pumpkinSummon;
    }

    public boolean fireImmune() {
        return fireImmune;
    }

    public boolean canSpawnFarFromPlayer() {
        return canSpawnFarFromPlayer;
    }

    // Pumpkin divergence: real field -- set by the constructors and by EntityTypes'
    // stand-ins, whose categories are vanilla's own.
    public MobCategory pumpkinCategory;

    public MobCategory getCategory() {
        if (pumpkinCategory == null) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getCategory (no category recorded)");
        }
        return pumpkinCategory;
    }

    public Component getDescription() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDescription:()Lnet/minecraft/network/chat/Component;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.toString:()Ljava/lang/String;");
    }

    // Pumpkin divergence: vanilla derives entities/<path> from the type's own key;
    // builder-built types carry theirs from build(key), vanilla stand-ins from their
    // name. A type with neither has no truthful answer and fails loudly.
    public Optional<ResourceKey<LootTable>> pumpkinLootTable;

    public Optional<ResourceKey<LootTable>> getDefaultLootTable() {
        if (pumpkinLootTable != null) {
            return pumpkinLootTable;
        }
        if (pumpkinVanillaName != null) {
            return Optional.of(net.minecraft.resources.ResourceKey.create(
                net.minecraft.core.registries.Registries.LOOT_TABLE,
                net.minecraft.resources.Identifier.withDefaultNamespace("entities/" + pumpkinVanillaName)));
        }
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDefaultLootTable:()Ljava/util/Optional;");
    }

    public float getWidth() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getWidth:()F");
    }

    public float getHeight() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getHeight:()F");
    }

    // Pumpkin divergence: every reachable FeatureFlagSet is the empty set (see that
    // class); the answer is the one set that exists.
    public FeatureFlagSet requiredFeatures() {
        return FeatureFlagSet.of();
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

    public EntityDimensions pumpkinDimensions;

    public EntityDimensions getDimensions() {
        if (pumpkinDimensions == null) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDimensions:()Lnet/minecraft/world/entity/EntityDimensions;");
        }
        return pumpkinDimensions;
    }

    public int clientTrackingRange() {
        return clientTrackingRange;
    }

    public int updateInterval() {
        return updateInterval;
    }

    public boolean pumpkinTrackDeltas = true;

    public boolean trackDeltas() {
        return pumpkinTrackDeltas;
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

    public boolean pumpkinAllowedInPeaceful = true;

    public boolean isAllowedInPeaceful() {
        return pumpkinAllowedInPeaceful;
    }

    public boolean pumpkinOnlyOpCanSetNbt;

    public boolean onlyOpCanSetNbt() {
        return pumpkinOnlyOpCanSetNbt;
    }

    public Stream<net.minecraft.tags.TagKey<EntityType<?>>> getTags() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getTags:()Ljava/util/stream/Stream;");
    }

    public static class Builder<T extends Entity> {

        private TagKey<Block> immuneTo;

        private boolean fireImmune;

        private boolean canSpawnFarFromPlayer;

        private int clientTrackingRange = 5;

        private int updateInterval = 3;

        private float spawnDimensionsScale = 1.0F;

        // Pumpkin divergence: NeoForge access-transforms this field public.
        public FeatureFlagSet requiredFeatures;

        private Builder(EntityType.EntityFactory<T> factory, MobCategory category) {
        }

        // Pumpkin divergence: real chain -- the category is the fact registration
        // reads back; presentation knobs accept and drop.
        MobCategory pumpkinCategory;

        public static <T extends Entity> EntityType.Builder<T> of(EntityType.EntityFactory<T> factory, MobCategory category) {
            Builder<T> builder = new Builder<>();
            builder.pumpkinCategory = category;
            builder.canSpawnFarFromPlayer = category == MobCategory.CREATURE || category == MobCategory.MISC;
            return builder;
        }

        EntityDimensions pumpkinDimensions = EntityDimensions.scalable(0.6F, 1.8F);

        public EntityType.Builder<T> sized(float width, float height) {
            pumpkinDimensions = EntityDimensions.scalable(width, height);
            return this;
        }

        public EntityType.Builder<T> spawnDimensionsScale(float scale) {
            spawnDimensionsScale = scale;
            return this;
        }

        public EntityType.Builder<T> eyeHeight(float eyeHeight) {
            return this;
        }

        public EntityType.Builder<T> attach(EntityAttachment attachment, float x, float y, float z) {
            return this;
        }

        public EntityType.Builder<T> attach(EntityAttachment attachment, Vec3 point) {
            return this;
        }

        boolean pumpkinSummon = true;

        public EntityType.Builder<T> noSummon() {
            pumpkinSummon = false;
            return this;
        }

        boolean pumpkinSerialize = true;

        public EntityType.Builder<T> noSave() {
            pumpkinSerialize = false;
            return this;
        }

        public EntityType.Builder<T> fireImmune() {
            fireImmune = true;
            return this;
        }

        public EntityType.Builder<T> immuneTo(TagKey<Block> tag) {
            immuneTo = tag;
            return this;
        }

        public EntityType.Builder<T> canSpawnFarFromPlayer() {
            canSpawnFarFromPlayer = true;
            return this;
        }

        public EntityType.Builder<T> clientTrackingRange(int clientChunkRange) {
            clientTrackingRange = clientChunkRange;
            return this;
        }

        public EntityType.Builder<T> updateInterval(int updateInterval) {
            this.updateInterval = updateInterval;
            return this;
        }

        public EntityType.Builder<T> requiredFeatures(FeatureFlag... flags) {
            return this;
        }

        boolean pumpkinNoLootTable;

        public EntityType.Builder<T> noLootTable() {
            pumpkinNoLootTable = true;
            return this;
        }

        boolean pumpkinAllowedInPeaceful = true;

        public EntityType.Builder<T> notInPeaceful() {
            pumpkinAllowedInPeaceful = false;
            return this;
        }

        public EntityType.Builder<T> setUpdateInterval(int interval) {
            return this;
        }

        public EntityType.Builder<T> setTrackingRange(int range) {
            return this;
        }

        boolean pumpkinTrackDeltas = true;

        public EntityType.Builder<T> setShouldReceiveVelocityUpdates(boolean value) {
            pumpkinTrackDeltas = value;
            return this;
        }

        boolean pumpkinOnlyOpCanSetNbt;

        public EntityType.Builder<T> setOnlyOpCanSetNbt(boolean onlyOpCanSetNbt) {
            pumpkinOnlyOpCanSetNbt = onlyOpCanSetNbt;
            return this;
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        public EntityType<T> build(ResourceKey<EntityType<?>> name) {
            EntityType type = new EntityType();
            type.pumpkinCategory = pumpkinCategory;
            type.pumpkinSerialize = pumpkinSerialize;
            type.pumpkinSummon = pumpkinSummon;
            type.fireImmune = fireImmune;
            type.canSpawnFarFromPlayer = canSpawnFarFromPlayer;
            type.pumpkinOnlyOpCanSetNbt = pumpkinOnlyOpCanSetNbt;
            type.pumpkinAllowedInPeaceful = pumpkinAllowedInPeaceful;
            type.immuneTo = immuneTo;
            type.clientTrackingRange = clientTrackingRange;
            type.updateInterval = updateInterval;
            type.spawnDimensionsScale = spawnDimensionsScale;
            type.pumpkinTrackDeltas = pumpkinTrackDeltas;
            type.pumpkinDimensions = pumpkinDimensions;
            if (pumpkinNoLootTable) {
                type.pumpkinLootTable = Optional.empty();
            } else if (name != null) {
                type.pumpkinLootTable = Optional.of(net.minecraft.resources.ResourceKey.create(
                    net.minecraft.core.registries.Registries.LOOT_TABLE,
                    net.minecraft.resources.Identifier.fromNamespaceAndPath(
                        name.identifier().getNamespace(), "entities/" + name.identifier().getPath())));
            }
            return type;
        }

        public Builder() {
        }
    }

    public interface EntityFactory<T extends Entity> {

        T create(final EntityType<T> entityType, final Level level);
    }

    // Pumpkin divergence: no vanilla counterpart -- the name a vanilla stand-in from
    // EntityTypes carries.
    public String pumpkinVanillaName;

    public EntityType() {
    }
}
