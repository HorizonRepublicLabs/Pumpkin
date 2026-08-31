package net.neoforged.neoforge.event;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.common.util.InsertableLinkedOpenCustomHashSet;
import dev.pumpkin.shim.Unimplemented;

public final class BuildCreativeModeTabContentsEvent extends Event implements IModBusEvent, CreativeModeTab.Output {

    public BuildCreativeModeTabContentsEvent(CreativeModeTab tab, ResourceKey<CreativeModeTab> tabKey, CreativeModeTab.ItemDisplayParameters parameters, InsertableLinkedOpenCustomHashSet<ItemStack> parentEntries, InsertableLinkedOpenCustomHashSet<ItemStack> searchEntries) {
    }

    public ResourceKey<CreativeModeTab> getTabKey() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/BuildCreativeModeTabContentsEvent.getTabKey:()Lnet/minecraft/resources/ResourceKey;");
    }

    public CreativeModeTab.ItemDisplayParameters getParameters() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/BuildCreativeModeTabContentsEvent.getParameters:()Lnet/minecraft/world/item/CreativeModeTab$ItemDisplayParameters;");
    }

    public void accept(ItemStack newEntry, CreativeModeTab.TabVisibility visibility) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/BuildCreativeModeTabContentsEvent.accept:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/CreativeModeTab$TabVisibility;)V");
    }

    public BuildCreativeModeTabContentsEvent() {
    }
}
