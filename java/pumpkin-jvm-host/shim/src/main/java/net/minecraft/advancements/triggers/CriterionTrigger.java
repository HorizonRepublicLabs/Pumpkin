package net.minecraft.advancements.triggers;

import com.mojang.serialization.Codec;
import net.minecraft.advancements.CriterionTriggerInstance;
import dev.pumpkin.shim.Unimplemented;

public interface CriterionTrigger<T extends CriterionTriggerInstance> {

    Codec<T> codec();

    default Criterion<T> createCriterion(T instance) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/CriterionTrigger.createCriterion:(Lnet/minecraft/advancements/CriterionTriggerInstance;)Lnet/minecraft/advancements/triggers/Criterion;");
    }
}
