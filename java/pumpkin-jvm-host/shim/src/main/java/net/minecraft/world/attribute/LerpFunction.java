package net.minecraft.world.attribute;

public interface LerpFunction<T> {

    T apply(float alpha, T from, T to);
}
