package net.neoforged.neoforge.client.event;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.client.gui.GuiLayer;
import dev.pumpkin.shim.Unimplemented;

public abstract class RenderGuiLayerEvent extends Event {

    protected RenderGuiLayerEvent(GuiGraphicsExtractor guiGraphics, DeltaTracker partialTick, Identifier name, GuiLayer layer) {
    }

    public Identifier getName() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderGuiLayerEvent.getName:()Lnet/minecraft/resources/Identifier;");
    }

    public GuiLayer getLayer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderGuiLayerEvent.getLayer:()Lnet/neoforged/neoforge/client/gui/GuiLayer;");
    }

    public static class Pre extends RenderGuiLayerEvent implements ICancellableEvent {

        public Pre(GuiGraphicsExtractor guiGraphics, DeltaTracker partialTick, Identifier name, GuiLayer layer) {
        }

        public Pre() {
        }
    }

    public static class Post extends RenderGuiLayerEvent {

        public Post(GuiGraphicsExtractor guiGraphics, DeltaTracker partialTick, Identifier name, GuiLayer layer) {
        }

        public Post() {
        }
    }

    public RenderGuiLayerEvent() {
    }
}
