package net.minecraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.gizmos.DrawableGizmoPrimitives;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Quaternionf;
import dev.pumpkin.shim.Unimplemented;

public class SubmitNodeCollection implements OrderedSubmitNodeCollector {

    public void submitShadow(PoseStack poseStack, float radius, List<EntityRenderState.ShadowPiece> pieces) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitShadow:(Lcom/mojang/blaze3d/vertex/PoseStack;FLjava/util/List;)V");
    }

    public void submitNameTag(PoseStack poseStack, Vec3 nameTagAttachment, int offset, Component name, boolean seeThrough, int lightCoords, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitNameTag:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/phys/Vec3;ILnet/minecraft/network/chat/Component;ZILnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public void submitText(PoseStack poseStack, float x, float y, FormattedCharSequence string, boolean dropShadow, Font.DisplayMode displayMode, int lightCoords, int color, int backgroundColor, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitText:(Lcom/mojang/blaze3d/vertex/PoseStack;FFLnet/minecraft/util/FormattedCharSequence;ZLnet/minecraft/client/gui/Font$DisplayMode;IIII)V");
    }

    public void submitFlame(PoseStack poseStack, EntityRenderState renderState, Quaternionf rotation) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitFlame:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/entity/state/EntityRenderState;Lorg/joml/Quaternionf;)V");
    }

    public void submitLeash(PoseStack poseStack, EntityRenderState.LeashState leashState) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitLeash:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/entity/state/EntityRenderState$LeashState;)V");
    }

    public <S> void submitModel(Model<? super S> model, S state, PoseStack poseStack, RenderType renderType, int lightCoords, int overlayCoords, int tintedColor, TextureAtlasSprite sprite, int outlineColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitModel:(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IIILnet/minecraft/client/renderer/texture/TextureAtlasSprite;ILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V");
    }

    public void submitMovingBlock(PoseStack poseStack, MovingBlockRenderState movingBlockRenderState, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitMovingBlock:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/block/MovingBlockRenderState;I)V");
    }

    public void submitBlockModel(PoseStack poseStack, RenderType renderType, List<BlockStateModelPart> modelParts, int[] tintLayers, int lightCoords, int overlayCoords, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitBlockModel:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;Ljava/util/List;[IIII)V");
    }

    public void submitMultiLayerBlockModel(PoseStack poseStack, List<BlockStateModelPart> modelParts, boolean translucent, int[] tintLayers, int lightCoords, int overlayCoords, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitMultiLayerBlockModel:(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/util/List;Z[IIII)V");
    }

    public void submitBreakingBlockModel(PoseStack poseStack, List<BlockStateModelPart> parts, int progress) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitBreakingBlockModel:(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/util/List;I)V");
    }

    public void submitShapeOutline(PoseStack poseStack, VoxelShape shape, RenderType renderType, int color, float width, boolean afterTerrain) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitShapeOutline:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/client/renderer/rendertype/RenderType;IFZ)V");
    }

    public void submitItem(PoseStack poseStack, ItemDisplayContext displayContext, int lightCoords, int overlayCoords, int outlineColor, int[] tintLayers, List<BakedQuad> quads, ItemStackRenderState.FoilType foilType) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitItem:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/item/ItemDisplayContext;III[ILjava/util/List;Lnet/minecraft/client/renderer/item/ItemStackRenderState$FoilType;)V");
    }

    public void submitCustomGeometry(PoseStack poseStack, RenderType renderType, SubmitNodeCollector.CustomGeometryRenderer customGeometryRenderer) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitCustomGeometry:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;Lnet/minecraft/client/renderer/SubmitNodeCollector$CustomGeometryRenderer;)V");
    }

    public void submitQuadParticleGroup(QuadParticleRenderState particles) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitQuadParticleGroup:(Lnet/minecraft/client/renderer/state/level/QuadParticleRenderState;)V");
    }

    public void submitGizmoPrimitives(DrawableGizmoPrimitives.Group group, CameraRenderState camera, boolean onTop) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitGizmoPrimitives:(Lnet/minecraft/client/renderer/gizmos/DrawableGizmoPrimitives$Group;Lnet/minecraft/client/renderer/state/level/CameraRenderState;Z)V");
    }

    public <T extends net.minecraft.client.renderer.feature.submit.SubmitNode, S extends T> void submitSpecial(net.neoforged.neoforge.client.submit.RenderPhaseKey<T> phaseKey, S submitNode) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SubmitNodeCollection.submitSpecial:(Lnet/neoforged/neoforge/client/submit/RenderPhaseKey;Lnet/minecraft/client/renderer/T;)V");
    }

    public SubmitNodeCollection() {
    }
}
