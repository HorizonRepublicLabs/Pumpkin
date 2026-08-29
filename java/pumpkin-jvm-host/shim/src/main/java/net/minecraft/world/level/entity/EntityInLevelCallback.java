package net.minecraft.world.level.entity;

import net.minecraft.world.entity.Entity;

public interface EntityInLevelCallback {

    void onMove();

    void onRemove(final Entity.RemovalReason reason);
}
