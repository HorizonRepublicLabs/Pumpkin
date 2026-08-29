package net.neoforged.neoforge.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;
import dev.pumpkin.shim.Unimplemented;

public class EventHooks {

    public static BonemealEvent fireBonemealEvent(Player player, Level level, BlockPos pos, BlockState state, ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.fireBonemealEvent:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/item/ItemStack;)Lnet/neoforged/neoforge/event/entity/player/BonemealEvent;");
    }

    public static int onArrowLoose(ItemStack stack, Level level, Player player, int charge, boolean hasAmmo) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.onArrowLoose:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;IZ)I");
    }

    public static BlockGrowFeatureEvent fireBlockGrowFeature(LevelAccessor level, RandomSource rand, BlockPos pos, Holder<ConfiguredFeature<?, ?>> holder) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.fireBlockGrowFeature:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Holder;)Lnet/neoforged/neoforge/event/level/BlockGrowFeatureEvent;");
    }

    protected EventHooks() {
    }
}
