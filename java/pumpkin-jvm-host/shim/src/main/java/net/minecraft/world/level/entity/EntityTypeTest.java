package net.minecraft.world.level.entity;

public interface EntityTypeTest<B, T extends B> {

    T tryCast(B entity);

    Class<? extends B> getBaseClass();
}
