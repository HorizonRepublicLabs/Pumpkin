package net.minecraft.world.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public class BlockItem extends Item {

    public BlockItem(Block block, Item.Properties properties) {
    }

    public InteractionResult useOn(UseOnContext context) {
        throw Unimplemented.forMember("net/minecraft/world/item/BlockItem.useOn:(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;");
    }

    public InteractionResult place(BlockPlaceContext placeContext) {
        throw Unimplemented.forMember("net/minecraft/world/item/BlockItem.place:(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/InteractionResult;");
    }

    public boolean shouldPrintOpWarning(ItemStack stack, Player player) {
        throw Unimplemented.forMember("net/minecraft/world/item/BlockItem.shouldPrintOpWarning:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public Block getBlock() {
        throw Unimplemented.forMember("net/minecraft/world/item/BlockItem.getBlock:()Lnet/minecraft/world/level/block/Block;");
    }

    public boolean canFitInsideContainerItems() {
        throw Unimplemented.forMember("net/minecraft/world/item/BlockItem.canFitInsideContainerItems:()Z");
    }

    public void onDestroyed(ItemEntity entity) {
        throw Unimplemented.forMember("net/minecraft/world/item/BlockItem.onDestroyed:(Lnet/minecraft/world/entity/item/ItemEntity;)V");
    }

    public BlockItem() {
    }
}
