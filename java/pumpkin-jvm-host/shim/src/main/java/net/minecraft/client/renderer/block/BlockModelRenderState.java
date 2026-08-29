package net.minecraft.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import dev.pumpkin.shim.Unimplemented;

public class BlockModelRenderState {

    private RenderType renderType;

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.clear:()V");
    }

    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int externalLightCoords, int overlayCoords, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.submit:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.isEmpty:()Z");
    }

    public BlockModelRenderState() {
    }
}
