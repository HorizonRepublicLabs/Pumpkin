package net.neoforged.neoforge.event.entity.player;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import dev.pumpkin.shim.Unimplemented;

public class ItemTooltipEvent extends PlayerEvent {

    public ItemTooltipEvent(ItemStack itemStack, Player player, List<Component> list, TooltipFlag flags, TooltipContext context, TooltipDisplay display) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/ItemTooltipEvent.<init>:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Ljava/util/List;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;)V");
    }

    public TooltipFlag getFlags() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/ItemTooltipEvent.getFlags:()Lnet/minecraft/world/item/TooltipFlag;");
    }

    public ItemStack getItemStack() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/ItemTooltipEvent.getItemStack:()Lnet/minecraft/world/item/ItemStack;");
    }

    public List<Component> getToolTip() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/ItemTooltipEvent.getToolTip:()Ljava/util/List;");
    }

    public Player getEntity() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/ItemTooltipEvent.getEntity:()Lnet/minecraft/world/entity/player/Player;");
    }

    public ItemTooltipEvent() {
    }
}
