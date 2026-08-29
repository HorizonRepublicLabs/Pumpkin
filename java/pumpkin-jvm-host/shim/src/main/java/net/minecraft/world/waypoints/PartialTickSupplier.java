package net.minecraft.world.waypoints;

import net.minecraft.world.entity.Entity;

public interface PartialTickSupplier {

    float apply(Entity entity);
}
