package net.minecraft.world.item.component;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;

public record UseRemainder(ItemStackTemplate convertInto) {

    public interface OnExtraCreatedRemainder {

        void apply(final ItemStack extraCreatedRemainder);
    }
}
