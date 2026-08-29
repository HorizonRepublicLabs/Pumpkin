package net.minecraft.world.level.storage.loot;

import java.util.Optional;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.level.block.entity.BlockEntity;
import dev.pumpkin.shim.Unimplemented;

public class LootContext {

    private LootContext(LootParams params, RandomSource random, HolderGetter.Provider lootDataResolver) {
    }

    public RandomSource getRandom() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext.getRandom:()Lnet/minecraft/util/RandomSource;");
    }

    public ServerLevel getLevel() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public enum BlockEntityTarget implements StringRepresentable, LootContextArg.SimpleGetter<BlockEntity> {

        BLOCK_ENTITY;

        public ContextKey<? extends BlockEntity> contextParam() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext$BlockEntityTarget.contextParam:()Lnet/minecraft/util/context/ContextKey;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext$BlockEntityTarget.getSerializedName:()Ljava/lang/String;");
        }
    }

    private LootContext(LootParams params, RandomSource random, HolderGetter.Provider p_287619_, Identifier queriedLootTableId) {
    }

    public static class Builder {

        public Builder(LootParams params) {
        }

        public Builder(LootContext context) {
        }

        public ServerLevel getLevel() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext$Builder.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
        }

        public LootContext create(Optional<Identifier> randomSequenceKey) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext$Builder.create:(Ljava/util/Optional;)Lnet/minecraft/world/level/storage/loot/LootContext;");
        }

        public Builder() {
        }
    }

    public enum EntityTarget implements StringRepresentable, LootContextArg.SimpleGetter<Entity> {

        THIS,
        ATTACKER,
        DIRECT_ATTACKER,
        ATTACKING_PLAYER,
        TARGET_ENTITY,
        INTERACTING_ENTITY;

        public ContextKey<? extends Entity> contextParam() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext$EntityTarget.contextParam:()Lnet/minecraft/util/context/ContextKey;");
        }

        public String getName() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext$EntityTarget.getName:()Ljava/lang/String;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext$EntityTarget.getSerializedName:()Ljava/lang/String;");
        }
    }

    public enum ItemStackTarget implements StringRepresentable, LootContextArg.SimpleGetter<ItemInstance> {

        TOOL;

        public ContextKey<? extends ItemInstance> contextParam() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext$ItemStackTarget.contextParam:()Lnet/minecraft/util/context/ContextKey;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContext$ItemStackTarget.getSerializedName:()Ljava/lang/String;");
        }
    }

    public record VisitedEntry<T extends Validatable>(LootDataType<T> type, T value) {
    }

    public LootContext() {
    }
}
