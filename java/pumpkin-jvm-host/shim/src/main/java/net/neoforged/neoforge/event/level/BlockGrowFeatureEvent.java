package net.neoforged.neoforge.event.level;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public class BlockGrowFeatureEvent extends LevelEvent implements ICancellableEvent {

    public BlockGrowFeatureEvent(LevelAccessor level, RandomSource rand, BlockPos pos, Holder<ConfiguredFeature<?, ?>> feature) {
    }

    public RandomSource getRandom() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockGrowFeatureEvent.getRandom:()Lnet/minecraft/util/RandomSource;");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockGrowFeatureEvent.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public void setCanceled(boolean canceled) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockGrowFeatureEvent.setCanceled:(Z)V");
    }

    public BlockGrowFeatureEvent() {
    }
}
