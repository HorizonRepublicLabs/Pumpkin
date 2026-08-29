package net.minecraft.world.level.levelgen.feature.configurations;

import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import dev.pumpkin.shim.Unimplemented;

public class OreConfiguration implements FeatureConfiguration {

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    public static final Codec<OreConfiguration> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/level/levelgen/feature/configurations/OreConfiguration.CODEC");

    public final List<OreConfiguration.TargetBlockState> targetStates = null;

    public final int size = 0;

    public OreConfiguration(List<OreConfiguration.TargetBlockState> targetBlockStates, int size, float discardChanceOnAirExposure) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/configurations/OreConfiguration.<init>:(Ljava/util/List;IF)V");
    }

    public OreConfiguration(List<OreConfiguration.TargetBlockState> targetBlockStates, int size) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/configurations/OreConfiguration.<init>:(Ljava/util/List;I)V");
    }

    public OreConfiguration(RuleTest target, BlockState state, int size, float discardChanceOnAirExposure) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/configurations/OreConfiguration.<init>:(Lnet/minecraft/world/level/levelgen/structure/templatesystem/RuleTest;Lnet/minecraft/world/level/block/state/BlockState;IF)V");
    }

    public OreConfiguration(RuleTest target, BlockState state, int size) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/configurations/OreConfiguration.<init>:(Lnet/minecraft/world/level/levelgen/structure/templatesystem/RuleTest;Lnet/minecraft/world/level/block/state/BlockState;I)V");
    }

    public static class TargetBlockState {

        public final RuleTest target = null;

        public final BlockState state = null;

        private TargetBlockState(RuleTest target, BlockState state) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/configurations/OreConfiguration$TargetBlockState.<init>:(Lnet/minecraft/world/level/levelgen/structure/templatesystem/RuleTest;Lnet/minecraft/world/level/block/state/BlockState;)V");
        }

        public TargetBlockState() {
        }
    }

    public OreConfiguration() {
    }
}
