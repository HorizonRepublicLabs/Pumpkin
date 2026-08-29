package net.minecraft.client.gui.components;

import java.util.Optional;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.narration.NarrationSupplier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import dev.pumpkin.shim.Unimplemented;

public class Tooltip implements NarrationSupplier {

    private Tooltip(Component message, Component narration, Optional<TooltipComponent> component, Identifier style) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Tooltip.<init>:(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/Component;Ljava/util/Optional;Lnet/minecraft/resources/Identifier;)V");
    }

    public static Tooltip create(Component message) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Tooltip.create:(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/client/gui/components/Tooltip;");
    }

    public static Tooltip create(Component message, Component narration) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Tooltip.create:(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/Component;)Lnet/minecraft/client/gui/components/Tooltip;");
    }

    public static Tooltip create(Component message, Optional<TooltipComponent> component, Identifier style) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Tooltip.create:(Lnet/minecraft/network/chat/Component;Ljava/util/Optional;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/gui/components/Tooltip;");
    }

    public Optional<TooltipComponent> component() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Tooltip.component:()Ljava/util/Optional;");
    }

    public Identifier style() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Tooltip.style:()Lnet/minecraft/resources/Identifier;");
    }

    public void updateNarration(NarrationElementOutput output) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Tooltip.updateNarration:(Lnet/minecraft/client/gui/narration/NarrationElementOutput;)V");
    }

    protected Tooltip() {
    }
}
