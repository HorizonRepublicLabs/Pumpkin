package net.minecraft.world.level.biome;

import java.util.List;
import java.util.function.ToIntFunction;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class FeatureSorter {

    public record StepFeatureData(List<PlacedFeature> features, ToIntFunction<PlacedFeature> indexMapping) {

        private StepFeatureData(List<PlacedFeature> features) {
            this((List<PlacedFeature>) null, (ToIntFunction<PlacedFeature>) null);
        }
    }

    public FeatureSorter() {
    }
}
