package net.minecraft.advancements.triggers;

import com.mojang.serialization.Codec;
import net.minecraft.advancements.CriterionTriggerInstance;

public interface CriterionTrigger<T extends CriterionTriggerInstance> {

    Codec<T> codec();
}
