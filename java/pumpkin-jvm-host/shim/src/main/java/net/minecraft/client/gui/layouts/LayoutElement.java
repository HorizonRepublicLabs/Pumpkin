package net.minecraft.client.gui.layouts;

import java.util.function.Consumer;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import dev.pumpkin.shim.Unimplemented;

public interface LayoutElement {

    void setX(int x);

    void setY(int y);

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    default ScreenRectangle getRectangle() {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LayoutElement.getRectangle:()Lnet/minecraft/client/gui/navigation/ScreenRectangle;");
    }

    default void setPosition(int x, int y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LayoutElement.setPosition:(II)V");
    }

    void visitWidgets(final Consumer<AbstractWidget> widgetVisitor);
}
