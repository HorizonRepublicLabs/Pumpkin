package net.neoforged.neoforge.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.callback.BakeCallback;
import dev.pumpkin.shim.Unimplemented;

public class RegistryBuilder<T> {

    private Identifier defaultKey;

    private boolean sync;

    // Pumpkin divergence: kept for create() -- the registry's one honest fact.
    private ResourceKey<? extends Registry<T>> pumpkinRegistryKey;

    public RegistryBuilder(ResourceKey<? extends Registry<T>> registryKey) {
        this.pumpkinRegistryKey = registryKey;
    }

    // Pumpkin divergence: builder metadata, accepted and dropped; chain returns this.
    public RegistryBuilder<T> defaultKey(Identifier key) {
        return this;
    }

    // Pumpkin divergence: builder metadata, accepted and dropped; chain returns this.
    public RegistryBuilder<T> defaultKey(ResourceKey<T> key) {
        return this;
    }

    // Pumpkin divergence: builder metadata, accepted and dropped; chain returns this.
    public RegistryBuilder<T> onBake(BakeCallback<T> callback) {
        return this;
    }

    // Pumpkin divergence: builder metadata, accepted and dropped; chain returns this.
    public RegistryBuilder<T> sync(boolean sync) {
        return this;
    }

    // Pumpkin divergence: no vanilla counterpart. Every custom registry a mod creates,
    // so the loader can fire that registry's RegisterEvent -- without it the mod's
    // registrations into its own registry would simply never flush.
    private static final java.util.List<ResourceKey<? extends Registry<?>>> PUMPKIN_CREATED =
            new java.util.concurrent.CopyOnWriteArrayList<>();

    public static java.util.List<ResourceKey<? extends Registry<?>>> pumpkinCreatedKeys() {
        return PUMPKIN_CREATED;
    }

    // Pumpkin divergence: a stub registry that knows which registry it is -- the one
    // question registration helpers ask -- and throws by name for everything else.
    // Entries a mod registers into it flow through DeferredRegister, where unknown
    // registry kinds are acknowledged and counted on the Rust side.
    @SuppressWarnings("unchecked")
    public Registry<T> create() {
        PUMPKIN_CREATED.add(pumpkinRegistryKey);
        return dev.pumpkin.shim.Stubs.of(Registry.class, "net/minecraft/core/Registry",
                java.util.Map.of("key", pumpkinRegistryKey));
    }

    public RegistryBuilder() {
    }
}
