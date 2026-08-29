package net.minecraft.world.attribute;

import net.minecraft.world.phys.Vec3;

public interface EnvironmentAttributeLayer<Value> {

    interface Constant<Value> extends EnvironmentAttributeLayer<Value> {

        Value applyConstant(Value baseValue);
    }

    interface Positional<Value> extends EnvironmentAttributeLayer<Value> {

        Value applyPositional(Value baseValue, Vec3 pos, SpatialAttributeInterpolator biomeInterpolator);
    }

    interface TimeBased<Value> extends EnvironmentAttributeLayer<Value> {

        Value applyTimeBased(Value baseValue, int cacheTickId);
    }
}
