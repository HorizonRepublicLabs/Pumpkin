package net.minecraft.client.model.monster.skeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.world.entity.HumanoidArm;
import dev.pumpkin.shim.Unimplemented;

public class SkeletonModel<S extends SkeletonRenderState> extends HumanoidModel<S> {

    public SkeletonModel(ModelPart root) {
    }

    public static LayerDefinition createBodyLayer() {
        throw Unimplemented.forMember("net/minecraft/client/model/monster/skeleton/SkeletonModel.createBodyLayer:()Lnet/minecraft/client/model/geom/builders/LayerDefinition;");
    }

    public static LayerDefinition createSingleModelDualBodyLayer() {
        throw Unimplemented.forMember("net/minecraft/client/model/monster/skeleton/SkeletonModel.createSingleModelDualBodyLayer:()Lnet/minecraft/client/model/geom/builders/LayerDefinition;");
    }

    public void translateToHand(SkeletonRenderState state, HumanoidArm arm, PoseStack poseStack) {
        throw Unimplemented.forMember("net/minecraft/client/model/monster/skeleton/SkeletonModel.translateToHand:(Lnet/minecraft/client/renderer/entity/state/SkeletonRenderState;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;)V");
    }

    public SkeletonModel() {
    }
}
