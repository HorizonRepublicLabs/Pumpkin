package net.minecraft.world.attribute;

import net.minecraft.world.phys.Vec3;

public interface EnvironmentAttributeReader {

    <Value> Value getDimensionValue(EnvironmentAttribute<Value> attribute);

    <Value> Value getValue(EnvironmentAttribute<Value> attribute, Vec3 pos, SpatialAttributeInterpolator biomeInterpolator);
}
