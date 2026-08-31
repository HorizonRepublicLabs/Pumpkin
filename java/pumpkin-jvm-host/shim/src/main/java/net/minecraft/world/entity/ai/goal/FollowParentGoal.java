package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.animal.Animal;
import dev.pumpkin.shim.Unimplemented;

public class FollowParentGoal extends Goal {

    public FollowParentGoal(Animal animal, double speedModifier) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FollowParentGoal.canUse:()Z");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FollowParentGoal.canContinueToUse:()Z");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FollowParentGoal.start:()V");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FollowParentGoal.stop:()V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FollowParentGoal.tick:()V");
    }

    public FollowParentGoal() {
    }
}
