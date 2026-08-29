package net.minecraft.client.renderer.entity.state;

import net.minecraft.world.entity.HumanoidArm;
import dev.pumpkin.shim.Unimplemented;

public class HumanoidRenderState extends ArmedEntityRenderState {

    public float ticksUsingItem(HumanoidArm arm) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/state/HumanoidRenderState.ticksUsingItem:(Lnet/minecraft/world/entity/HumanoidArm;)F");
    }

    public HumanoidRenderState() {
    }
}
