package net.minecraft.world.phys.shapes;

import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public abstract class DiscreteVoxelShape {

    protected DiscreteVoxelShape(int xSize, int ySize, int zSize) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/DiscreteVoxelShape.<init>:(III)V");
    }

    public abstract boolean isFull(final int x, final int y, final int z);

    public abstract void fill(final int x, final int y, final int z);

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/DiscreteVoxelShape.isEmpty:()Z");
    }

    public abstract int firstFull(final Direction.Axis axis);

    public abstract int lastFull(final Direction.Axis axis);

    public interface IntFaceConsumer {

        void consume(Direction direction, int x, int y, int z);
    }

    public interface IntLineConsumer {

        void consume(int x1, int y1, int z1, int x2, int y2, int z2);
    }

    protected DiscreteVoxelShape() {
    }
}
