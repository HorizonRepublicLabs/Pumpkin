package net.neoforged.neoforge.client.gui;

import java.util.function.BooleanSupplier;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class GuiLayerManager {

    public record NamedLayer(Identifier name, GuiLayer layer) {
    }

    public GuiLayerManager add(Identifier name, GuiLayer layer) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/GuiLayerManager.add:(Lnet/minecraft/resources/Identifier;Lnet/neoforged/neoforge/client/gui/GuiLayer;)Lnet/neoforged/neoforge/client/gui/GuiLayerManager;");
    }

    public GuiLayerManager add(GuiLayerManager child, BooleanSupplier shouldRender) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/GuiLayerManager.add:(Lnet/neoforged/neoforge/client/gui/GuiLayerManager;Ljava/util/function/BooleanSupplier;)Lnet/neoforged/neoforge/client/gui/GuiLayerManager;");
    }

    public void render(GuiGraphicsExtractor guiGraphics, DeltaTracker partialTick) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/GuiLayerManager.render:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V");
    }

    public GuiLayerManager() {
    }
}
