package net.neoforged.neoforge.transfer.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public final class FluidUtil {

    protected FluidUtil() {
    }

    public static boolean interactWithFluidHandler(Player player, InteractionHand hand, Level level, BlockPos pos, Direction side) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.interactWithFluidHandler:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z");
    }

    public static boolean interactWithFluidHandler(Player player, InteractionHand hand, Level level, BlockPos pos, Direction side, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.interactWithFluidHandler:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Z");
    }

    public static boolean interactWithFluidHandler(Player player, InteractionHand hand, BlockPos pos, ResourceHandler<FluidResource> handler) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.interactWithFluidHandler:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/core/BlockPos;Lnet/neoforged/neoforge/transfer/ResourceHandler;)Z");
    }

    public static boolean interactWithFluidHandler(Player player, InteractionHand hand, BlockPos pos, ResourceHandler<FluidResource> handler, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.interactWithFluidHandler:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/core/BlockPos;Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Z");
    }

    public static FluidStack tryPickupFluid(ResourceHandler<FluidResource> destination, Player player, Level level, BlockPos pos, Direction side) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.tryPickupFluid:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    public static FluidStack tryPickupFluid(ResourceHandler<FluidResource> destination, Player player, Level level, BlockPos pos, Direction side, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.tryPickupFluid:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    public static FluidStack tryPickupFluid(ResourceHandler<FluidResource> destination, Player player, Level level, BlockPos pos, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.tryPickupFluid:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    private static FluidStack tryPickupFluid(ResourceHandler<FluidResource> destination, Player player, Level level, BlockPos pos, BucketPickup bucketPickup, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.tryPickupFluid:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/BucketPickup;Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    public static FluidStack tryPlaceFluid(ResourceHandler<FluidResource> source, Player player, Level level, InteractionHand hand, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.tryPlaceFluid:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/core/BlockPos;)Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    public static FluidStack tryPlaceFluid(ResourceHandler<FluidResource> source, Player player, Level level, BlockPos pos, boolean validatePlaced, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.tryPlaceFluid:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;ZLnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    public static boolean tryPlaceFluid(FluidResource resource, Player player, Level level, InteractionHand hand, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.tryPlaceFluid:(Lnet/neoforged/neoforge/transfer/fluid/FluidResource;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/core/BlockPos;)Z");
    }

    public static boolean tryPlaceFluid(FluidResource resource, Player player, Level level, BlockPos pos, boolean validatePlaced) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.tryPlaceFluid:(Lnet/neoforged/neoforge/transfer/fluid/FluidResource;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Z)Z");
    }

    private static boolean tryPlaceFluid(FluidResource resource, Player player, Level level, BlockPos pos, boolean validatePlaced, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidUtil.tryPlaceFluid:(Lnet/neoforged/neoforge/transfer/fluid/FluidResource;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;ZLnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Z");
    }
}
