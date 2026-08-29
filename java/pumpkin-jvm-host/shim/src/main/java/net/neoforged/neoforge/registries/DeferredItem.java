package net.neoforged.neoforge.registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import dev.pumpkin.shim.Unimplemented;

public class DeferredItem<T extends Item> extends DeferredHolder<Item, T> implements ItemLike {

    protected DeferredItem(ResourceKey<Item> key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredItem.<init>:(Lnet/minecraft/resources/ResourceKey;)V");
    }

    public Item asItem() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredItem.asItem:()Lnet/minecraft/world/item/Item;");
    }

    public DeferredItem() {
    }
}
