package net.minecraft.client.gui.screens;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.AbstractContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public abstract class Screen extends AbstractContainerEventHandler implements Renderable {

    protected Screen(Component title) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/Screen.<init>:(Lnet/minecraft/network/chat/Component;)V");
    }

    protected Screen(Minecraft minecraft, Font font, Component title) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/Screen.<init>:(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;)V");
    }

    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/Screen.extractRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    public boolean keyPressed(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/Screen.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    public List<? extends GuiEventListener> children() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/Screen.children:()Ljava/util/List;");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/Screen.tick:()V");
    }

    public boolean isMouseOver(double mouseX, double mouseY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/Screen.isMouseOver:(DD)Z");
    }

    public ScreenRectangle getRectangle() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/Screen.getRectangle:()Lnet/minecraft/client/gui/navigation/ScreenRectangle;");
    }

    public record NarratableSearchResult(NarratableEntry entry, int index, NarratableEntry.NarrationPriority priority) {
    }

    protected Screen() {
    }
}
