package net.minecraft.world.level.levelgen.feature.configurations;

import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class OreConfiguration implements FeatureConfiguration {

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    public static final Codec<OreConfiguration> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/level/levelgen/feature/configurations/OreConfiguration.CODEC");

    public final List<OreConfiguration.TargetBlockState> targetStates = null;

    public final int size = 0;

    public OreConfiguration(List<OreConfiguration.TargetBlockState> targetBlockStates, int size, float discardChanceOnAirExposure) {
    }

    public OreConfiguration(List<OreConfiguration.TargetBlockState> targetBlockStates, int size) {
    }

    public OreConfiguration(RuleTest target, BlockState state, int size, float discardChanceOnAirExposure) {
    }

    public OreConfiguration(RuleTest target, BlockState state, int size) {
    }

    public static class TargetBlockState {

        public static final Codec<OreConfiguration.TargetBlockState> CODEC =
                dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.CODEC");

        public final RuleTest target = null;

        public final BlockState state = null;

        private TargetBlockState(RuleTest target, BlockState state) {
        }

        public TargetBlockState() {
        }
    }

    public OreConfiguration() {
    }
}
