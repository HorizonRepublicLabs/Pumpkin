package net.neoforged.neoforge.registries;

import java.util.Map;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.callback.RegistryCallback;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import dev.pumpkin.shim.Unimplemented;

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

    default Identifier getKeyOrNull(T element) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/IRegistryExtension.getKeyOrNull:(Ljava/lang/Object;)Lnet/minecraft/resources/Identifier;");
    }
}
