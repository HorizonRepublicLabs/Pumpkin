package net.minecraft.resources;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Registry;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public class ResourceKey<T> implements Comparable<ResourceKey<?>> {

    // Pumpkin divergence from the generated shim: this field and the members below marked
    // the same way carry real behaviour. A ResourceKey is a pair of names, and Registries
    // is nothing but a list of them; a throwing stub here means no mod can name the
    // registry it wants to add to. Re-apply by hand after any regeneration -- grep for
    // "Pumpkin divergence".
    private final Identifier pumpkinRegistryName;

    private final Identifier identifier;

    // Pumpkin divergence: inert codec -- composes at class-init, throws by name on use.
    public static <T> Codec<ResourceKey<T>> codec(ResourceKey<? extends Registry<T>> registryKey) {
        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/resources/ResourceKey.codec:(Lnet/minecraft/resources/ResourceKey;)Lcom/mojang/serialization/Codec;");
    }

    // Pumpkin divergence: inert stream codec -- same contract as codec() above.
    @SuppressWarnings("unchecked")
    public static <T> StreamCodec<ByteBuf, ResourceKey<T>> streamCodec(ResourceKey<? extends Registry<T>> registryName) {
        return dev.pumpkin.shim.Stubs.of(net.minecraft.network.codec.StreamCodec.class,
                "net/minecraft/resources/ResourceKey.streamCodec:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    // Pumpkin divergence: real body.
    public static <T> ResourceKey<T> create(ResourceKey<? extends Registry<T>> registryName, Identifier location) {
        return new ResourceKey<>(registryName.identifier(), location);
    }

    // Pumpkin divergence: real body. Vanilla interns these; the shim does not, and the
    // equals/hashCode contract below makes interning unobservable.
    private static <T> ResourceKey<T> create(Identifier registryName, Identifier identifier) {
        return new ResourceKey<>(registryName, identifier);
    }

    // Pumpkin divergence: no mod calls this, so the pruner drops it, but Registries needs
    // it to name the root registry. Vanilla's own createRegistryKey does exactly this.
    public static <T> ResourceKey<Registry<T>> createRegistryKey(Identifier identifier) {
        return new ResourceKey<>(Identifier.fromNamespaceAndPath("minecraft", "root"), identifier);
    }

    // Pumpkin divergence: real body.
    private ResourceKey(Identifier registryName, Identifier identifier) {
        this.pumpkinRegistryName = registryName;
        this.identifier = identifier;
    }

    // Pumpkin divergence: real body.
    public String toString() {
        return "ResourceKey[" + pumpkinRegistryName + " / " + identifier + "]";
    }

    // Pumpkin divergence: real body.
    public Identifier identifier() {
        return identifier;
    }

    // Pumpkin divergence: no vanilla counterpart. The registry half, for callers that
    // need to rebuild a registry key from a value key -- DeferredHolder.create(key) does.
    public Identifier pumpkinRegistry() {
        return pumpkinRegistryName;
    }

    // Pumpkin divergence: vanilla body -- the registry half as a registry key.
    public ResourceKey<Registry<T>> registryKey() {
        return createRegistryKey(pumpkinRegistryName);
    }

    // Pumpkin divergence: real bodies. The comment above promises this contract, and
    // without it two keys naming the same entry compared unequal -- vanilla interns its
    // keys, so identity is enough there and nothing calls these directly, which is why
    // the generator had pruned them.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof ResourceKey<?> other
                && pumpkinRegistryName.equals(other.pumpkinRegistryName)
                && identifier.equals(other.identifier);
    }

    @Override
    public int hashCode() {
        return 31 * pumpkinRegistryName.hashCode() + identifier.hashCode();
    }

    // Pumpkin divergence: real body.
    public int compareTo(ResourceKey<?> o) {
        int byRegistry = pumpkinRegistryName.compareTo(o.pumpkinRegistryName);
        return byRegistry != 0 ? byRegistry : identifier.compareTo(o.identifier);
    }

    private record InternKey(Identifier registry, Identifier identifier) {
    }

    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class; this one has two final fields to assign, so it delegates.
    public ResourceKey() {
        this(Identifier.fromNamespaceAndPath("minecraft", "root"), Identifier.fromNamespaceAndPath("minecraft", ""));
    }
}
