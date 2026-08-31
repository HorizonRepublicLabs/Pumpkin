package net.minecraft.world.phys;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.joml.Vector3f;
import dev.pumpkin.shim.Unimplemented;

public class AABB {

    public static final AABB INFINITE = null;

    // Pumpkin divergence: real fields -- a box is its bounds.
    public double minX, minY, minZ, maxX, maxY, maxZ;

    public AABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
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

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public AABB expandTowards(double xa, double ya, double za) {
        return new AABB(xa < 0.0 ? minX + xa : minX, ya < 0.0 ? minY + ya : minY, za < 0.0 ? minZ + za : minZ, xa > 0.0 ? maxX + xa : maxX, ya > 0.0 ? maxY + ya : maxY, za > 0.0 ? maxZ + za : maxZ);
    }

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public AABB inflate(double xAdd, double yAdd, double zAdd) {
        return new AABB(minX - xAdd, minY - yAdd, minZ - zAdd, maxX + xAdd, maxY + yAdd, maxZ + zAdd);
    }

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public AABB inflate(double amountToAddInAllDirections) {
        return inflate(amountToAddInAllDirections, amountToAddInAllDirections, amountToAddInAllDirections);
    }

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public AABB move(double xa, double ya, double za) {
        return new AABB(minX + xa, minY + ya, minZ + za, maxX + xa, maxY + ya, maxZ + za);
    }

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public AABB move(BlockPos pos) {
        return move(pos.getX(), pos.getY(), pos.getZ());
    }

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public AABB move(Vec3 pos) {
        return move(pos.x, pos.y, pos.z);
    }

    public AABB move(Vector3f pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.move:(Lorg/joml/Vector3f;)Lnet/minecraft/world/phys/AABB;");
    }

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public boolean intersects(AABB aabb) {
        return intersects(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
    }

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public boolean intersects(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return this.minX < maxX && this.maxX > minX && this.minY < maxY && this.maxY > minY && this.minZ < maxZ && this.maxZ > minZ;
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

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public boolean contains(double x, double y, double z) {
        return x >= minX && x < maxX && y >= minY && y < maxY && z >= minZ && z < maxZ;
    }

    public double getSize() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.getSize:()D");
    }

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public double getXsize() {
        return maxX - minX;
    }

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public double getYsize() {
        return maxY - minY;
    }

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public double getZsize() {
        return maxZ - minZ;
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

    // Pumpkin divergence: vanilla arithmetic over the real bounds.
    public Vec3 getCenter() {
        return new Vec3((minX + maxX) / 2.0, (minY + maxY) / 2.0, (minZ + maxZ) / 2.0);
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
