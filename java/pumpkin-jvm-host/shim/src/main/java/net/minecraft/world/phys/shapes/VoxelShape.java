package net.minecraft.world.phys.shapes;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class VoxelShape {

    protected VoxelShape(DiscreteVoxelShape shape) {
    }

    public double max(Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.max:(Lnet/minecraft/core/Direction$Axis;)D");
    }

    public AABB bounds() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.bounds:()Lnet/minecraft/world/phys/AABB;");
    }

    protected double get(Direction.Axis axis, int i) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.get:(Lnet/minecraft/core/Direction$Axis;I)D");
    }

    public abstract DoubleList getCoords(final Direction.Axis axis);

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.isEmpty:()Z");
    }

    // Pumpkin divergence: real over known boxes -- shift each; unknown geometry stays loud.
    public VoxelShape move(Vec3 delta) {
        if (pumpkinBoxes == null) {
            throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move (a shape with unknown geometry)");
        }
        java.util.List<net.minecraft.world.phys.AABB> moved = new java.util.ArrayList<>();
        for (net.minecraft.world.phys.AABB box : pumpkinBoxes) {
            moved.add(box.move(delta));
        }
        return pumpkinOfBoxes(moved);
    }

    public VoxelShape move(Vec3i delta) {
        return move(delta.getX(), delta.getY(), delta.getZ());
    }

    // Pumpkin divergence: real over known boxes -- shift each; unknown geometry stays loud.
    public VoxelShape move(double dx, double dy, double dz) {
        if (pumpkinBoxes == null) {
            throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move (a shape with unknown geometry)");
        }
        java.util.List<net.minecraft.world.phys.AABB> moved = new java.util.ArrayList<>();
        for (net.minecraft.world.phys.AABB box : pumpkinBoxes) {
            moved.add(box.move(dx, dy, dz));
        }
        return pumpkinOfBoxes(moved);
    }

    // Pumpkin divergence: real-enough body -- optimizing an inert shape is the shape.
    public VoxelShape optimize() {
        return this;
    }

    public void forAllBoxes(Shapes.DoubleLineConsumer consumer) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.forAllBoxes:(Lnet/minecraft/world/phys/shapes/Shapes$DoubleLineConsumer;)V");
    }

    // Pumpkin divergence: real where the shape was built from boxes -- the mod's own
    // numbers coming back out. A shape with unknown geometry still fails loudly.
    java.util.List<net.minecraft.world.phys.AABB> pumpkinBoxes;

    public List<AABB> toAabbs() {
        if (pumpkinBoxes == null) {
            throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.toAabbs:()Ljava/util/List; (a shape with unknown geometry)");
        }
        return pumpkinBoxes;
    }

    // Pumpkin divergence: no vanilla counterpart -- an inert shape that knows its boxes.
    public static VoxelShape pumpkinOfBoxes(java.util.List<net.minecraft.world.phys.AABB> boxes) {
        VoxelShape shape = pumpkinInert();
        shape.pumpkinBoxes = java.util.List.copyOf(boxes);
        return shape;
    }

    public double max(Direction.Axis aAxis, double b, double c) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.max:(Lnet/minecraft/core/Direction$Axis;DD)D");
    }

    public BlockHitResult clip(Vec3 from, Vec3 to, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.clip:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/BlockHitResult;");
    }

    public VoxelShape getFaceShape(Direction direction) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.getFaceShape:(Lnet/minecraft/core/Direction;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.equals:(Ljava/lang/Object;)Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.toString:()Ljava/lang/String;");
    }

    // Pumpkin divergence: no vanilla counterpart. The inert shape every shape-building
    // helper returns -- geometry Pumpkin never consults, whose one abstract member throws
    // with a name if anything ever reads it.
    public static VoxelShape pumpkinInert() {
        return new VoxelShape() {
            @Override
            public DoubleList getCoords(Direction.Axis axis) {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.getCoords:(Lnet/minecraft/core/Direction$Axis;)Lit/unimi/dsi/fastutil/doubles/DoubleList;");
            }
        };
    }

    public VoxelShape() {
    }
}
