package net.neoforged.neoforge.client.event;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class RenderGuiEvent extends Event {

    protected RenderGuiEvent(GuiGraphicsExtractor guiGraphics, DeltaTracker partialTick) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderGuiEvent.<init>:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V");
    }

    public GuiGraphicsExtractor getGuiGraphics() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderGuiEvent.getGuiGraphics:()Lnet/minecraft/client/gui/GuiGraphicsExtractor;");
    }

    public static class Pre extends RenderGuiEvent implements ICancellableEvent {

        public Pre(GuiGraphicsExtractor guiGraphics, DeltaTracker partialTick) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderGuiEvent$Pre.<init>:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V");
        }

        public Pre() {
        }
    }

    public static class Post extends RenderGuiEvent {

        public Post(GuiGraphicsExtractor guiGraphics, DeltaTracker partialTick) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderGuiEvent$Post.<init>:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V");
        }

        public Post() {
        }
    }

    public RenderGuiEvent() {
    }
}
