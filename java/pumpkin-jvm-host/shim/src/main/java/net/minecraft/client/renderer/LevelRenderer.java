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
        throw Unimplemented.forMember("net/minecraft/client/renderer/LevelRenderer.<init>:(Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;Lnet/minecraft/client/renderer/blockentity/BlockEntityRenderDispatcher;Lnet/minecraft/client/resources/model/ModelManager;Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/client/resources/model/sprite/AtlasManager;Lnet/minecraft/client/renderer/ShaderManager;Lnet/minecraft/client/renderer/GameRenderer;II)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/LevelRenderer.close:()V");
    }

    private record FinalizedGizmos(DrawableGizmoPrimitives standardPrimitives, DrawableGizmoPrimitives alwaysOnTopPrimitives) {
    }

    protected LevelRenderer() {
    }
}
