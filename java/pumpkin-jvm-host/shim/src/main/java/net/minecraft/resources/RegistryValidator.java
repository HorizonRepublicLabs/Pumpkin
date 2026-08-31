package net.minecraft.resources;

import java.util.Map;
import net.minecraft.core.Registry;

public interface RegistryValidator<T> {

    void validate(Registry<T> registry, Map<ResourceKey<?>, Exception> loadingErrors);
}
