package net.minecraft.advancements.triggers;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.MobEffectsPredicate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import dev.pumpkin.shim.Unimplemented;

public class EffectsChangedTrigger extends SimpleCriterionTrigger<EffectsChangedTrigger.TriggerInstance> {

    public Codec<EffectsChangedTrigger.TriggerInstance> codec() {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/EffectsChangedTrigger.codec:()Lcom/mojang/serialization/Codec;");
    }

    public void trigger(ServerPlayer player, Entity source) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/EffectsChangedTrigger.trigger:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/entity/Entity;)V");
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<MobEffectsPredicate> effects, Optional<ContextAwarePredicate> source) implements SimpleCriterionTrigger.SimpleInstance {

        public boolean matches(ServerPlayer player, LootContext source) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/EffectsChangedTrigger$TriggerInstance.matches:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/level/storage/loot/LootContext;)Z");
        }

        public void validate(ValidationContextSource validator) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/EffectsChangedTrigger$TriggerInstance.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContextSource;)V");
        }
    }

    public EffectsChangedTrigger() {
    }
}
