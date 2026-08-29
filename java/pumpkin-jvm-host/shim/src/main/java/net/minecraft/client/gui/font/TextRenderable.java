package net.minecraft.client.gui.font;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.joml.Matrix4fc;
import dev.pumpkin.shim.Unimplemented;

public interface TextRenderable {

    void render(Matrix4fc pose, VertexConsumer buffer, int packedLightCoords, boolean flat);

    RenderType renderType(Font.DisplayMode displayMode);

    GpuTextureView textureView();

    RenderPipeline guiPipeline();

    float left();

    float top();

    float right();

    float bottom();

    interface Styled extends TextRenderable, ActiveArea {

        default float activeLeft() {
            throw Unimplemented.forMember("net/minecraft/client/gui/font/TextRenderable$Styled.activeLeft:()F");
        }

        default float activeTop() {
            throw Unimplemented.forMember("net/minecraft/client/gui/font/TextRenderable$Styled.activeTop:()F");
        }

        default float activeRight() {
            throw Unimplemented.forMember("net/minecraft/client/gui/font/TextRenderable$Styled.activeRight:()F");
        }

        default float activeBottom() {
            throw Unimplemented.forMember("net/minecraft/client/gui/font/TextRenderable$Styled.activeBottom:()F");
        }
    }
}
