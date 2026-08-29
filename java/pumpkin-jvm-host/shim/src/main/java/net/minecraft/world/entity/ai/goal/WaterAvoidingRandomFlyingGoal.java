package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class WaterAvoidingRandomFlyingGoal extends WaterAvoidingRandomStrollGoal {

    public WaterAvoidingRandomFlyingGoal(PathfinderMob mob, double speedModifier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/WaterAvoidingRandomFlyingGoal.<init>:(Lnet/minecraft/world/entity/PathfinderMob;D)V");
    }

    protected Vec3 getPosition() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/WaterAvoidingRandomFlyingGoal.getPosition:()Lnet/minecraft/world/phys/Vec3;");
    }

    public WaterAvoidingRandomFlyingGoal() {
    }
}
