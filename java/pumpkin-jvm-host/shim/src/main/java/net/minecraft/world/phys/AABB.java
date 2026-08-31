package net.minecraft.world.phys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.joml.Vector3f;
import dev.pumpkin.shim.Unimplemented;

public class AABB {

    public static final AABB INFINITE = null;

    public final double minX = 0.0;

    public final double minY = 0.0;

    public final double minZ = 0.0;

    public final double maxX = 0.0;

    public final double maxY = 0.0;

    public final double maxZ = 0.0;

    public AABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
    }

    public AABB(BlockPos pos) {
    }

    public AABB(Vec3 begin, Vec3 end) {
    }

    public static AABB encapsulatingFullBlocks(BlockPos pos0, BlockPos pos1) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.encapsulatingFullBlocks:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB setMinY(double minY) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.setMinY:(D)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB setMaxY(double maxY) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.setMaxY:(D)Lnet/minecraft/world/phys/AABB;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.hashCode:()I");
    }

    public AABB expandTowards(Vec3 delta) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.expandTowards:(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB expandTowards(double xa, double ya, double za) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.expandTowards:(DDD)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB inflate(double xAdd, double yAdd, double zAdd) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.inflate:(DDD)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB inflate(double amountToAddInAllDirections) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.inflate:(D)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB move(double xa, double ya, double za) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.move:(DDD)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB move(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.move:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB move(Vec3 pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.move:(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB move(Vector3f pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.move:(Lorg/joml/Vector3f;)Lnet/minecraft/world/phys/AABB;");
    }

    public boolean intersects(AABB aabb) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.intersects:(Lnet/minecraft/world/phys/AABB;)Z");
    }

    public boolean intersects(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.intersects:(DDDDDD)Z");
    }

    public boolean intersects(Vec3 min, Vec3 max) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.intersects:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;)Z");
    }

    public boolean intersects(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.intersects:(Lnet/minecraft/core/BlockPos;)Z");
    }

    public boolean contains(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.contains:(Lnet/minecraft/world/phys/Vec3;)Z");
    }

    public boolean contains(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.contains:(DDD)Z");
    }

    public double getSize() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.getSize:()D");
    }

    public double getXsize() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.getXsize:()D");
    }

    public double getYsize() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.getYsize:()D");
    }

    public double getZsize() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.getZsize:()D");
    }

    public AABB deflate(double xSubstract, double ySubtract, double zSubtract) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.deflate:(DDD)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB deflate(double amount) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.deflate:(D)Lnet/minecraft/world/phys/AABB;");
    }

    private static Direction getDirection(AABB aabb, Vec3 from, double[] scaleReference, Direction direction, double dx, double dy, double dz) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.getDirection:(Lnet/minecraft/world/phys/AABB;Lnet/minecraft/world/phys/Vec3;[DLnet/minecraft/core/Direction;DDD)Lnet/minecraft/core/Direction;");
    }

    private static Direction getDirection(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, Vec3 from, double[] scaleReference, Direction direction, double dx, double dy, double dz) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.getDirection:(DDDDDDLnet/minecraft/world/phys/Vec3;[DLnet/minecraft/core/Direction;DDD)Lnet/minecraft/core/Direction;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.toString:()Ljava/lang/String;");
    }

    public Vec3 getCenter() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.getCenter:()Lnet/minecraft/world/phys/Vec3;");
    }

    public static AABB ofSize(Vec3 center, double sizeX, double sizeY, double sizeZ) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.ofSize:(Lnet/minecraft/world/phys/Vec3;DDD)Lnet/minecraft/world/phys/AABB;");
    }

    public static class Builder {

        public AABB build() {
            throw Unimplemented.forMember("net/minecraft/world/phys/AABB$Builder.build:()Lnet/minecraft/world/phys/AABB;");
        }

        public Builder() {
        }
    }

    public AABB() {
    }
}
