package net.minecraft.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.function.Function;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class HumanoidModel<T extends HumanoidRenderState> extends EntityModel<T> implements ArmedModel<T>, HeadedModel {

    public static final MeshTransformer BABY_TRANSFORMER = Stubs.of(MeshTransformer.class, "net/minecraft/client/model/geom/builders/MeshTransformer");

    public final ModelPart head = null;

    public final ModelPart hat = null;

    public final ModelPart body = null;

    public final ModelPart rightArm = null;

    public final ModelPart leftArm = null;

    public final ModelPart rightLeg = null;

    public final ModelPart leftLeg = null;

    public HumanoidModel(ModelPart root) {
    }

    public HumanoidModel(ModelPart root, Function<Identifier, RenderType> renderType) {
    }

    public static MeshDefinition createMesh(CubeDeformation g, float yOffset) {
        throw Unimplemented.forMember("net/minecraft/client/model/HumanoidModel.createMesh:(Lnet/minecraft/client/model/geom/builders/CubeDeformation;F)Lnet/minecraft/client/model/geom/builders/MeshDefinition;");
    }

    public static ArmorModelSet<MeshDefinition> createBabyArmorMeshSet(CubeDeformation innerDeformation, CubeDeformation outerDeformation, PartPose armOffset) {
        throw Unimplemented.forMember("net/minecraft/client/model/HumanoidModel.createBabyArmorMeshSet:(Lnet/minecraft/client/model/geom/builders/CubeDeformation;Lnet/minecraft/client/model/geom/builders/CubeDeformation;Lnet/minecraft/client/model/geom/PartPose;)Lnet/minecraft/client/renderer/entity/ArmorModelSet;");
    }

    public void setupAnim(T state) {
        throw Unimplemented.forMember("net/minecraft/client/model/HumanoidModel.setupAnim:(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V");
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
