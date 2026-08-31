package net.neoforged.neoforge.registries.callback;

import net.minecraft.core.Registry;

public interface BakeCallback<T> extends RegistryCallback<T> {

    void onBake(Registry<T> registry);
}
