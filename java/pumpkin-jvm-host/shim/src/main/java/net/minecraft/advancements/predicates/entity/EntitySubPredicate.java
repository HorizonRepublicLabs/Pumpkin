package net.minecraft.advancements.predicates.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public interface EntitySubPredicate {

    boolean matches(Entity entity, ServerLevel level, Vec3 position);
}
