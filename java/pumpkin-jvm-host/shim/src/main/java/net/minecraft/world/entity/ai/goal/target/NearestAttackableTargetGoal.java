package net.minecraft.world.entity.ai.goal.target;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import dev.pumpkin.shim.Unimplemented;

public class NearestAttackableTargetGoal<T extends LivingEntity> extends TargetGoal {

    public NearestAttackableTargetGoal(Mob mob, Class<T> targetType, boolean mustSee) {
    }

    public NearestAttackableTargetGoal(Mob mob, Class<T> targetType, boolean mustSee, TargetingConditions.Selector selector) {
    }

    public NearestAttackableTargetGoal(Mob mob, Class<T> targetType, boolean mustSee, boolean mustReach) {
    }

    public NearestAttackableTargetGoal(Mob mob, Class<T> targetType, int randomInterval, boolean mustSee, boolean mustReach, TargetingConditions.Selector selector) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/target/NearestAttackableTargetGoal.canUse:()Z");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/target/NearestAttackableTargetGoal.start:()V");
    }

    public void setTarget(LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/target/NearestAttackableTargetGoal.setTarget:(Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    public NearestAttackableTargetGoal() {
    }
}
