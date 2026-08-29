package net.minecraft.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.function.Supplier;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import dev.pumpkin.shim.Unimplemented;

public class BlockEntityRenderDispatcher implements ResourceManagerReloadListener {

    public BlockEntityRenderDispatcher(Font font, Supplier<EntityModelSet> entityModelSet, BlockModelResolver blockModelResolver, ItemModelResolver itemModelResolver, EntityRenderDispatcher entityRenderer, SpriteGetter sprites, PlayerSkinRenderCache playerSkinRenderCache) {
    }

    public <S extends BlockEntityRenderState> void submit(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/BlockEntityRenderDispatcher.submit:(Lnet/minecraft/client/renderer/blockentity/state/BlockEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public void onResourceManagerReload(ResourceManager resourceManager) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/BlockEntityRenderDispatcher.onResourceManagerReload:(Lnet/minecraft/server/packs/resources/ResourceManager;)V");
    }

    public BlockEntityRenderDispatcher() {
    }
}
