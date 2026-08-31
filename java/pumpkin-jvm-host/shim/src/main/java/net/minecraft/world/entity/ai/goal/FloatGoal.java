package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.Mob;
import dev.pumpkin.shim.Unimplemented;

public class FloatGoal extends Goal {

    public FloatGoal(Mob mob) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FloatGoal.canUse:()Z");
    }

    public boolean requiresUpdateEveryTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FloatGoal.requiresUpdateEveryTick:()Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/FloatGoal.tick:()V");
    }

    public FloatGoal() {
    }
}
