package net.minecraft.core;

import dev.pumpkin.shim.Unimplemented;

public class Vec3i implements Comparable<Vec3i> {

    public Vec3i(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.<init>:(III)V");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.hashCode:()I");
    }

    public int compareTo(Vec3i pos) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.compareTo:(Lnet/minecraft/core/Vec3i;)I");
    }

    public int getX() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.getX:()I");
    }

    public int getY() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.getY:()I");
    }

    public int getZ() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.getZ:()I");
    }

    protected Vec3i setX(int x) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.setX:(I)Lnet/minecraft/core/Vec3i;");
    }

    protected Vec3i setY(int y) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.setY:(I)Lnet/minecraft/core/Vec3i;");
    }

    public int get(Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.get:(Lnet/minecraft/core/Direction$Axis;)I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.toString:()Ljava/lang/String;");
    }

    protected Vec3i() {
    }
}
