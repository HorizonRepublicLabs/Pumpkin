package net.minecraft.advancements.triggers;

import dev.pumpkin.shim.Unimplemented;

public class CriteriaTriggers {

    public static final KilledTrigger PLAYER_KILLED_ENTITY = null;

    public static final SummonedEntityTrigger SUMMONED_ENTITY = null;

    public static final EffectsChangedTrigger EFFECTS_CHANGED = null;

    public static final ItemUsedOnLocationTrigger ITEM_USED_ON_BLOCK = null;

    public static final UsingItemTrigger USING_ITEM = null;

    public static <T extends CriterionTrigger<?>> T register(String name, T criterion) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/CriteriaTriggers.register:(Ljava/lang/String;Lnet/minecraft/advancements/triggers/CriterionTrigger;)Lnet/minecraft/advancements/triggers/CriterionTrigger;");
    }

    public CriteriaTriggers() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/CriteriaTriggers");
        }
    }
}
