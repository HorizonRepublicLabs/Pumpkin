package net.minecraft.world.item.crafting.display;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public interface DisplayContentsFactory<T> {

    interface ForRemainders<T> extends DisplayContentsFactory<T> {

        T addRemainder(T entry, List<T> remainders);
    }

    interface ForStacks<T> extends DisplayContentsFactory<T> {

        default T forStack(Holder<Item> item) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/DisplayContentsFactory$ForStacks.forStack:(Lnet/minecraft/core/Holder;)Ljava/lang/Object;");
        }

        default T forStack(Item item) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/DisplayContentsFactory$ForStacks.forStack:(Lnet/minecraft/world/item/Item;)Ljava/lang/Object;");
        }

        T forStack(ItemStack stack);
    }
}
