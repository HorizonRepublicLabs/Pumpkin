package net.minecraft.world.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import dev.pumpkin.shim.Unimplemented;

public class AxeItem extends Item {

    public AxeItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Item.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/item/AxeItem.<init>:(Lnet/minecraft/world/item/ToolMaterial;FFLnet/minecraft/world/item/Item$Properties;)V");
    }

    public InteractionResult useOn(UseOnContext context) {
        throw Unimplemented.forMember("net/minecraft/world/item/AxeItem.useOn:(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;");
    }

    public boolean canPerformAction(ItemInstance stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        throw Unimplemented.forMember("net/minecraft/world/item/AxeItem.canPerformAction:(Lnet/minecraft/world/item/ItemInstance;Lnet/neoforged/neoforge/common/ItemAbility;)Z");
    }

    public AxeItem() {
    }
}
