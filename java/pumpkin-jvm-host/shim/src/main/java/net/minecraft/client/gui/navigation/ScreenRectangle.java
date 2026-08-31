package net.minecraft.client.gui.navigation;

import org.joml.Matrix3x2fc;
import dev.pumpkin.shim.Unimplemented;

public record ScreenRectangle(ScreenPosition position, int width, int height) {

    public ScreenRectangle(int x, int y, int width, int height) {
        this((ScreenPosition) null, (int) 0, (int) 0);
    }

    public ScreenRectangle intersection(ScreenRectangle other) {
        throw Unimplemented.forMember("net/minecraft/client/gui/navigation/ScreenRectangle.intersection:(Lnet/minecraft/client/gui/navigation/ScreenRectangle;)Lnet/minecraft/client/gui/navigation/ScreenRectangle;");
    }

    public int top() {
        throw Unimplemented.forMember("net/minecraft/client/gui/navigation/ScreenRectangle.top:()I");
    }

    public int bottom() {
        throw Unimplemented.forMember("net/minecraft/client/gui/navigation/ScreenRectangle.bottom:()I");
    }

    public int left() {
        throw Unimplemented.forMember("net/minecraft/client/gui/navigation/ScreenRectangle.left:()I");
    }

    public int right() {
        throw Unimplemented.forMember("net/minecraft/client/gui/navigation/ScreenRectangle.right:()I");
    }

    public ScreenRectangle transformMaxBounds(Matrix3x2fc matrix) {
        throw Unimplemented.forMember("net/minecraft/client/gui/navigation/ScreenRectangle.transformMaxBounds:(Lorg/joml/Matrix3x2fc;)Lnet/minecraft/client/gui/navigation/ScreenRectangle;");
    }
}
