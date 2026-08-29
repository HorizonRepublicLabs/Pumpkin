package net.minecraft.util;

public interface BoundedFloatFunction<C> {

    float apply(final C c);

    float minValue();

    float maxValue();
}
