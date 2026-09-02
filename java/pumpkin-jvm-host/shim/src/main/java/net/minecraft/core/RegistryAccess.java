package net.minecraft.core;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public interface RegistryAccess extends HolderLookup.Provider {

    RegistryAccess.Frozen EMPTY = null;

    <E> Optional<Registry<E>> lookup(final ResourceKey<? extends Registry<? extends E>> registryKey);

    // Pumpkin divergence: real body -- the same registry the HolderLookup door answers
    // with, typed as a Registry. A machine's setLevel asks through this one; while it
    // threw, every machine fell back to a half-built level and reported no supported
    // upgrades.
    @SuppressWarnings({"unchecked", "rawtypes"})
    default <E> Registry<E> lookupOrThrow(ResourceKey<? extends Registry<? extends E>> name) {
        return dev.pumpkin.shim.Stubs.of(Registry.class,
                "net/minecraft/core/Registry(" + name.identifier() + ") via RegistryAccess.lookupOrThrow",
                dev.pumpkin.bridge.PumpkinRegistryLookup.answersFor(name));
    }

    Stream<RegistryAccess.RegistryEntry<?>> registries();

    default Stream<ResourceKey<? extends Registry<?>>> listRegistryKeys() {
        throw Unimplemented.forMember("net/minecraft/core/RegistryAccess.listRegistryKeys:()Ljava/util/stream/Stream;");
    }

    default RegistryAccess.Frozen freeze() {
        throw Unimplemented.forMember("net/minecraft/core/RegistryAccess.freeze:()Lnet/minecraft/core/RegistryAccess$Frozen;");
    }

    interface Frozen extends RegistryAccess {
    }

    class ImmutableRegistryAccess implements RegistryAccess {

        public ImmutableRegistryAccess(List<? extends Registry<?>> registries) {
        }

        public ImmutableRegistryAccess(Map<? extends ResourceKey<? extends Registry<?>>, ? extends Registry<?>> registries) {
        }

        public ImmutableRegistryAccess(Stream<RegistryAccess.RegistryEntry<?>> entries) {
        }

        public <E> Optional<Registry<E>> lookup(ResourceKey<? extends Registry<? extends E>> registryKey) {
            throw Unimplemented.forMember("net/minecraft/core/RegistryAccess$ImmutableRegistryAccess.lookup:(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;");
        }

        public Stream<RegistryAccess.RegistryEntry<?>> registries() {
            throw Unimplemented.forMember("net/minecraft/core/RegistryAccess$ImmutableRegistryAccess.registries:()Ljava/util/stream/Stream;");
        }

        protected ImmutableRegistryAccess() {
        }
    }

    record RegistryEntry<T>(ResourceKey<? extends Registry<T>> key, Registry<T> value) {

        private RegistryAccess.RegistryEntry<T> freeze() {
            throw Unimplemented.forMember("net/minecraft/core/RegistryAccess$RegistryEntry.freeze:()Lnet/minecraft/core/RegistryAccess$RegistryEntry;");
        }
    }
}
