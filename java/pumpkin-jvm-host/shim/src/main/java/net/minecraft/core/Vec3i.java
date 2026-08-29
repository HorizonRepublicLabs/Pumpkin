package net.minecraft.core;

import dev.pumpkin.shim.Unimplemented;

public class Vec3i implements Comparable<Vec3i> {

    public Vec3i(int x, int y, int z) {
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

    public Vec3i offset(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.offset:(III)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i offset(Vec3i vec) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.offset:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i above() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.above:()Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i above(int steps) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.above:(I)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i below() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.below:()Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i below(int steps) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.below:(I)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i relative(Direction direction) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.relative:(Lnet/minecraft/core/Direction;)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i relative(Direction direction, int steps) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.relative:(Lnet/minecraft/core/Direction;I)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i relative(Direction.Axis axis, int steps) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.relative:(Lnet/minecraft/core/Direction$Axis;I)Lnet/minecraft/core/Vec3i;");
    }

    public int get(Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.get:(Lnet/minecraft/core/Direction$Axis;)I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.toString:()Ljava/lang/String;");
    }

    public Vec3i() {
    }
}
