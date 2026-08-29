package net.minecraft.client.gui.components;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractSliderButton extends AbstractWidget.WithInactiveMessage {

    public AbstractSliderButton(int x, int y, int width, int height, Component message, double initialValue) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.<init>:(IIIILnet/minecraft/network/chat/Component;D)V");
    }

    protected MutableComponent createNarrationMessage() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.createNarrationMessage:()Lnet/minecraft/network/chat/MutableComponent;");
    }

    public void updateWidgetNarration(NarrationElementOutput output) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.updateWidgetNarration:(Lnet/minecraft/client/gui/narration/NarrationElementOutput;)V");
    }

    public void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.extractWidgetRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    protected void handleCursor(GuiGraphicsExtractor graphics) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.handleCursor:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;)V");
    }

    public void onClick(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.onClick:(Lnet/minecraft/client/input/MouseButtonEvent;Z)V");
    }

    public void setFocused(boolean focused) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.setFocused:(Z)V");
    }

    public boolean keyPressed(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    protected void onDrag(MouseButtonEvent event, double dx, double dy) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.onDrag:(Lnet/minecraft/client/input/MouseButtonEvent;DD)V");
    }

    public void playDownSound(SoundManager soundManager) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.playDownSound:(Lnet/minecraft/client/sounds/SoundManager;)V");
    }

    public void onRelease(MouseButtonEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractSliderButton.onRelease:(Lnet/minecraft/client/input/MouseButtonEvent;)V");
    }

    protected abstract void updateMessage();

    protected abstract void applyValue();

    public AbstractSliderButton() {
    }
}
