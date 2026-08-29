package net.minecraft.core;

public interface IdMap<T> extends Iterable<T> {

    int getId(T thing);

    T byId(int id);

    int size();
}
