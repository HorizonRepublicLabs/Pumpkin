package net.minecraft.world.level.block.state.properties;

import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public class BlockStateProperties {

    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    public static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");

    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class);

    public static final EnumProperty<Direction> HORIZONTAL_FACING = EnumProperty.create("facing", Direction.class, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);

    public static final EnumProperty<DoubleBlockHalf> DOUBLE_BLOCK_HALF = EnumProperty.create("half", DoubleBlockHalf.class);

    public static final EnumProperty<BedPart> BED_PART = EnumProperty.create("part", BedPart.class);

    public BlockStateProperties() {
    }

    // Pumpkin divergence: no throwing initializer -- vanilla's own definitions above.
}
