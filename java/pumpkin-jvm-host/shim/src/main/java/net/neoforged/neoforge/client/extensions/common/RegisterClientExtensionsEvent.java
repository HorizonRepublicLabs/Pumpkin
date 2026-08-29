package net.neoforged.neoforge.client.extensions.common;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public final class RegisterClientExtensionsEvent extends Event implements IModBusEvent {

    RegisterClientExtensionsEvent() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/RegisterClientExtensionsEvent.<init>:()V");
    }

    public void registerItem(IClientItemExtensions extensions, Item... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/RegisterClientExtensionsEvent.registerItem:(Lnet/neoforged/neoforge/client/extensions/common/IClientItemExtensions;[Lnet/minecraft/world/item/Item;)V");
    }

    public final void registerItem(IClientItemExtensions extensions, Holder<Item>... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/RegisterClientExtensionsEvent.registerItem:(Lnet/neoforged/neoforge/client/extensions/common/IClientItemExtensions;[Lnet/minecraft/core/Holder;)V");
    }
}
