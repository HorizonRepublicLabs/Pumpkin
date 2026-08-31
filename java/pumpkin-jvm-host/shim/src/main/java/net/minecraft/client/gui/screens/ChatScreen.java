package net.minecraft.client.gui.screens;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import dev.pumpkin.shim.Unimplemented;

public class ChatScreen extends Screen {

    public ChatScreen(String initial, boolean isDraft) {
    }

    public ChatScreen(String initial, boolean isDraft, boolean closeOnSubmit) {
    }

    protected void init() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.init:()V");
    }

    protected void setInitialFocus() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.setInitialFocus:()V");
    }

    public void resize(int width, int height) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.resize:(II)V");
    }

    public void onClose() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.onClose:()V");
    }

    public void removed() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.removed:()V");
    }

    public boolean keyPressed(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.mouseScrolled:(DDDD)Z");
    }

    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.mouseClicked:(Lnet/minecraft/client/input/MouseButtonEvent;Z)Z");
    }

    public void insertText(String text, boolean replace) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.insertText:(Ljava/lang/String;Z)V");
    }

    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.extractRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.extractBackground:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    public boolean isPauseScreen() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.isPauseScreen:()Z");
    }

    public boolean isAllowedInPortal() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.isAllowedInPortal:()Z");
    }

    protected void updateNarrationState(NarrationElementOutput output) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/ChatScreen.updateNarrationState:(Lnet/minecraft/client/gui/narration/NarrationElementOutput;)V");
    }

    public interface ChatConstructor<T extends ChatScreen> {

        T create(String initial, boolean isDraft);
    }

    protected enum ExitReason {

        INTENTIONAL, INTERRUPTED, DONE
    }

    public ChatScreen() {
    }
}
