package net.minecraft.client.renderer.entity.state;

import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class HumanoidRenderState extends ArmedEntityRenderState {

    public boolean isFallFlying;

    public float elytraRotX;

    public float elytraRotY;

    public float elytraRotZ;

    public ItemStack headEquipment;

    public ItemStack chestEquipment;

    public ItemStack legsEquipment;

    public ItemStack feetEquipment;

    public float ticksUsingItem(HumanoidArm arm) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/state/HumanoidRenderState.ticksUsingItem:(Lnet/minecraft/world/entity/HumanoidArm;)F");
    }

    public HumanoidRenderState() {
    }
}
