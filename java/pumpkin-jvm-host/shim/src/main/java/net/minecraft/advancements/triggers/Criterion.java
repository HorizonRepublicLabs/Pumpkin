package net.minecraft.advancements.triggers;

import net.minecraft.advancements.CriterionTriggerInstance;

public record Criterion<T extends CriterionTriggerInstance>(CriterionTrigger<T> trigger, T triggerInstance) {
}
