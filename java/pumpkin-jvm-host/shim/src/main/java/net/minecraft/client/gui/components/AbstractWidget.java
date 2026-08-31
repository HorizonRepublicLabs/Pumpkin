package net.minecraft.client.gui.components;

import java.util.function.Consumer;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractWidget implements LayoutElement, Renderable, GuiEventListener, NarratableEntry {

    protected int width;

    protected int height;

    protected boolean isHovered;

    public boolean active;

    public boolean visible;

    protected float alpha;

    private boolean focused;

    private final WidgetTooltipHolder tooltip = null;

    public AbstractWidget(int x, int y, int width, int height, Component message) {
    }

    public int getHeight() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.getHeight:()I");
    }

    public final void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.extractRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    public void setTooltip(Tooltip tooltip) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.setTooltip:(Lnet/minecraft/client/gui/components/Tooltip;)V");
    }

    protected abstract void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a);

    public void onClick(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.onClick:(Lnet/minecraft/client/input/MouseButtonEvent;Z)V");
    }

    public void onRelease(MouseButtonEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.onRelease:(Lnet/minecraft/client/input/MouseButtonEvent;)V");
    }

    protected void onDrag(MouseButtonEvent event, double dx, double dy) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.onDrag:(Lnet/minecraft/client/input/MouseButtonEvent;DD)V");
    }

    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.mouseClicked:(Lnet/minecraft/client/input/MouseButtonEvent;Z)Z");
    }

    public boolean mouseReleased(MouseButtonEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.mouseReleased:(Lnet/minecraft/client/input/MouseButtonEvent;)Z");
    }

    protected boolean isValidClickButton(MouseButtonInfo buttonInfo) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.isValidClickButton:(Lnet/minecraft/client/input/MouseButtonInfo;)Z");
    }

    public boolean mouseDragged(MouseButtonEvent event, double dx, double dy) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.mouseDragged:(Lnet/minecraft/client/input/MouseButtonEvent;DD)Z");
    }

    public ComponentPath nextFocusPath(FocusNavigationEvent navigationEvent) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.nextFocusPath:(Lnet/minecraft/client/gui/navigation/FocusNavigationEvent;)Lnet/minecraft/client/gui/ComponentPath;");
    }

    public boolean isMouseOver(double mouseX, double mouseY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.isMouseOver:(DD)Z");
    }

    public void playDownSound(SoundManager soundManager) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.playDownSound:(Lnet/minecraft/client/sounds/SoundManager;)V");
    }

    public int getWidth() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.getWidth:()I");
    }

    public void setWidth(int width) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.setWidth:(I)V");
    }

    public Component getMessage() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.getMessage:()Lnet/minecraft/network/chat/Component;");
    }

    public boolean isFocused() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.isFocused:()Z");
    }

    public boolean isHovered() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.isHovered:()Z");
    }

    public boolean isHoveredOrFocused() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.isHoveredOrFocused:()Z");
    }

    public boolean isActive() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.isActive:()Z");
    }

    public void setFocused(boolean focused) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.setFocused:(Z)V");
    }

    protected int packedFGColor;

    public int getFGColor() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.getFGColor:()I");
    }

    public NarratableEntry.NarrationPriority narrationPriority() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.narrationPriority:()Lnet/minecraft/client/gui/narration/NarratableEntry$NarrationPriority;");
    }

    public final void updateNarration(NarrationElementOutput output) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.updateNarration:(Lnet/minecraft/client/gui/narration/NarrationElementOutput;)V");
    }

    protected abstract void updateWidgetNarration(final NarrationElementOutput output);

    public int getX() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.getX:()I");
    }

    public void setX(int x) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.setX:(I)V");
    }

    public int getY() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.getY:()I");
    }

    public void setY(int y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.setY:(I)V");
    }

    public int getRight() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.getRight:()I");
    }

    public int getBottom() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.getBottom:()I");
    }

    public void visitWidgets(Consumer<AbstractWidget> widgetVisitor) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.visitWidgets:(Ljava/util/function/Consumer;)V");
    }

    public ScreenRectangle getRectangle() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.getRectangle:()Lnet/minecraft/client/gui/navigation/ScreenRectangle;");
    }

    public int getTabOrderGroup() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget.getTabOrderGroup:()I");
    }

    public abstract static class WithInactiveMessage extends AbstractWidget {

        public WithInactiveMessage(int x, int y, int width, int height, Component message) {
        }

        public Component getMessage() {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget$WithInactiveMessage.getMessage:()Lnet/minecraft/network/chat/Component;");
        }

        public void setMessage(Component message) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/AbstractWidget$WithInactiveMessage.setMessage:(Lnet/minecraft/network/chat/Component;)V");
        }

        public WithInactiveMessage() {
        }
    }

    public AbstractWidget() {
    }
}
