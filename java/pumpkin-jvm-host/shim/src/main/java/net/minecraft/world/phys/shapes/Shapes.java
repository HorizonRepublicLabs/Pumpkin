package net.minecraft.world.phys.shapes;

import dev.pumpkin.shim.Unimplemented;

public final class Shapes {

    public interface DoubleLineConsumer {

        void consume(double x1, double y1, double z1, double x2, double y2, double z2);
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes");
        }
    }
}
