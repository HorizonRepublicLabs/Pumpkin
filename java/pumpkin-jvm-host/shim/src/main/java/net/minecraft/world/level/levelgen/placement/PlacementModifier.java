package net.minecraft.world.level.levelgen.placement;

import com.mojang.serialization.Codec;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

public abstract class PlacementModifier {

    public static final Codec<PlacementModifier> CODEC = null;

    public abstract Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos origin);

    public abstract PlacementModifierType<?> type();

    public PlacementModifier() {
    }
}
