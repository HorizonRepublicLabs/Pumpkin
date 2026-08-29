package net.minecraft.world.phys;

import dev.pumpkin.shim.Unimplemented;

public abstract class HitResult {

    protected HitResult(Vec3 location) {
        throw Unimplemented.forMember("net/minecraft/world/phys/HitResult.<init>:(Lnet/minecraft/world/phys/Vec3;)V");
    }

    public abstract HitResult.Type getType();

    public enum Type {

        MISS, BLOCK, ENTITY
    }

    public HitResult() {
    }
}
