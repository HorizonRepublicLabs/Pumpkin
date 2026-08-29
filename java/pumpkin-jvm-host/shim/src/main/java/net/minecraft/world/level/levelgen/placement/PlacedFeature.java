package net.minecraft.world.level.levelgen.placement;

import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import dev.pumpkin.shim.Unimplemented;

public record PlacedFeature(Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placement) {

    public static final Codec<Holder<PlacedFeature>> CODEC = null;

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/PlacedFeature.toString:()Ljava/lang/String;");
    }
}
