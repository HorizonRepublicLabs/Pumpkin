package net.minecraft.world.entity;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum HumanoidArm implements StringRepresentable {

    LEFT, RIGHT;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/HumanoidArm.getSerializedName:()Ljava/lang/String;");
    }
}
