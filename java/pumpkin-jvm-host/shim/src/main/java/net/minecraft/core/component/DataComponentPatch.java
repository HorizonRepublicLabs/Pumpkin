package net.minecraft.core.component;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public final class DataComponentPatch {

    public static final DataComponentPatch EMPTY = null;

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    public static final Codec<DataComponentPatch> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/component/DataComponentPatch.CODEC");

    DataComponentPatch(Reference2ObjectMap<DataComponentType<?>, Optional<?>> map) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.<init>:(Lit/unimi/dsi/fastutil/objects/Reference2ObjectMap;)V");
    }

    public static DataComponentPatch.Builder builder() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.builder:()Lnet/minecraft/core/component/DataComponentPatch$Builder;");
    }

    public <T> T get(DataComponentGetter prototype, DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.get:(Lnet/minecraft/core/component/DataComponentGetter;Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    public Set<Entry<DataComponentType<?>, Optional<?>>> entrySet() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.entrySet:()Ljava/util/Set;");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.size:()I");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.isEmpty:()Z");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.toString:()Ljava/lang/String;");
    }

    public static class Builder {

        protected Builder() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.<init>:()V");
        }

        public <T> DataComponentPatch.Builder set(DataComponentType<T> type, T value) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.set:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/core/component/DataComponentPatch$Builder;");
        }

        public <T> DataComponentPatch.Builder remove(DataComponentType<T> type) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.remove:(Lnet/minecraft/core/component/DataComponentType;)Lnet/minecraft/core/component/DataComponentPatch$Builder;");
        }

        public <T> DataComponentPatch.Builder set(TypedDataComponent<T> component) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.set:(Lnet/minecraft/core/component/TypedDataComponent;)Lnet/minecraft/core/component/DataComponentPatch$Builder;");
        }

        public <T> DataComponentPatch.Builder set(Iterable<TypedDataComponent<?>> components) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.set:(Ljava/lang/Iterable;)Lnet/minecraft/core/component/DataComponentPatch$Builder;");
        }

        public DataComponentPatch build() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.build:()Lnet/minecraft/core/component/DataComponentPatch;");
        }
    }

    private interface CodecGetter {

        <T> StreamCodec<? super RegistryFriendlyByteBuf, T> apply(DataComponentType<T> type);
    }

    private record PatchKey(DataComponentType<?> type, boolean removed) {

        public Codec<?> valueCodec() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$PatchKey.valueCodec:()Lcom/mojang/serialization/Codec;");
        }
    }

    public record SplitResult(DataComponentMap added, Set<DataComponentType<?>> removed) {
    }

    public DataComponentPatch() {
    }
}
