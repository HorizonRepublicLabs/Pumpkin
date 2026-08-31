package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.item.PrimedTnt;
import dev.pumpkin.shim.Unimplemented;

public class TntRenderer extends EntityRenderer<PrimedTnt, TntRenderState> {

    public TntRenderer(EntityRendererProvider.Context context) {
    }

    public void submit(TntRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/TntRenderer.submit:(Lnet/minecraft/client/renderer/entity/state/TntRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public TntRenderState createRenderState() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/TntRenderer.createRenderState:()Lnet/minecraft/client/renderer/entity/state/TntRenderState;");
    }

    public TntRenderer() {
    }
}
