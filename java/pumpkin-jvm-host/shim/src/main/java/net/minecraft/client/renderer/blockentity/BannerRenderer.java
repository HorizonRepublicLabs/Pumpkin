package net.minecraft.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.function.Consumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.state.BannerRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public class BannerRenderer implements BlockEntityRenderer<BannerBlockEntity, BannerRenderState> {

    public BannerRenderer(BlockEntityRendererProvider.Context context) {
    }

    public BannerRenderer(SpecialModelRenderer.BakingContext context) {
    }

    public BannerRenderer(EntityModelSet modelSet, SpriteGetter sprites) {
    }

    public BannerRenderState createRenderState() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/BannerRenderer.createRenderState:()Lnet/minecraft/client/renderer/blockentity/state/BannerRenderState;");
    }

    public void submit(BannerRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/BannerRenderer.submit:(Lnet/minecraft/client/renderer/blockentity/state/BannerRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public static <S> void submitPatterns(SpriteGetter sprites, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, Model<S> model, S state, boolean banner, DyeColor baseColor, BannerPatternLayers patterns, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/BannerRenderer.submitPatterns:(Lnet/minecraft/client/resources/model/sprite/SpriteGetter;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IILnet/minecraft/client/model/Model;Ljava/lang/Object;ZLnet/minecraft/world/item/DyeColor;Lnet/minecraft/world/level/block/entity/BannerPatternLayers;Lnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V");
    }

    public void getExtents(Consumer<Vector3fc> output) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/BannerRenderer.getExtents:(Ljava/util/function/Consumer;)V");
    }

    public net.minecraft.world.phys.AABB getRenderBoundingBox(BannerBlockEntity blockEntity) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/BannerRenderer.getRenderBoundingBox:(Lnet/minecraft/world/level/block/entity/BannerBlockEntity;)Lnet/minecraft/world/phys/AABB;");
    }

    public BannerRenderer() {
    }
}
