package net.minecraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.state.MapRenderState;
import net.minecraft.client.resources.MapTextureManager;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import dev.pumpkin.shim.Unimplemented;

public class MapRenderer {

    public MapRenderer(AtlasManager atlasManager, MapTextureManager mapTextureManager) {
    }

    public void render(MapRenderState mapRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, boolean showOnlyFrame, int lightCoords) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/MapRenderer.render:(Lnet/minecraft/client/renderer/state/MapRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ZI)V");
    }

    public MapRenderer() {
    }
}
