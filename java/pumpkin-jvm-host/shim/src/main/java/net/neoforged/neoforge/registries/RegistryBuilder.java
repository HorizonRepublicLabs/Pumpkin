package net.neoforged.neoforge.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.callback.BakeCallback;
import dev.pumpkin.shim.Unimplemented;

public class RegistryBuilder<T> {

    private Identifier defaultKey;

    private boolean sync;

    public RegistryBuilder(ResourceKey<? extends Registry<T>> registryKey) {
    }

    public RegistryBuilder<T> defaultKey(Identifier key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegistryBuilder.defaultKey:(Lnet/minecraft/resources/Identifier;)Lnet/neoforged/neoforge/registries/RegistryBuilder;");
    }

    public RegistryBuilder<T> defaultKey(ResourceKey<T> key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegistryBuilder.defaultKey:(Lnet/minecraft/resources/ResourceKey;)Lnet/neoforged/neoforge/registries/RegistryBuilder;");
    }

    public RegistryBuilder<T> onBake(BakeCallback<T> callback) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegistryBuilder.onBake:(Lnet/neoforged/neoforge/registries/callback/BakeCallback;)Lnet/neoforged/neoforge/registries/RegistryBuilder;");
    }

    public RegistryBuilder<T> sync(boolean sync) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegistryBuilder.sync:(Z)Lnet/neoforged/neoforge/registries/RegistryBuilder;");
    }

    public Registry<T> create() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegistryBuilder.create:()Lnet/minecraft/core/Registry;");
    }

    public RegistryBuilder() {
    }
}
