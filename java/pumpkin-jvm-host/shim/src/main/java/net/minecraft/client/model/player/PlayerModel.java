package net.minecraft.client.model.player;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.HumanoidArm;
import dev.pumpkin.shim.Unimplemented;

public class PlayerModel extends HumanoidModel<AvatarRenderState> {

    public PlayerModel(ModelPart root, boolean slim) {
        throw Unimplemented.forMember("net/minecraft/client/model/player/PlayerModel.<init>:(Lnet/minecraft/client/model/geom/ModelPart;Z)V");
    }

    public void translateToHand(AvatarRenderState state, HumanoidArm arm, PoseStack poseStack) {
        throw Unimplemented.forMember("net/minecraft/client/model/player/PlayerModel.translateToHand:(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;)V");
    }

    public PlayerModel() {
    }
}
