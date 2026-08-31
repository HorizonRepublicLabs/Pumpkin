package net.minecraft.client.renderer.state.gui.pip;

import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.state.gui.ScreenArea;
import org.joml.Matrix3x2fc;
import dev.pumpkin.shim.Unimplemented;

public interface PictureInPictureRenderState extends ScreenArea {

    int x0();

    int x1();

    int y0();

    int y1();

    float scale();

    default Matrix3x2fc pose() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/gui/pip/PictureInPictureRenderState.pose:()Lorg/joml/Matrix3x2fc;");
    }

    ScreenRectangle scissorArea();

    static ScreenRectangle getBounds(int x0, int y0, int x1, int y1, ScreenRectangle scissorArea) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/gui/pip/PictureInPictureRenderState.getBounds:(IIIILnet/minecraft/client/gui/navigation/ScreenRectangle;)Lnet/minecraft/client/gui/navigation/ScreenRectangle;");
    }
}
