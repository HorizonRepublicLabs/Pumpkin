package net.minecraft.util;

public interface AbortableIterationConsumer<T> {

    AbortableIterationConsumer.Continuation accept(T entry);

    enum Continuation {

        CONTINUE, ABORT
    }
}
