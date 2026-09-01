package net.minecraft.world;

import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class TickRateManager {

    protected float tickrate;

    public float tickrate() {
        throw Unimplemented.forMember("net/minecraft/world/TickRateManager.tickrate:()F");
    }

    // Pumpkin divergence: real body. Pumpkin has no tick freeze or sprint; the rate
    // is always the normal one.
    public boolean runsNormally() {
        return true;
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/TickRateManager.tick:()V");
    }

    public boolean isEntityFrozen(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/TickRateManager.isEntityFrozen:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public TickRateManager() {
    }
}
