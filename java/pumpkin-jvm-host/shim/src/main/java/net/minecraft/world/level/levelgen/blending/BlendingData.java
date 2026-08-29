package net.minecraft.world.level.levelgen.blending;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import dev.pumpkin.shim.Unimplemented;

public class BlendingData {

    private BlendingData(int minSection, int maxSection, Optional<double[]> heights) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/blending/BlendingData.<init>:(IILjava/util/Optional;)V");
    }

    public static BlendingData unpack(BlendingData.Packed packed) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/blending/BlendingData.unpack:(Lnet/minecraft/world/level/levelgen/blending/BlendingData$Packed;)Lnet/minecraft/world/level/levelgen/blending/BlendingData;");
    }

    protected double getHeight(int cellX, int cellY, int cellZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/blending/BlendingData.getHeight:(III)D");
    }

    private int getMinY() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/blending/BlendingData.getMinY:()I");
    }

    protected interface BiomeConsumer {

        void consume(final int cellX, final int cellZ, final Holder<Biome> biome);
    }

    protected interface DensityConsumer {

        void consume(final int cellX, final int cellY, final int cellZ, final double density);
    }

    protected interface HeightConsumer {

        void consume(final int cellX, final int cellZ, final double height);
    }

    public record Packed(int minSection, int maxSection, Optional<double[]> heights) {
    }

    public BlendingData() {
    }
}
