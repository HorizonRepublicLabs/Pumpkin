package net.minecraft.data;

import java.util.concurrent.CompletableFuture;

public interface DataProvider {

    CompletableFuture<?> run(CachedOutput cache);

    String getName();

    interface Factory<T extends DataProvider> {

        T create(PackOutput output);
    }
}
