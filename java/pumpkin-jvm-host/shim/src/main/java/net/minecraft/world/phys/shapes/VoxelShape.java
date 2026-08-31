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

    public VoxelShape move(Vec3 delta) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move:(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public VoxelShape move(Vec3i delta) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public VoxelShape move(double dx, double dy, double dz) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move:(DDD)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public VoxelShape optimize() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.optimize:()Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public void forAllBoxes(Shapes.DoubleLineConsumer consumer) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.forAllBoxes:(Lnet/minecraft/world/phys/shapes/Shapes$DoubleLineConsumer;)V");
    }

    public List<AABB> toAabbs() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.toAabbs:()Ljava/util/List;");
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
