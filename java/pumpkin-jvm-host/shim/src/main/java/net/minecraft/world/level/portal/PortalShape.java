package net.minecraft.world.level.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public class PortalShape {

    private PortalShape(Direction.Axis axis, int portalBlockCount, Direction rightDir, BlockPos bottomLeft, int width, int height) {
    }

    public boolean isValid() {
        throw Unimplemented.forMember("net/minecraft/world/level/portal/PortalShape.isValid:()Z");
    }

    public PortalShape() {
    }
}
