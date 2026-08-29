package net.minecraft.world.phys.shapes;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import dev.pumpkin.shim.Unimplemented;

public abstract class VoxelShape {

    protected VoxelShape(DiscreteVoxelShape shape) {
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
