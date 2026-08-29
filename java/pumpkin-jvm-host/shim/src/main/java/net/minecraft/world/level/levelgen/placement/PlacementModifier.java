package net.minecraft.world.level.levelgen.placement;

import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

public abstract class PlacementModifier {

    public abstract Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos origin);

    public abstract PlacementModifierType<?> type();

    protected PlacementModifier() {
    }
}
