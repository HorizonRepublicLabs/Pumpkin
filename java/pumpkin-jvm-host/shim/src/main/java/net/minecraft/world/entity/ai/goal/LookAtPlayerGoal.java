package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import dev.pumpkin.shim.Unimplemented;

public class LookAtPlayerGoal extends Goal {

    public LookAtPlayerGoal(Mob mob, Class<? extends LivingEntity> lookAtType, float lookDistance) {
    }

    public LookAtPlayerGoal(Mob mob, Class<? extends LivingEntity> lookAtType, float lookDistance, float probability) {
    }

    public LookAtPlayerGoal(Mob mob, Class<? extends LivingEntity> lookAtType, float lookDistance, float probability, boolean onlyHorizontal) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/LookAtPlayerGoal.canUse:()Z");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/LookAtPlayerGoal.canContinueToUse:()Z");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/LookAtPlayerGoal.start:()V");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/LookAtPlayerGoal.stop:()V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/LookAtPlayerGoal.tick:()V");
    }

    public LookAtPlayerGoal() {
    }
}
