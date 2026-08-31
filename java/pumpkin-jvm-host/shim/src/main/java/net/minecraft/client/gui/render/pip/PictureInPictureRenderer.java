package net.minecraft.client.gui.render.pip;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import dev.pumpkin.shim.Unimplemented;

public abstract class PictureInPictureRenderer<T extends PictureInPictureRenderState> implements AutoCloseable {

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/gui/render/pip/PictureInPictureRenderer.close:()V");
    }

    public abstract Class<T> getRenderStateClass();

    protected abstract void renderToTexture(T renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector);

    protected abstract String getTextureLabel();

    public PictureInPictureRenderer() {
    }
}
