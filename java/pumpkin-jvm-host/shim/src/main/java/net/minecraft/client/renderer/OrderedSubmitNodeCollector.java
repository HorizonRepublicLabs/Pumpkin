package net.minecraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
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
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Quaternionf;
import net.neoforged.neoforge.client.extensions.OrderedSubmitNodeCollectorExtension;
import dev.pumpkin.shim.Unimplemented;

public interface OrderedSubmitNodeCollector extends OrderedSubmitNodeCollectorExtension {

    void submitShadow(PoseStack poseStack, float radius, List<EntityRenderState.ShadowPiece> pieces);

    void submitNameTag(PoseStack poseStack, Vec3 nameTagAttachment, final int offset, Component name, boolean seeThrough, int lightCoords, final CameraRenderState camera);

    void submitText(PoseStack poseStack, float x, float y, FormattedCharSequence string, boolean dropShadow, Font.DisplayMode displayMode, int lightCoords, int color, int backgroundColor, int outlineColor);

    void submitFlame(PoseStack poseStack, EntityRenderState renderState, Quaternionf rotation);

    void submitLeash(PoseStack poseStack, EntityRenderState.LeashState leashState);

    <S> void submitModel(Model<? super S> model, S state, PoseStack poseStack, RenderType renderType, int lightCoords, int overlayCoords, int tintedColor, TextureAtlasSprite sprite, int outlineColor, final ModelFeatureRenderer.CrumblingOverlay crumblingOverlay);

    default <S> void submitModel(Model<? super S> model, S state, PoseStack poseStack, RenderType renderType, int lightCoords, int overlayCoords, int outlineColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/OrderedSubmitNodeCollector.submitModel:(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IIILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V");
    }

    default <S> void submitModel(Model<? super S> model, S state, PoseStack poseStack, Identifier texture, int lightCoords, int overlayCoords, int outlineColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/OrderedSubmitNodeCollector.submitModel:(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/resources/Identifier;IIILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V");
    }

    default <S> void submitModel(Model<S> model, S state, PoseStack poseStack, int lightCoords, int overlayCoords, int tintedColor, SpriteId sprite, SpriteGetter sprites, int outlineColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/OrderedSubmitNodeCollector.submitModel:(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;IIILnet/minecraft/client/resources/model/sprite/SpriteId;Lnet/minecraft/client/resources/model/sprite/SpriteGetter;ILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V");
    }

    default void submitModelPart(ModelPart modelPart, PoseStack poseStack, RenderType renderType, int lightCoords, int overlayCoords, TextureAtlasSprite sprite) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/OrderedSubmitNodeCollector.submitModelPart:(Lnet/minecraft/client/model/geom/ModelPart;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IILnet/minecraft/client/renderer/texture/TextureAtlasSprite;)V");
    }

    default void submitModelPart(ModelPart modelPart, PoseStack poseStack, RenderType renderType, int lightCoords, int overlayCoords, TextureAtlasSprite sprite, int tintedColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/OrderedSubmitNodeCollector.submitModelPart:(Lnet/minecraft/client/model/geom/ModelPart;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IILnet/minecraft/client/renderer/texture/TextureAtlasSprite;ILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V");
    }

    default void submitModelPart(ModelPart modelPart, PoseStack poseStack, RenderType renderType, int lightCoords, int overlayCoords, TextureAtlasSprite sprite, int tintedColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/OrderedSubmitNodeCollector.submitModelPart:(Lnet/minecraft/client/model/geom/ModelPart;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IILnet/minecraft/client/renderer/texture/TextureAtlasSprite;ILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;I)V");
    }

    void submitMovingBlock(PoseStack poseStack, MovingBlockRenderState movingBlockRenderState, int outlineColor);

    void submitBlockModel(PoseStack poseStack, RenderType renderType, List<BlockStateModelPart> parts, int[] tintLayers, int lightCoords, int overlayCoords, int outlineColor);

    void submitBreakingBlockModel(PoseStack poseStack, List<BlockStateModelPart> parts, int progress);

    void submitShapeOutline(PoseStack poseStack, VoxelShape shape, RenderType renderType, int color, float width, boolean afterTerrain);

    void submitItem(PoseStack poseStack, ItemDisplayContext displayContext, int lightCoords, int overlayCoords, int outlineColor, int[] tintLayers, List<BakedQuad> quads, ItemStackRenderState.FoilType foilType);

    void submitCustomGeometry(PoseStack poseStack, RenderType renderType, SubmitNodeCollector.CustomGeometryRenderer customGeometryRenderer);

    void submitQuadParticleGroup(QuadParticleRenderState particles);

    void submitGizmoPrimitives(DrawableGizmoPrimitives.Group group, CameraRenderState camera, boolean onTop);
}
