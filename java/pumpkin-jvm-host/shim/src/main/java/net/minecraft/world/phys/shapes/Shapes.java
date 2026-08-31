package net.minecraft.world.phys.shapes;

import net.minecraft.world.phys.AABB;
import dev.pumpkin.shim.Unimplemented;

public final class Shapes {

    public static VoxelShape empty() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.empty:()Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.

    public static VoxelShape block() {

        return VoxelShape.pumpkinInert();

    }

    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.

    public static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {

        return VoxelShape.pumpkinInert();

    }

    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.

    public static VoxelShape create(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {

        return VoxelShape.pumpkinInert();

    }

    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.

    public static VoxelShape create(AABB aabb) {

        return VoxelShape.pumpkinInert();

    }

    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.

    public static VoxelShape or(VoxelShape first, VoxelShape second) {

        return VoxelShape.pumpkinInert();

    }

    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.

    public static VoxelShape or(VoxelShape first, VoxelShape... tail) {

        return VoxelShape.pumpkinInert();

    }

    public static VoxelShape joinUnoptimized(VoxelShape first, VoxelShape second, BooleanOp op) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.joinUnoptimized:(Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/BooleanOp;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public static boolean joinIsNotEmpty(VoxelShape first, VoxelShape second, BooleanOp op) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.joinIsNotEmpty:(Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/BooleanOp;)Z");
    }

    private static boolean joinIsNotEmpty(IndexMerger xMerger, IndexMerger yMerger, IndexMerger zMerger, DiscreteVoxelShape first, DiscreteVoxelShape second, BooleanOp op) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.joinIsNotEmpty:(Lnet/minecraft/world/phys/shapes/IndexMerger;Lnet/minecraft/world/phys/shapes/IndexMerger;Lnet/minecraft/world/phys/shapes/IndexMerger;Lnet/minecraft/world/phys/shapes/DiscreteVoxelShape;Lnet/minecraft/world/phys/shapes/DiscreteVoxelShape;Lnet/minecraft/world/phys/shapes/BooleanOp;)Z");
    }

    public interface DoubleLineConsumer {

        void consume(double x1, double y1, double z1, double x2, double y2, double z2);
    }

    public Shapes() {
    }
}
