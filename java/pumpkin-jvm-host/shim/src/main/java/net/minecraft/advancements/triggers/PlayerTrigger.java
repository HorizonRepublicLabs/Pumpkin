package net.minecraft.advancements.triggers;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.server.level.ServerPlayer;
import dev.pumpkin.shim.Unimplemented;

public class PlayerTrigger extends SimpleCriterionTrigger<PlayerTrigger.TriggerInstance> {

    public Codec<PlayerTrigger.TriggerInstance> codec() {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/PlayerTrigger.codec:()Lcom/mojang/serialization/Codec;");
    }

    public void trigger(ServerPlayer player) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/PlayerTrigger.trigger:(Lnet/minecraft/server/level/ServerPlayer;)V");
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

        public static Criterion<PlayerTrigger.TriggerInstance> tick() {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/PlayerTrigger$TriggerInstance.tick:()Lnet/minecraft/advancements/triggers/Criterion;");
        }
    }

    public PlayerTrigger() {
    }
}
