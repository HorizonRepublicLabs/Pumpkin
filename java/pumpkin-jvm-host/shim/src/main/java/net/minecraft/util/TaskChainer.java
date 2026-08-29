package net.minecraft.util;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface TaskChainer {

    <T> void append(CompletableFuture<T> preparation, Consumer<T> chainedTask);
}
