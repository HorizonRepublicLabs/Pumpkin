package net.minecraft.world.phys.shapes;

import net.minecraft.world.phys.AABB;
import dev.pumpkin.shim.Unimplemented;

public final class Shapes {

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

    public interface DoubleLineConsumer {

        void consume(double x1, double y1, double z1, double x2, double y2, double z2);
    }

    public Shapes() {
    }
}
