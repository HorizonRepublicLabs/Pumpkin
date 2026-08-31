package net.neoforged.neoforge.client.event;

import java.util.List;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.client.gui.GuiLayer;
import net.neoforged.neoforge.client.gui.GuiLayerManager;
import dev.pumpkin.shim.Unimplemented;

public class RegisterGuiLayersEvent extends Event implements IModBusEvent {

    public RegisterGuiLayersEvent(List<GuiLayerManager.NamedLayer> layers) {
    }

    public void registerBelowAll(Identifier id, GuiLayer layer) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterGuiLayersEvent.registerBelowAll:(Lnet/minecraft/resources/Identifier;Lnet/neoforged/neoforge/client/gui/GuiLayer;)V");
    }

    public void registerAbove(Identifier other, Identifier id, GuiLayer layer) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterGuiLayersEvent.registerAbove:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;Lnet/neoforged/neoforge/client/gui/GuiLayer;)V");
    }

    private enum Ordering {

        BEFORE, AFTER
    }

    public RegisterGuiLayersEvent() {
    }
}
