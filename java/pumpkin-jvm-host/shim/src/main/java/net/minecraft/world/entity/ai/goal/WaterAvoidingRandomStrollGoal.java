package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class WaterAvoidingRandomStrollGoal extends RandomStrollGoal {

    public WaterAvoidingRandomStrollGoal(PathfinderMob mob, double speedModifier) {
    }

    public WaterAvoidingRandomStrollGoal(PathfinderMob mob, double speedModifier, float probability) {
    }

    protected Vec3 getPosition() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/WaterAvoidingRandomStrollGoal.getPosition:()Lnet/minecraft/world/phys/Vec3;");
    }

    public WaterAvoidingRandomStrollGoal() {
    }
}
