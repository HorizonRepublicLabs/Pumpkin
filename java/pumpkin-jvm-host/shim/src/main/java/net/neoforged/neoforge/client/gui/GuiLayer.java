package net.neoforged.neoforge.client.gui;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface GuiLayer {

    void render(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker);
}
