package net.minecraft.world.phys;

import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public class AABB {

    public static final AABB INFINITE = null;

    public AABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.<init>:(DDDDDD)V");
    }

    public AABB(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.<init>:(Lnet/minecraft/core/BlockPos;)V");
    }

    public AABB(Vec3 begin, Vec3 end) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.<init>:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;)V");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.hashCode:()I");
    }

    public AABB inflate(double xAdd, double yAdd, double zAdd) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.inflate:(DDD)Lnet/minecraft/world/phys/AABB;");
    }

    public AABB inflate(double amountToAddInAllDirections) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.inflate:(D)Lnet/minecraft/world/phys/AABB;");
    }

    public boolean contains(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.contains:(Lnet/minecraft/world/phys/Vec3;)Z");
    }

    public double getSize() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.getSize:()D");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.toString:()Ljava/lang/String;");
    }

    public static AABB ofSize(Vec3 center, double sizeX, double sizeY, double sizeZ) {
        throw Unimplemented.forMember("net/minecraft/world/phys/AABB.ofSize:(Lnet/minecraft/world/phys/Vec3;DDD)Lnet/minecraft/world/phys/AABB;");
    }

    public static class Builder {

        public AABB build() {
            throw Unimplemented.forMember("net/minecraft/world/phys/AABB$Builder.build:()Lnet/minecraft/world/phys/AABB;");
        }

        protected Builder() {
        }
    }

    protected AABB() {
    }
}
