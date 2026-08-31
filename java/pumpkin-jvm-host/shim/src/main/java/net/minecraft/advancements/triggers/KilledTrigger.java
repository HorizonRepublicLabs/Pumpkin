package net.minecraft.advancements.triggers;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.DamageSourcePredicate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import dev.pumpkin.shim.Unimplemented;

public class KilledTrigger extends SimpleCriterionTrigger<KilledTrigger.TriggerInstance> {

    public Codec<KilledTrigger.TriggerInstance> codec() {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/KilledTrigger.codec:()Lcom/mojang/serialization/Codec;");
    }

    public void trigger(ServerPlayer player, Entity entity, DamageSource killingBlow) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/KilledTrigger.trigger:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ContextAwarePredicate> entity, Optional<DamageSourcePredicate> killingBlow) implements SimpleCriterionTrigger.SimpleInstance {

        public boolean matches(ServerPlayer player, LootContext entity, DamageSource killingBlow) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/KilledTrigger$TriggerInstance.matches:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/level/storage/loot/LootContext;Lnet/minecraft/world/damagesource/DamageSource;)Z");
        }

        public void validate(ValidationContextSource validator) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/KilledTrigger$TriggerInstance.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContextSource;)V");
        }
    }

    public KilledTrigger() {
    }
}
