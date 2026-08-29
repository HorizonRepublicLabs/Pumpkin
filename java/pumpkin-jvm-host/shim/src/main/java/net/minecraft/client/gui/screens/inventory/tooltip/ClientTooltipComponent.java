package net.minecraft.client.gui.screens.inventory.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import dev.pumpkin.shim.Unimplemented;

public interface ClientTooltipComponent {

    static ClientTooltipComponent create(FormattedCharSequence charSequence) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipComponent.create:(Lnet/minecraft/util/FormattedCharSequence;)Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipComponent;");
    }

    static ClientTooltipComponent create(TooltipComponent component) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipComponent.create:(Lnet/minecraft/world/inventory/tooltip/TooltipComponent;)Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipComponent;");
    }

    int getHeight(final Font font);

    int getWidth(final Font font);
}
