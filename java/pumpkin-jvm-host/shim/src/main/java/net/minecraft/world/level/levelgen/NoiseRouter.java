package net.minecraft.world.level.levelgen;

public record NoiseRouter(DensityFunction barrierNoise, DensityFunction fluidLevelFloodednessNoise, DensityFunction fluidLevelSpreadNoise, DensityFunction lavaNoise, DensityFunction temperature, DensityFunction vegetation, DensityFunction continents, DensityFunction erosion, DensityFunction depth, DensityFunction ridges, DensityFunction preliminarySurfaceLevel, DensityFunction finalDensity, DensityFunction veinToggle, DensityFunction veinRidged, DensityFunction veinGap) {
}
