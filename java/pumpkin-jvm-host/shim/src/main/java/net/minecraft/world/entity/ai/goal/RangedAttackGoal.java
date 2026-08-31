package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.monster.RangedAttackMob;
import dev.pumpkin.shim.Unimplemented;

public class RangedAttackGoal extends Goal {

    public RangedAttackGoal(RangedAttackMob mob, double speedModifier, int attackInterval, float attackRadius) {
    }

    public RangedAttackGoal(RangedAttackMob mob, double speedModifier, int attackIntervalMin, int attackIntervalMax, float attackRadius) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RangedAttackGoal.canUse:()Z");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RangedAttackGoal.canContinueToUse:()Z");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RangedAttackGoal.stop:()V");
    }

    public boolean requiresUpdateEveryTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RangedAttackGoal.requiresUpdateEveryTick:()Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RangedAttackGoal.tick:()V");
    }

    public RangedAttackGoal() {
    }
}
