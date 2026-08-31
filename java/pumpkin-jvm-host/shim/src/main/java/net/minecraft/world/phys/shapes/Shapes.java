package net.minecraft.world.phys.shapes;

import net.minecraft.world.phys.AABB;
import dev.pumpkin.shim.Unimplemented;

public final class Shapes {

    private static final VoxelShape PUMPKIN_EMPTY =
            VoxelShape.pumpkinOfBoxes(java.util.List.of());

    private static final VoxelShape PUMPKIN_BLOCK = VoxelShape.pumpkinOfBoxes(
            java.util.List.of(new net.minecraft.world.phys.AABB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)));


    // Pumpkin divergence: a real shared instance -- geometry lives on the Rust side,
    // and mods mostly carry these around; anything deeper fails loudly on its member.
    public static VoxelShape empty() {
        return PUMPKIN_EMPTY;
    }

    // Pumpkin divergence: real -- the full cube.
    public static VoxelShape block() {
        return PUMPKIN_BLOCK;
    }

    // Pumpkin divergence: real -- one box carrying the mod's own numbers.
    public static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return VoxelShape.pumpkinOfBoxes(java.util.List.of(
                new net.minecraft.world.phys.AABB(minX, minY, minZ, maxX, maxY, maxZ)));
    }

    public static VoxelShape create(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return box(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public static VoxelShape create(AABB aabb) {
        return VoxelShape.pumpkinOfBoxes(java.util.List.of(aabb));
    }

    // Pumpkin divergence: real union where both sides know their boxes -- the union of
    // box lists is their concatenation (unsimplified, which toAabbs permits). A side
    // with unknown geometry keeps the result loud.
    public static VoxelShape or(VoxelShape first, VoxelShape second) {
        if (first.pumpkinBoxes != null && second.pumpkinBoxes != null) {
            java.util.List<AABB> joined = new java.util.ArrayList<>(first.pumpkinBoxes);
            joined.addAll(second.pumpkinBoxes);
            return VoxelShape.pumpkinOfBoxes(joined);
        }
        return VoxelShape.pumpkinInert();
    }

    public static VoxelShape or(VoxelShape first, VoxelShape... tail) {
        VoxelShape result = first;
        for (VoxelShape shape : tail) {
            result = or(result, shape);
        }
        return result;
    }

    // Pumpkin divergence: the OR case is a real union; any other operation on shapes is
    // geometry this shim does not compute, and stays loud.
    public static VoxelShape joinUnoptimized(VoxelShape first, VoxelShape second, BooleanOp op) {
        if (op == BooleanOp.OR) {
            return or(first, second);
        }
        return VoxelShape.pumpkinInert();
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
