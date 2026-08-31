package net.neoforged.neoforge.event;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.util.BlockSnapshot;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.level.BlockEvent.NeighborNotifyEvent;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;
import dev.pumpkin.shim.Unimplemented;

public class EventHooks {

    public static boolean onBlockPlace(Entity entity, BlockSnapshot blockSnapshot, Direction direction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.onBlockPlace:(Lnet/minecraft/world/entity/Entity;Lnet/neoforged/neoforge/common/util/BlockSnapshot;Lnet/minecraft/core/Direction;)Z");
    }

    public static NeighborNotifyEvent onNeighborNotify(Level level, BlockPos pos, BlockState state, EnumSet<Direction> notifiedSides, boolean forceRedstoneUpdate) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.onNeighborNotify:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Ljava/util/EnumSet;Z)Lnet/neoforged/neoforge/event/level/BlockEvent$NeighborNotifyEvent;");
    }

    public static boolean onEntityStruckByLightning(Entity entity, LightningBolt bolt) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.onEntityStruckByLightning:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/LightningBolt;)Z");
    }

    public static BonemealEvent fireBonemealEvent(Player player, Level level, BlockPos pos, BlockState state, ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.fireBonemealEvent:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/item/ItemStack;)Lnet/neoforged/neoforge/event/entity/player/BonemealEvent;");
    }

    public static boolean onExplosionStart(Level level, ServerExplosion explosion) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.onExplosionStart:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/ServerExplosion;)Z");
    }

    public static int onArrowLoose(ItemStack stack, Level level, Player player, int charge, boolean hasAmmo) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.onArrowLoose:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;IZ)I");
    }

    public static boolean onProjectileImpact(Projectile projectile, HitResult ray) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.onProjectileImpact:(Lnet/minecraft/world/entity/projectile/Projectile;Lnet/minecraft/world/phys/HitResult;)Z");
    }

    public static BlockGrowFeatureEvent fireBlockGrowFeature(LevelAccessor level, RandomSource rand, BlockPos pos, Holder<ConfiguredFeature<?, ?>> holder) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.fireBlockGrowFeature:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Holder;)Lnet/neoforged/neoforge/event/level/BlockGrowFeatureEvent;");
    }

    public static void onLivingConvert(LivingEntity entity, LivingEntity outcome) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/EventHooks.onLivingConvert:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    public EventHooks() {
    }
}
