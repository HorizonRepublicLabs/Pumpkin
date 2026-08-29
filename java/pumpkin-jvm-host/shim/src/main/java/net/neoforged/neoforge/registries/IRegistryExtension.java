package net.neoforged.neoforge.registries;

import java.util.Map;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.callback.RegistryCallback;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public interface IRegistryExtension<T> {

    boolean doesSync();

    int getMaxId();

    void addCallback(RegistryCallback<T> callback);

    void addAlias(Identifier from, Identifier to);

    Identifier resolve(Identifier name);

    ResourceKey<T> resolve(ResourceKey<T> key);

    int getId(ResourceKey<T> key);

    int getId(Identifier name);

    boolean containsValue(T value);

    <A> Map<ResourceKey<T>, A> getDataMap(DataMapType<T, A> type);
}
