package net.minecraft.advancements.triggers;

import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import dev.pumpkin.shim.Unimplemented;

public abstract class SimpleCriterionTrigger<T extends SimpleCriterionTrigger.SimpleInstance> implements CriterionTrigger<T> {

    protected void trigger(ServerPlayer player, Predicate<T> matcher) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/SimpleCriterionTrigger.trigger:(Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Predicate;)V");
    }

    public interface SimpleInstance extends CriterionTriggerInstance {

        default void validate(ValidationContextSource validator) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/SimpleCriterionTrigger$SimpleInstance.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContextSource;)V");
        }

        Optional<ContextAwarePredicate> player();
    }

    protected SimpleCriterionTrigger() {
    }
}
