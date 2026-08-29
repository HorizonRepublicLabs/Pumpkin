package net.minecraft.world.entity;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum Pose implements StringRepresentable {

    STANDING,
    FALL_FLYING,
    SLEEPING,
    SWIMMING,
    SPIN_ATTACK,
    CROUCHING,
    LONG_JUMPING,
    DYING,
    CROAKING,
    USING_TONGUE,
    SITTING,
    ROARING,
    SNIFFING,
    EMERGING,
    DIGGING,
    SLIDING,
    SHOOTING,
    INHALING;

    public int id() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Pose.id:()I");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Pose.getSerializedName:()Ljava/lang/String;");
    }
}
