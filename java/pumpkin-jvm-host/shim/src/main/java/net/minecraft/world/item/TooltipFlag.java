package net.minecraft.world.item;

import net.neoforged.neoforge.common.extensions.TooltipFlagExtension;
import dev.pumpkin.shim.Unimplemented;

public interface TooltipFlag extends TooltipFlagExtension {

    boolean isAdvanced();

    boolean isCreative();

    record Default(boolean advanced, boolean creative) implements TooltipFlag {

        public boolean isAdvanced() {
            throw Unimplemented.forMember("net/minecraft/world/item/TooltipFlag$Default.isAdvanced:()Z");
        }

        public boolean isCreative() {
            throw Unimplemented.forMember("net/minecraft/world/item/TooltipFlag$Default.isCreative:()Z");
        }
    }
}
