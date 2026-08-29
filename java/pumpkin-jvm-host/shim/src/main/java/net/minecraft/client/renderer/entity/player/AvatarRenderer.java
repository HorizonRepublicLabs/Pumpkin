package net.minecraft.client.renderer.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.entity.ClientAvatarEntity;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;
import dev.pumpkin.shim.Unimplemented;

public class AvatarRenderer<AvatarlikeEntity extends Avatar & ClientAvatarEntity> extends LivingEntityRenderer<AvatarlikeEntity, AvatarRenderState, PlayerModel> {

    public AvatarRenderer(EntityRendererProvider.Context context, boolean slimSteve) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/player/AvatarRenderer.<init>:(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;Z)V");
    }

    public Identifier getTextureLocation(AvatarRenderState state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/player/AvatarRenderer.getTextureLocation:(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;)Lnet/minecraft/resources/Identifier;");
    }

    public void submit(AvatarRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/player/AvatarRenderer.submit:(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V");
    }

    public AvatarRenderState createRenderState() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/player/AvatarRenderer.createRenderState:()Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;");
    }

    public AvatarRenderer() {
    }
}
