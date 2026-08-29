package net.minecraft.advancements.triggers;

import java.util.Optional;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import dev.pumpkin.shim.Unimplemented;

public abstract class SimpleCriterionTrigger<T extends SimpleCriterionTrigger.SimpleInstance> implements CriterionTrigger<T> {

    public interface SimpleInstance extends CriterionTriggerInstance {

        default void validate(ValidationContextSource validator) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/SimpleCriterionTrigger$SimpleInstance.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContextSource;)V");
        }

        Optional<ContextAwarePredicate> player();
    }

    protected SimpleCriterionTrigger() {
    }
}
