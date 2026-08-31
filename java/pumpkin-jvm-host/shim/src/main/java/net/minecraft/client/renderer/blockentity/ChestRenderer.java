package net.minecraft.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import dev.pumpkin.shim.Unimplemented;

public class ChestRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T, ChestRenderState> {

    public ChestRenderer(BlockEntityRendererProvider.Context context) {
    }

    public ChestRenderState createRenderState() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/ChestRenderer.createRenderState:()Lnet/minecraft/client/renderer/blockentity/state/ChestRenderState;");
    }

    public void submit(ChestRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/ChestRenderer.submit:(Lnet/minecraft/client/renderer/blockentity/state/ChestRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    protected SpriteId getCustomSprite(T blockEntity, ChestRenderState renderState) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/ChestRenderer.getCustomSprite:(Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/client/renderer/blockentity/state/ChestRenderState;)Lnet/minecraft/client/resources/model/sprite/SpriteId;");
    }

    public net.minecraft.world.phys.AABB getRenderBoundingBox(T blockEntity) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/blockentity/ChestRenderer.getRenderBoundingBox:(Lnet/minecraft/world/level/block/entity/BlockEntity;)Lnet/minecraft/world/phys/AABB;");
    }

    public ChestRenderer() {
    }
}
