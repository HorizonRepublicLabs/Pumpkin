package net.minecraft.world.entity.ai.goal;

import dev.pumpkin.shim.Unimplemented;

public abstract class Goal {

    public abstract boolean canUse();

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/Goal.tick:()V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/Goal.toString:()Ljava/lang/String;");
    }

    public enum Flag {

        MOVE, LOOK, JUMP, TARGET
    }

    protected Goal() {
    }
}
