package net.minecraft.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public interface NoDataSpecialModelRenderer extends SpecialModelRenderer<Void> {

    default Void extractArgument(ItemStack stack) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/special/NoDataSpecialModelRenderer.extractArgument:(Lnet/minecraft/world/item/ItemStack;)Ljava/lang/Void;");
    }

    default void submit(Void argument, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/special/NoDataSpecialModelRenderer.submit:(Ljava/lang/Void;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V");
    }

    void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, final int outlineColor);

    interface Unbaked extends SpecialModelRenderer.Unbaked<Void> {

        MapCodec<? extends NoDataSpecialModelRenderer.Unbaked> type();
    }
}
