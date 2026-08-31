package net.minecraft.client.gui.layouts;

import java.util.function.Consumer;
import net.minecraft.client.gui.components.AbstractWidget;
import dev.pumpkin.shim.Unimplemented;

public interface Layout extends LayoutElement {

    void visitChildren(Consumer<LayoutElement> layoutElementVisitor);

    default void visitWidgets(Consumer<AbstractWidget> widgetVisitor) {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/Layout.visitWidgets:(Ljava/util/function/Consumer;)V");
    }

    void removeChildren();
}
