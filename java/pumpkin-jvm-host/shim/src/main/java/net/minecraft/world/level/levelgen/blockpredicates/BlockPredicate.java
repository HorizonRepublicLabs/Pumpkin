package net.minecraft.world.level.levelgen.blockpredicates;

import com.mojang.serialization.Codec;
import java.util.function.BiPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;

public interface BlockPredicate extends BiPredicate<WorldGenLevel, BlockPos> {

    Codec<BlockPredicate> CODEC = null;

    BlockPredicateType<?> type();
}
