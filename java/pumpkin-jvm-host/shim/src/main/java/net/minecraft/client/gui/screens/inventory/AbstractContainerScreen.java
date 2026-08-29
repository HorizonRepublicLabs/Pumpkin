package net.minecraft.client.gui.screens.inventory;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractContainerScreen<T extends AbstractContainerMenu> extends Screen implements MenuAccess<T> {

    public AbstractContainerScreen(T menu, Inventory inventory, Component title) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.<init>:(Lnet/minecraft/world/inventory/AbstractContainerMenu;Lnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/network/chat/Component;)V");
    }

    public AbstractContainerScreen(T menu, Inventory inventory, Component title, int imageWidth, int imageHeight) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.<init>:(Lnet/minecraft/world/inventory/AbstractContainerMenu;Lnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/network/chat/Component;II)V");
    }

    protected void init() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.init:()V");
    }

    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.extractRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.extractContents:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.mouseScrolled:(DDDD)Z");
    }

    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.mouseClicked:(Lnet/minecraft/client/input/MouseButtonEvent;Z)Z");
    }

    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.mouseDragged:(Lnet/minecraft/client/input/MouseButtonEvent;DD)Z");
    }

    public boolean mouseReleased(MouseButtonEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.mouseReleased:(Lnet/minecraft/client/input/MouseButtonEvent;)Z");
    }

    public boolean keyPressed(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    public void removed() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.removed:()V");
    }

    public boolean isPauseScreen() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.isPauseScreen:()Z");
    }

    public boolean isInGameUi() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.isInGameUi:()Z");
    }

    public final void tick() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.tick:()V");
    }

    public T getMenu() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.getMenu:()Lnet/minecraft/world/inventory/AbstractContainerMenu;");
    }

    public void onClose() {
        throw Unimplemented.forMember("net/minecraft/client/gui/screens/inventory/AbstractContainerScreen.onClose:()V");
    }

    protected AbstractContainerScreen() {
    }
}
