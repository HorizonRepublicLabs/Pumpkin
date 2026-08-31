package net.minecraft.world;

import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class TickRateManager {

    protected float tickrate;

    public float tickrate() {
        throw Unimplemented.forMember("net/minecraft/world/TickRateManager.tickrate:()F");
    }

    public boolean runsNormally() {
        throw Unimplemented.forMember("net/minecraft/world/TickRateManager.runsNormally:()Z");
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
