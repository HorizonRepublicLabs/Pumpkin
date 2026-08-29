package net.minecraft.world.level.block;

import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum Rotation implements StringRepresentable {

    NONE, CLOCKWISE_90, CLOCKWISE_180, COUNTERCLOCKWISE_90;

    public Direction rotate(Direction direction) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Rotation.rotate:(Lnet/minecraft/core/Direction;)Lnet/minecraft/core/Direction;");
    }

    public int rotate(int rotation, int steps) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Rotation.rotate:(II)I");
    }

    public static Rotation getRandom(RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Rotation.getRandom:(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/level/block/Rotation;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Rotation.getSerializedName:()Ljava/lang/String;");
    }
}
