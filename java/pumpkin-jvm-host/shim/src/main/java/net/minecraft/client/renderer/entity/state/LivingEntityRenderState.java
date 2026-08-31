package net.minecraft.client.renderer.entity.state;

import net.minecraft.client.renderer.item.ItemStackRenderState;

public class LivingEntityRenderState extends EntityRenderState {

    public float bodyRot;

    public float yRot;

    public float xRot;

    public float scale;

    public boolean isBaby;

    public boolean isInvisibleToPlayer;

    public final ItemStackRenderState headItem = null;

    public LivingEntityRenderState() {
    }
}
