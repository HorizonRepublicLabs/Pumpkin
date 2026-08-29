package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import dev.pumpkin.shim.Unimplemented;

public class RandomStrollGoal extends Goal {

    public RandomStrollGoal(PathfinderMob mob, double speedModifier) {
    }

    public RandomStrollGoal(PathfinderMob mob, double speedModifier, int interval) {
    }

    public RandomStrollGoal(PathfinderMob mob, double speedModifier, int interval, boolean checkNoActionTime) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RandomStrollGoal.canUse:()Z");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RandomStrollGoal.canContinueToUse:()Z");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RandomStrollGoal.start:()V");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RandomStrollGoal.stop:()V");
    }

    public RandomStrollGoal() {
    }
}
