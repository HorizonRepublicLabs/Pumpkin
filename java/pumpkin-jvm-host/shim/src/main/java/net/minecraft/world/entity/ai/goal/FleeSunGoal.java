package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import dev.pumpkin.shim.Unimplemented;

public class FleeSunGoal extends Goal {

    public FleeSunGoal(PathfinderMob mob, double speedModifier) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FleeSunGoal.canUse:()Z");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FleeSunGoal.canContinueToUse:()Z");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FleeSunGoal.start:()V");
    }

    public FleeSunGoal() {
    }
}
