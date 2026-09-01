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

    // Pumpkin divergence: bake callbacks are how mods finish registry-dependent
    // statics (Mekanism wires the empty chemical's holder in one); they run after
    // the registry's registrations flush, from pumpkinFireBakes below.
    private final java.util.List<BakeCallback<T>> pumpkinBakes = new java.util.ArrayList<>();

    public RegistryBuilder<T> onBake(BakeCallback<T> callback) {
        pumpkinBakes.add(callback);
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

    // Pumpkin divergence: a stub registry that knows which registry it is -- and can
    // answer getOrThrow from what DeferredRegister recorded -- while every other
    // method keeps the stub contract: real default bodies run, the rest throw by
    // name. Entries a mod registers into it flow through DeferredRegister, where
    // unknown registry kinds are acknowledged and counted on the Rust side.
    @SuppressWarnings("unchecked")
    public Registry<T> create() {
        PUMPKIN_CREATED.add(pumpkinRegistryKey);
        String registryName = pumpkinRegistryKey.identifier().toString();
        Registry<T> registry = dev.pumpkin.shim.Stubs.of(Registry.class,
                "net/minecraft/core/Registry (created registry " + registryName + ")",
                java.util.Map.of(
                        "key", pumpkinRegistryKey,
                        "getOrThrow", (dev.pumpkin.shim.Stubs.Dynamic) args -> {
                            ResourceKey<T> entry = (ResourceKey<T>) args[0];
                            Object value = DeferredHolder.pumpkinResolve(
                                    registryName, entry.identifier().toString());
                            if (value == null) {
                                throw new IllegalStateException("Missing key in " + registryName
                                        + ": " + entry.identifier());
                            }
                            return net.minecraft.core.Holder.Reference.pumpkinOf(entry, (T) value);
                        }));
        PUMPKIN_PENDING_BAKES.add(() -> {
            for (BakeCallback<T> callback : pumpkinBakes) {
                callback.onBake(registry);
            }
        });
        return registry;
    }

    // Pumpkin divergence: no vanilla counterpart. Bakes fire once the loader has
    // flushed the created registries' registrations -- NeoForge's freeze point.
    private static final java.util.List<Runnable> PUMPKIN_PENDING_BAKES =
            new java.util.concurrent.CopyOnWriteArrayList<>();

    public static void pumpkinFireBakes() {
        for (Runnable bake : PUMPKIN_PENDING_BAKES) {
            bake.run();
        }
        PUMPKIN_PENDING_BAKES.clear();
    }

    public RegistryBuilder() {
    }
}
