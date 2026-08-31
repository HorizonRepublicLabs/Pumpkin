package net.minecraft.advancements.triggers;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import dev.pumpkin.shim.Unimplemented;

public class SummonedEntityTrigger extends SimpleCriterionTrigger<SummonedEntityTrigger.TriggerInstance> {

    public Codec<SummonedEntityTrigger.TriggerInstance> codec() {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/SummonedEntityTrigger.codec:()Lcom/mojang/serialization/Codec;");
    }

    public void trigger(ServerPlayer player, Entity entity) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/SummonedEntityTrigger.trigger:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/entity/Entity;)V");
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ContextAwarePredicate> entity) implements SimpleCriterionTrigger.SimpleInstance {

        public boolean matches(LootContext entity) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/SummonedEntityTrigger$TriggerInstance.matches:(Lnet/minecraft/world/level/storage/loot/LootContext;)Z");
        }

        public void validate(ValidationContextSource validator) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/SummonedEntityTrigger$TriggerInstance.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContextSource;)V");
        }
    }

    public SummonedEntityTrigger() {
    }
}
