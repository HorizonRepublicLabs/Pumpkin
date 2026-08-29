package net.minecraft.world.entity;

public interface PostSpawnProcessor<T extends Entity> {

    void apply(T target);
}
