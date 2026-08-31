package net.minecraft.world.item.component;

import java.util.SequencedSet;
import net.minecraft.core.component.DataComponentType;
import dev.pumpkin.shim.Unimplemented;

public record TooltipDisplay(boolean hideTooltip, SequencedSet<DataComponentType<?>> hiddenComponents) {

    public boolean shows(DataComponentType<?> component) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/TooltipDisplay.shows:(Lnet/minecraft/core/component/DataComponentType;)Z");
    }
}
