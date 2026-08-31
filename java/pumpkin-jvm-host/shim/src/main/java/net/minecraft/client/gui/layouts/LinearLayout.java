package net.minecraft.client.gui.layouts;

import java.util.function.Consumer;
import dev.pumpkin.shim.Unimplemented;

public class LinearLayout implements Layout {

    private LinearLayout(LinearLayout.Orientation orientation) {
    }

    public LinearLayout(int x, int y, LinearLayout.Orientation orientation) {
    }

    public void visitChildren(Consumer<LayoutElement> layoutElementVisitor) {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LinearLayout.visitChildren:(Ljava/util/function/Consumer;)V");
    }

    public void removeChildren() {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LinearLayout.removeChildren:()V");
    }

    public void arrangeElements() {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LinearLayout.arrangeElements:()V");
    }

    public int getWidth() {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LinearLayout.getWidth:()I");
    }

    public int getHeight() {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LinearLayout.getHeight:()I");
    }

    public void setX(int x) {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LinearLayout.setX:(I)V");
    }

    public void setY(int y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LinearLayout.setY:(I)V");
    }

    public int getX() {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LinearLayout.getX:()I");
    }

    public int getY() {
        throw Unimplemented.forMember("net/minecraft/client/gui/layouts/LinearLayout.getY:()I");
    }

    public enum Orientation {

        HORIZONTAL, VERTICAL
    }

    public LinearLayout() {
    }
}
