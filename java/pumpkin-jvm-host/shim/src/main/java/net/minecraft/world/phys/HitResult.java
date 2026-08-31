package net.minecraft.world.phys;

import dev.pumpkin.shim.Unimplemented;

public abstract class HitResult {

    protected HitResult(Vec3 location) {
    }

    public abstract HitResult.Type getType();

    public Vec3 getLocation() {
        throw Unimplemented.forMember("net/minecraft/world/phys/HitResult.getLocation:()Lnet/minecraft/world/phys/Vec3;");
    }

    public enum Type {

        MISS, BLOCK, ENTITY
    }

    public HitResult() {
    }
}
