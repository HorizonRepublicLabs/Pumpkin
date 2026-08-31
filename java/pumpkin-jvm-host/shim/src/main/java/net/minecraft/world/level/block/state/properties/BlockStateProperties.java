package net.minecraft.world.level.block.state.properties;

import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public class BlockStateProperties {

    public static final BooleanProperty OPEN = null;

    public static final BooleanProperty WATERLOGGED = null;

    public static final EnumProperty<Direction> FACING = null;

    public static final EnumProperty<Direction> HORIZONTAL_FACING = null;

    public static final EnumProperty<DoubleBlockHalf> DOUBLE_BLOCK_HALF = null;

    public static final EnumProperty<BedPart> BED_PART = null;

    public BlockStateProperties() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BlockStateProperties");
        }
    }
}
