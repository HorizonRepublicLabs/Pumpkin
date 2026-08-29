package net.minecraft.world.level.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public class PortalShape {

    private PortalShape(Direction.Axis axis, int portalBlockCount, Direction rightDir, BlockPos bottomLeft, int width, int height) {
        throw Unimplemented.forMember("net/minecraft/world/level/portal/PortalShape.<init>:(Lnet/minecraft/core/Direction$Axis;ILnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;II)V");
    }

    public boolean isValid() {
        throw Unimplemented.forMember("net/minecraft/world/level/portal/PortalShape.isValid:()Z");
    }

    protected PortalShape() {
    }
}
