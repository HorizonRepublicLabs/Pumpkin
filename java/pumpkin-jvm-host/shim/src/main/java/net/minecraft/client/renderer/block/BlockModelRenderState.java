package net.minecraft.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.List;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import org.joml.Matrix4fc;
import dev.pumpkin.shim.Unimplemented;

public class BlockModelRenderState {

    public static final int[] EMPTY_TINTS = null;

    private List<BlockStateModelPart> modelParts;

    private Matrix4fc transformation;

    private RenderType renderType;

    private SpecialModelRenderer<?> specialRenderer;

    private Matrix4fc specialRendererTransformation;

    private IntList tintLayers;

    public int blockLightCoords;

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.clear:()V");
    }

    public IntList tintLayers() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.tintLayers:()Lit/unimi/dsi/fastutil/ints/IntList;");
    }

    public List<BlockStateModelPart> setupModel(Matrix4fc transformation, boolean hasTranslucency) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.setupModel:(Lorg/joml/Matrix4fc;Z)Ljava/util/List;");
    }

    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int externalLightCoords, int overlayCoords, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.submit:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V");
    }

    private void submitSpecialRenderer(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int externalLightCoords, int overlayCoords, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.submitSpecialRenderer:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V");
    }

    private void submitModel(RenderType renderType, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int externalLightCoords, int overlayCoords, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.submitModel:(Lnet/minecraft/client/renderer/rendertype/RenderType;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V");
    }

    private static void submitSpecialRenderer(SpecialModelRenderer<?> renderer, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.submitSpecialRenderer:(Lnet/minecraft/client/renderer/special/SpecialModelRenderer;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelRenderState.isEmpty:()Z");
    }

    public BlockModelRenderState() {
    }
}
