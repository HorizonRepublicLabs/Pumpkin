package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import dev.pumpkin.shim.Unimplemented;

public class StrollThroughVillageGoal extends Goal {

    public StrollThroughVillageGoal(PathfinderMob mob, int interval) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/StrollThroughVillageGoal.canUse:()Z");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/StrollThroughVillageGoal.canContinueToUse:()Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/StrollThroughVillageGoal.tick:()V");
    }

    public StrollThroughVillageGoal() {
    }
}
