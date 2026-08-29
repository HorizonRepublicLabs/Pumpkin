package net.minecraft.world.phys.shapes;

import net.minecraft.world.phys.AABB;
import dev.pumpkin.shim.Unimplemented;

public final class Shapes {

    public static VoxelShape block() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.block:()Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.box:(DDDDDD)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public static VoxelShape create(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.create:(DDDDDD)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public static VoxelShape create(AABB aabb) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.create:(Lnet/minecraft/world/phys/AABB;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public static VoxelShape or(VoxelShape first, VoxelShape second) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.or:(Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/VoxelShape;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public static VoxelShape or(VoxelShape first, VoxelShape... tail) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.or:(Lnet/minecraft/world/phys/shapes/VoxelShape;[Lnet/minecraft/world/phys/shapes/VoxelShape;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public interface DoubleLineConsumer {

        void consume(double x1, double y1, double z1, double x2, double y2, double z2);
    }

    public Shapes() {
    }
}
