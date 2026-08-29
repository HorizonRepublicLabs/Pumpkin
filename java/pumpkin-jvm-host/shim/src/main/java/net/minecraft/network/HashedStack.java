package net.minecraft.network;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public interface HashedStack {

    boolean matches(ItemStack stack, HashedPatchMap.HashGenerator hasher);

    static HashedStack create(ItemStack itemStack, HashedPatchMap.HashGenerator hasher) {
        throw Unimplemented.forMember("net/minecraft/network/HashedStack.create:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/network/HashedPatchMap$HashGenerator;)Lnet/minecraft/network/HashedStack;");
    }

    record ActualItem(Holder<Item> item, int count, HashedPatchMap components) implements HashedStack {

        public boolean matches(ItemStack itemStack, HashedPatchMap.HashGenerator hasher) {
            throw Unimplemented.forMember("net/minecraft/network/HashedStack$ActualItem.matches:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/network/HashedPatchMap$HashGenerator;)Z");
        }
    }
}
