package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.Mob;
import dev.pumpkin.shim.Unimplemented;

public class RandomLookAroundGoal extends Goal {

    public RandomLookAroundGoal(Mob mob) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RandomLookAroundGoal.canUse:()Z");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RandomLookAroundGoal.canContinueToUse:()Z");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RandomLookAroundGoal.start:()V");
    }

    public boolean requiresUpdateEveryTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RandomLookAroundGoal.requiresUpdateEveryTick:()Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RandomLookAroundGoal.tick:()V");
    }

    public RandomLookAroundGoal() {
    }
}
