package net.minecraft.world.level.levelgen.placement;

import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import dev.pumpkin.shim.Unimplemented;

public record PlacedFeature(Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placement) {

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    public static final Codec<Holder<PlacedFeature>> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/level/levelgen/placement/PlacedFeature.CODEC");

    private boolean placeWithContext(PlacementContext context, RandomSource random, BlockPos origin) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/PlacedFeature.placeWithContext:(Lnet/minecraft/world/level/levelgen/placement/PlacementContext;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;)Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/PlacedFeature.toString:()Ljava/lang/String;");
    }
}
