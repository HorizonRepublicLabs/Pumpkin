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

    public static final EnumProperty<SlabType> SLAB_TYPE = EnumProperty.create("type", SlabType.class);

    public static final EnumProperty<Half> HALF = EnumProperty.create("half", Half.class);

    public static final EnumProperty<StairsShape> STAIRS_SHAPE = EnumProperty.create("shape", StairsShape.class);

    public static final BooleanProperty UP = BooleanProperty.create("up");

    public static final EnumProperty<WallSide> NORTH_WALL = EnumProperty.create("north", WallSide.class);

    public static final EnumProperty<WallSide> EAST_WALL = EnumProperty.create("east", WallSide.class);

    public static final EnumProperty<WallSide> SOUTH_WALL = EnumProperty.create("south", WallSide.class);

    public static final EnumProperty<WallSide> WEST_WALL = EnumProperty.create("west", WallSide.class);

    public BlockStateProperties() {
    }

    // Pumpkin divergence: no throwing initializer -- vanilla's own definitions above.
}
