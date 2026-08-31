package net.minecraft.world.level.levelgen.blockpredicates;

import com.mojang.serialization.Codec;
import java.util.function.BiPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;

public interface BlockPredicate extends BiPredicate<WorldGenLevel, BlockPos> {

    // Pumpkin divergence: a throwing codec, not null -- DFU composes through it at
    // class-init; it throws by name on first real use.
    Codec<BlockPredicate> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/level/levelgen/blockpredicates/BlockPredicate.CODEC");

    BlockPredicateType<?> type();
}
