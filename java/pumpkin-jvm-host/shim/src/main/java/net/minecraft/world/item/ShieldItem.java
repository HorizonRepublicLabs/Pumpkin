package net.minecraft.world.item;

import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public class ShieldItem extends Item {

    public ShieldItem(Item.Properties properties) {
    }

    public Component getName(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/ShieldItem.getName:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/chat/Component;");
    }

    public ShieldItem() {
    }
}
