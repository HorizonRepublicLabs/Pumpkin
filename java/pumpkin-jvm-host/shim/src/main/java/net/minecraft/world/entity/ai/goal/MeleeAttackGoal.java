package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import dev.pumpkin.shim.Unimplemented;

public class MeleeAttackGoal extends Goal {

    public MeleeAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MeleeAttackGoal.canUse:()Z");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MeleeAttackGoal.canContinueToUse:()Z");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MeleeAttackGoal.start:()V");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MeleeAttackGoal.stop:()V");
    }

    public boolean requiresUpdateEveryTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MeleeAttackGoal.requiresUpdateEveryTick:()Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MeleeAttackGoal.tick:()V");
    }

    public MeleeAttackGoal() {
    }
}
