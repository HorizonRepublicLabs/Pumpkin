package net.minecraft.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import dev.pumpkin.shim.Unimplemented;

public abstract class Feature<FC extends FeatureConfiguration> {

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/Feature.register:(Ljava/lang/String;Lnet/minecraft/world/level/levelgen/feature/Feature;)Lnet/minecraft/world/level/levelgen/feature/Feature;");
    }

    public Feature(Codec<FC> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/Feature.<init>:(Lcom/mojang/serialization/Codec;)V");
    }

    public abstract boolean place(final FeaturePlaceContext<FC> context);

    public Feature() {
    }
}
