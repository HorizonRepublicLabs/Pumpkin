package net.minecraft.resources;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Lifecycle;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.core.RegistrationInfo;

public abstract class RegistryLoadTask<T> {

    protected RegistryLoadTask(RegistryDataLoader.RegistryData<T> data, Lifecycle lifecycle, Map<ResourceKey<?>, Exception> loadingErrors) {
    }

    public abstract CompletableFuture<?> load(RegistryOps.RegistryInfoLookup context, Executor executor);

    protected record PendingRegistration<T>(ResourceKey<T> key, Either<T, Exception> value, RegistrationInfo registrationInfo) {
    }

    public RegistryLoadTask() {
    }
}
