package net.minecraft.client.renderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.gizmos.DrawableGizmoPrimitives;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import dev.pumpkin.shim.Unimplemented;

public class LevelRenderer implements AutoCloseable {

    public LevelRenderer(EntityRenderDispatcher entityRenderDispatcher, BlockEntityRenderDispatcher blockEntityRenderDispatcher, ModelManager modelManager, TextureManager textureManager, AtlasManager atlasManager, ShaderManager shaderManager, GameRenderer gameRenderer, int width, int height) {
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/LevelRenderer.close:()V");
    }

    private record FinalizedGizmos(DrawableGizmoPrimitives standardPrimitives, DrawableGizmoPrimitives alwaysOnTopPrimitives) {
    }

    public LevelRenderer() {
    }
}
