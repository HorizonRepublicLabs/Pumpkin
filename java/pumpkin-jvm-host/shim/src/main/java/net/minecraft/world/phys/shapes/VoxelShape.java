package net.minecraft.world.phys.shapes;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import dev.pumpkin.shim.Unimplemented;

public abstract class VoxelShape {

    protected VoxelShape(DiscreteVoxelShape shape) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.<init>:(Lnet/minecraft/world/phys/shapes/DiscreteVoxelShape;)V");
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

    public VoxelShape() {
    }
}
