package net.minecraft.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.function.Function;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public class HumanoidModel<T extends HumanoidRenderState> extends EntityModel<T> implements ArmedModel<T>, HeadedModel {

    public HumanoidModel(ModelPart root) {
    }

    public HumanoidModel(ModelPart root, Function<Identifier, RenderType> renderType) {
    }

    public void translateToHand(HumanoidRenderState state, HumanoidArm arm, PoseStack poseStack) {
        throw Unimplemented.forMember("net/minecraft/client/model/HumanoidModel.translateToHand:(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;)V");
    }

    public ModelPart getHead() {
        throw Unimplemented.forMember("net/minecraft/client/model/HumanoidModel.getHead:()Lnet/minecraft/client/model/geom/ModelPart;");
    }

    public enum ArmPose implements IExtensibleEnum {

        EMPTY,
        ITEM,
        BLOCK,
        BOW_AND_ARROW,
        THROW_TRIDENT,
        CROSSBOW_CHARGE,
        CROSSBOW_HOLD,
        SPYGLASS,
        TOOT_HORN,
        BRUSH,
        SPEAR {

            public <S extends ArmedEntityRenderState> void animateUseItem(S state, PoseStack poseStack, float ticksUsingItem, HumanoidArm arm, ItemStack actualItem) {
                throw Unimplemented.forMember("net/minecraft/client/model/HumanoidModel$ArmPose$SPEAR.animateUseItem:()");
            }
        }

    }

    public HumanoidModel() {
    }
}
