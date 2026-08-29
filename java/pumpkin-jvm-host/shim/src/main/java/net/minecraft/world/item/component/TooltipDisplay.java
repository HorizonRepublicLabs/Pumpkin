package net.minecraft.world.item.component;

import java.util.SequencedSet;
import net.minecraft.core.component.DataComponentType;

public record TooltipDisplay(boolean hideTooltip, SequencedSet<DataComponentType<?>> hiddenComponents) {
}
