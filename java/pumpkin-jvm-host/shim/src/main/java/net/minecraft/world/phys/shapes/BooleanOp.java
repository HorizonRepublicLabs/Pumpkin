package net.minecraft.world.phys.shapes;

import dev.pumpkin.shim.Stubs;

public interface BooleanOp {

    BooleanOp ONLY_SECOND = Stubs.of(BooleanOp.class, "net/minecraft/world/phys/shapes/BooleanOp");

    BooleanOp ONLY_FIRST = Stubs.of(BooleanOp.class, "net/minecraft/world/phys/shapes/BooleanOp");

    BooleanOp OR = Stubs.of(BooleanOp.class, "net/minecraft/world/phys/shapes/BooleanOp");

    boolean apply(final boolean first, final boolean second);
}
