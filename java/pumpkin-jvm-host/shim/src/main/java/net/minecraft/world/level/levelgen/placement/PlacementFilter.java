package net.minecraft.world.level.levelgen.placement;

import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public abstract class PlacementFilter extends PlacementModifier {

    public final Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos origin) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/PlacementFilter.getPositions:(Lnet/minecraft/world/level/levelgen/placement/PlacementContext;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;)Ljava/util/stream/Stream;");
    }

    protected abstract boolean shouldPlace(PlacementContext context, RandomSource random, BlockPos origin);

    public PlacementFilter() {
    }
}
