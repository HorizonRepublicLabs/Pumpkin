package net.minecraft.advancements.triggers;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class UsingItemTrigger extends SimpleCriterionTrigger<UsingItemTrigger.TriggerInstance> {

    public Codec<UsingItemTrigger.TriggerInstance> codec() {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/UsingItemTrigger.codec:()Lcom/mojang/serialization/Codec;");
    }

    public void trigger(ServerPlayer player, ItemStack item) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/UsingItemTrigger.trigger:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> item) implements SimpleCriterionTrigger.SimpleInstance {

        public boolean matches(ItemStack item) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/UsingItemTrigger$TriggerInstance.matches:(Lnet/minecraft/world/item/ItemStack;)Z");
        }
    }

    public UsingItemTrigger() {
    }
}
