package net.minecraft.advancements.triggers;

import dev.pumpkin.shim.Unimplemented;

public class CriteriaTriggers {

    public static final ItemUsedOnLocationTrigger ITEM_USED_ON_BLOCK = null;

    public static <T extends CriterionTrigger<?>> T register(String name, T criterion) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/CriteriaTriggers.register:(Ljava/lang/String;Lnet/minecraft/advancements/triggers/CriterionTrigger;)Lnet/minecraft/advancements/triggers/CriterionTrigger;");
    }

    protected CriteriaTriggers() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/CriteriaTriggers");
        }
    }
}
