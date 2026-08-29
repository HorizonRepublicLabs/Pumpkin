package net.minecraft.world.level.levelgen;

import java.util.List;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.state.BlockState;

public record NoiseGeneratorSettings(NoiseSettings noiseSettings, BlockState defaultBlock, BlockState defaultFluid, NoiseRouter noiseRouter, SurfaceRules.RuleSource surfaceRule, List<Climate.ParameterPoint> spawnTarget, int seaLevel, boolean disableMobGeneration, boolean aquifersEnabled, boolean oreVeinsEnabled, boolean useLegacyRandomSource) {
}
