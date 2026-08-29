package net.minecraft.advancements.triggers;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import dev.pumpkin.shim.Unimplemented;

public class ItemUsedOnLocationTrigger extends SimpleCriterionTrigger<ItemUsedOnLocationTrigger.TriggerInstance> {

    public Codec<ItemUsedOnLocationTrigger.TriggerInstance> codec() {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/ItemUsedOnLocationTrigger.codec:()Lcom/mojang/serialization/Codec;");
    }

    public void trigger(ServerPlayer player, BlockPos pos, ItemInstance tool) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/ItemUsedOnLocationTrigger.trigger:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemInstance;)V");
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ContextAwarePredicate> location) implements SimpleCriterionTrigger.SimpleInstance {

        public boolean matches(LootContext locationContext) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/ItemUsedOnLocationTrigger$TriggerInstance.matches:(Lnet/minecraft/world/level/storage/loot/LootContext;)Z");
        }

        public void validate(ValidationContextSource validator) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/ItemUsedOnLocationTrigger$TriggerInstance.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContextSource;)V");
        }
    }

    protected ItemUsedOnLocationTrigger() {
    }
}
