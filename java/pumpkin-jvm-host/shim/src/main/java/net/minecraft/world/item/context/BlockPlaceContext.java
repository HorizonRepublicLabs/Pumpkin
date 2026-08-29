package net.minecraft.world.item.context;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import dev.pumpkin.shim.Unimplemented;

public class BlockPlaceContext extends UseOnContext {

    public BlockPlaceContext(Player player, InteractionHand hand, ItemStack itemInHand, BlockHitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/item/context/BlockPlaceContext.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/phys/BlockHitResult;)V");
    }

    public BlockPlaceContext(UseOnContext context) {
        throw Unimplemented.forMember("net/minecraft/world/item/context/BlockPlaceContext.<init>:(Lnet/minecraft/world/item/context/UseOnContext;)V");
    }

    public BlockPlaceContext(Level level, Player player, InteractionHand hand, ItemStack itemStackInHand, BlockHitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/item/context/BlockPlaceContext.<init>:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/phys/BlockHitResult;)V");
    }

    public static BlockPlaceContext at(BlockPlaceContext context, BlockPos pos, Direction direction) {
        throw Unimplemented.forMember("net/minecraft/world/item/context/BlockPlaceContext.at:(Lnet/minecraft/world/item/context/BlockPlaceContext;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Lnet/minecraft/world/item/context/BlockPlaceContext;");
    }

    public BlockPos getClickedPos() {
        throw Unimplemented.forMember("net/minecraft/world/item/context/BlockPlaceContext.getClickedPos:()Lnet/minecraft/core/BlockPos;");
    }

    protected BlockPlaceContext() {
    }
}
