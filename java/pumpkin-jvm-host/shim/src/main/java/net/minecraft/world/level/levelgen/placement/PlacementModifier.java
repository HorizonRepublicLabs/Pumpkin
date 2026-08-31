package net.minecraft.world.level.levelgen.placement;

import com.mojang.serialization.Codec;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

public abstract class PlacementModifier {

    public static final Codec<PlacementModifier> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.world.level.levelgen.placement.PlacementModifier.CODEC");

    public abstract Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos origin);

    public abstract PlacementModifierType<?> type();

    public PlacementModifier() {
    }
}
