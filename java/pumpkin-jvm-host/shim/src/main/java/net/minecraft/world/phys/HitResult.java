package net.minecraft.world.phys;

public abstract class HitResult {

    protected HitResult(Vec3 location) {
    }

    public abstract HitResult.Type getType();

    public enum Type {

        MISS, BLOCK, ENTITY
    }

    public HitResult() {
    }
}
