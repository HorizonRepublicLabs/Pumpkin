package net.minecraft.resources;

import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import java.util.Optional;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import dev.pumpkin.shim.Unimplemented;

public class RegistryOps<T> extends DelegatingOps<T> {

    public static <T> RegistryOps<T> create(DynamicOps<T> parent, HolderLookup.Provider lookupProvider) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryOps.create:(Lcom/mojang/serialization/DynamicOps;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/resources/RegistryOps;");
    }

    public static <T> RegistryOps<T> create(DynamicOps<T> parent, RegistryOps.RegistryInfoLookup lookupProvider) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryOps.create:(Lcom/mojang/serialization/DynamicOps;Lnet/minecraft/resources/RegistryOps$RegistryInfoLookup;)Lnet/minecraft/resources/RegistryOps;");
    }

    protected RegistryOps(DynamicOps<T> parent, RegistryOps.RegistryInfoLookup lookupProvider) {
    }

    protected RegistryOps(RegistryOps<T> other) {
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryOps.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/resources/RegistryOps.hashCode:()I");
    }

    public static final class HolderLookupAdapter implements RegistryOps.RegistryInfoLookup {

        public HolderLookupAdapter(HolderLookup.Provider lookupProvider) {
        }

        public <E> Optional<RegistryOps.RegistryInfo<E>> lookup(ResourceKey<? extends Registry<? extends E>> registryKey) {
            throw Unimplemented.forMember("net/minecraft/resources/RegistryOps$HolderLookupAdapter.lookup:(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;");
        }

        public boolean equals(Object obj) {
            throw Unimplemented.forMember("net/minecraft/resources/RegistryOps$HolderLookupAdapter.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/resources/RegistryOps$HolderLookupAdapter.hashCode:()I");
        }

        public HolderLookupAdapter() {
        }
    }

    public record RegistryInfo<T>(HolderOwner<T> owner, HolderGetter<T> getter, Lifecycle elementsLifecycle) {
    }

    public interface RegistryInfoLookup {

        <T> Optional<RegistryOps.RegistryInfo<T>> lookup(ResourceKey<? extends Registry<? extends T>> registryKey);
    }

    public RegistryOps() {
    }
}
