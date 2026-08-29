package net.minecraft.world.level.block;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum Mirror implements StringRepresentable {

    NONE, LEFT_RIGHT, FRONT_BACK;

    public Rotation getRotation(Direction value) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Mirror.getRotation:(Lnet/minecraft/core/Direction;)Lnet/minecraft/world/level/block/Rotation;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Mirror.getSerializedName:()Ljava/lang/String;");
    }
}
