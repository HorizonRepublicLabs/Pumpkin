package net.minecraft.core;

import net.minecraft.resources.Identifier;

public interface DefaultedRegistry<T> extends Registry<T> {

    Identifier getKey(T thing);

    T getValue(Identifier key);

    T byId(int id);

    Identifier getDefaultKey();
}
