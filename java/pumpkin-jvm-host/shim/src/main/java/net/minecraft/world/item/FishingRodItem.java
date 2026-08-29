package net.minecraft.world.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class FishingRodItem extends Item {

    public FishingRodItem(Item.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/item/FishingRodItem.<init>:(Lnet/minecraft/world/item/Item$Properties;)V");
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/item/FishingRodItem.use:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public boolean canPerformAction(ItemInstance stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        throw Unimplemented.forMember("net/minecraft/world/item/FishingRodItem.canPerformAction:(Lnet/minecraft/world/item/ItemInstance;Lnet/neoforged/neoforge/common/ItemAbility;)Z");
    }

    public FishingRodItem() {
    }
}
