package net.minecraft.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import dev.pumpkin.shim.Unimplemented;

public class HumanoidArmorLayer<S extends HumanoidRenderState, M extends HumanoidModel<S>, A extends HumanoidModel<S>> extends RenderLayer<S, M> {

    private final ArmorModelSet<A> modelSet = null;

    private final ArmorModelSet<A> babyModelSet = null;

    public HumanoidArmorLayer(RenderLayerParent<S, M> renderer, ArmorModelSet<A> modelSet, EquipmentLayerRenderer equipmentRenderer) {
    }

    public HumanoidArmorLayer(RenderLayerParent<S, M> renderer, ArmorModelSet<A> modelSet, ArmorModelSet<A> babyModelSet, EquipmentLayerRenderer equipmentRenderer) {
    }

    public static boolean shouldRender(ItemStack itemStack, EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/layers/HumanoidArmorLayer.shouldRender:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    private static boolean shouldRender(Equippable equippable, EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/layers/HumanoidArmorLayer.shouldRender:(Lnet/minecraft/world/item/equipment/Equippable;Lnet/minecraft/world/entity/EquipmentSlot;)Z");
    }

    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, float yRot, float xRot) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/layers/HumanoidArmorLayer.submit:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FF)V");
    }

    private A getArmorModel(S state, EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/layers/HumanoidArmorLayer.getArmorModel:(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/client/model/HumanoidModel;");
    }

    public HumanoidArmorLayer() {
    }
}
