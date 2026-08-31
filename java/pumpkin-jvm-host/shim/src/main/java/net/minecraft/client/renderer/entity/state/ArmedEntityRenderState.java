package net.minecraft.client.renderer.entity.state;

import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;

public class ArmedEntityRenderState extends LivingEntityRenderState {

    public HumanoidArm mainArm;

    public final ItemStackRenderState rightHandItemState = null;

    public ItemStack rightHandItemStack;

    public final ItemStackRenderState leftHandItemState = null;

    public ItemStack leftHandItemStack;

    public ArmedEntityRenderState() {
    }
}
