package net.minecraft.world.entity.ai.goal.target;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import dev.pumpkin.shim.Unimplemented;

public abstract class TargetGoal extends Goal {

    public TargetGoal(Mob mob, boolean mustSee) {
    }

    public TargetGoal(Mob mob, boolean mustSee, boolean mustReach) {
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/target/TargetGoal.canContinueToUse:()Z");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/target/TargetGoal.start:()V");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/target/TargetGoal.stop:()V");
    }

    public TargetGoal() {
    }
}
