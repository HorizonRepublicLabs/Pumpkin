package net.minecraft.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class ShearsItem extends Item {

    public ShearsItem(Item.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/item/ShearsItem.<init>:(Lnet/minecraft/world/item/Item$Properties;)V");
    }

    public static Tool createToolProperties() {
        throw Unimplemented.forMember("net/minecraft/world/item/ShearsItem.createToolProperties:()Lnet/minecraft/world/item/component/Tool;");
    }

    public boolean mineBlock(ItemStack itemStack, Level level, BlockState state, BlockPos pos, LivingEntity miner) {
        throw Unimplemented.forMember("net/minecraft/world/item/ShearsItem.mineBlock:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/LivingEntity;)Z");
    }

    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, net.minecraft.world.InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/item/ShearsItem.interactLivingEntity:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public boolean canPerformAction(ItemInstance stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        throw Unimplemented.forMember("net/minecraft/world/item/ShearsItem.canPerformAction:(Lnet/minecraft/world/item/ItemInstance;Lnet/neoforged/neoforge/common/ItemAbility;)Z");
    }

    public InteractionResult useOn(UseOnContext context) {
        throw Unimplemented.forMember("net/minecraft/world/item/ShearsItem.useOn:(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;");
    }

    public ShearsItem() {
    }
}
