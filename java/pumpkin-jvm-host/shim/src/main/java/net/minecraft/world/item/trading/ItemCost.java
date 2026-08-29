package net.minecraft.world.item.trading;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import dev.pumpkin.shim.Unimplemented;

public record ItemCost(Holder<Item> item, int count, DataComponentExactPredicate components, ItemStack itemStack) {

    public ItemCost(ItemLike item) {
        this((Holder<Item>) null, (int) 0, (DataComponentExactPredicate) null, (ItemStack) null);
        throw Unimplemented.forMember("net/minecraft/world/item/trading/ItemCost.<init>:(Lnet/minecraft/world/level/ItemLike;)V");
    }

    public ItemCost(ItemLike item, int count) {
        this((Holder<Item>) null, (int) 0, (DataComponentExactPredicate) null, (ItemStack) null);
        throw Unimplemented.forMember("net/minecraft/world/item/trading/ItemCost.<init>:(Lnet/minecraft/world/level/ItemLike;I)V");
    }

    public ItemCost(Holder<Item> item, int count, DataComponentExactPredicate components) {
        this((Holder<Item>) null, (int) 0, (DataComponentExactPredicate) null, (ItemStack) null);
        throw Unimplemented.forMember("net/minecraft/world/item/trading/ItemCost.<init>:(Lnet/minecraft/core/Holder;ILnet/minecraft/core/component/DataComponentExactPredicate;)V");
    }

    public boolean test(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/ItemCost.test:(Lnet/minecraft/world/item/ItemStack;)Z");
    }
}
