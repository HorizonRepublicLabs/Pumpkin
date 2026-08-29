package net.minecraft.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import dev.pumpkin.shim.Unimplemented;

public class ItemStackRenderState {

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.clear:()V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.isEmpty:()Z");
    }

    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.submit:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V");
    }

    public enum FoilType {

        NONE, STANDARD, SPECIAL
    }

    public class LayerRenderState {

        public void clear() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState.clear:()V");
        }

        public LayerRenderState() {
        }
    }

    public ItemStackRenderState() {
    }
}
