package net.minecraft.world.entity.ai.goal;

import java.util.EnumSet;
import dev.pumpkin.shim.Unimplemented;

public abstract class Goal {

    public abstract boolean canUse();

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/Goal.stop:()V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/Goal.tick:()V");
    }

    public void setFlags(EnumSet<Goal.Flag> requiredControlFlags) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/Goal.setFlags:(Ljava/util/EnumSet;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/Goal.toString:()Ljava/lang/String;");
    }

    public enum Flag {

        MOVE, LOOK, JUMP, TARGET
    }

    public Goal() {
    }
}
