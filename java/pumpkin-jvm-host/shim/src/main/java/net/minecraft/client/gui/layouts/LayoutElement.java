package net.minecraft.client.gui.layouts;

import java.util.function.Consumer;
import net.minecraft.client.gui.components.AbstractWidget;

public interface LayoutElement {

    void setX(int x);

    void setY(int y);

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    void visitWidgets(final Consumer<AbstractWidget> widgetVisitor);
}
