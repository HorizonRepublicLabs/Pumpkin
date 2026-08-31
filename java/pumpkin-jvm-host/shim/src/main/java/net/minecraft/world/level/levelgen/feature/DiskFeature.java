package net.minecraft.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import dev.pumpkin.shim.Unimplemented;

public class DiskFeature extends Feature<DiskConfiguration> {

    public DiskFeature(Codec<DiskConfiguration> codec) {
    }

    public boolean place(FeaturePlaceContext<DiskConfiguration> context) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/DiskFeature.place:(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z");
    }

    public DiskFeature() {
    }
}
