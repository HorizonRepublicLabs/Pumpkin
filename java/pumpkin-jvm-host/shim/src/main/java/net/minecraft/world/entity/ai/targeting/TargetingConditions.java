package net.minecraft.world.entity.ai.targeting;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import dev.pumpkin.shim.Unimplemented;

public class TargetingConditions {

    private TargetingConditions(boolean isCombat) {
    }

    public TargetingConditions copy() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/targeting/TargetingConditions.copy:()Lnet/minecraft/world/entity/ai/targeting/TargetingConditions;");
    }

    public boolean test(ServerLevel level, LivingEntity targeter, LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/targeting/TargetingConditions.test:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)Z");
    }

    public interface Selector {

        boolean test(LivingEntity target, ServerLevel level);
    }

    public TargetingConditions() {
    }
}
