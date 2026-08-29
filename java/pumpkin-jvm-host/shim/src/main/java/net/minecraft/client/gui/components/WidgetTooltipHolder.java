package net.minecraft.client.gui.components;

import net.minecraft.client.gui.narration.NarrationElementOutput;
import dev.pumpkin.shim.Unimplemented;

public class WidgetTooltipHolder {

    public void set(Tooltip tooltip) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/WidgetTooltipHolder.set:(Lnet/minecraft/client/gui/components/Tooltip;)V");
    }

    public Tooltip get() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/WidgetTooltipHolder.get:()Lnet/minecraft/client/gui/components/Tooltip;");
    }

    public void updateNarration(NarrationElementOutput output) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/WidgetTooltipHolder.updateNarration:(Lnet/minecraft/client/gui/narration/NarrationElementOutput;)V");
    }

    protected WidgetTooltipHolder() {
    }
}
