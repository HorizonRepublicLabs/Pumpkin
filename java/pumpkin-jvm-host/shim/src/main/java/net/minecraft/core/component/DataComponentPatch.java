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

    public static final DataComponentPatch EMPTY = new DataComponentPatch(null);

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    public static final Codec<DataComponentPatch> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/component/DataComponentPatch.CODEC");

    // Pumpkin divergence: a patch really is a map -- Optional.of(value) sets,
    // Optional.empty() removes.
    public final java.util.LinkedHashMap<DataComponentType<?>, Optional<?>> pumpkinMap = new java.util.LinkedHashMap<>();

    DataComponentPatch(Reference2ObjectMap<DataComponentType<?>, Optional<?>> map) {
    }

    public static DataComponentPatch.Builder builder() {
        return new Builder();
    }

    public <T> T get(DataComponentGetter prototype, DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.get:(Lnet/minecraft/core/component/DataComponentGetter;Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    public Set<Entry<DataComponentType<?>, Optional<?>>> entrySet() {
        return pumpkinMap.entrySet();
    }

    public int size() {
        return pumpkinMap.size();
    }

    public boolean isEmpty() {
        return pumpkinMap.isEmpty();
    }

    public boolean equals(Object obj) {
        return obj instanceof DataComponentPatch other && pumpkinMap.equals(other.pumpkinMap);
    }

    public int hashCode() {
        return pumpkinMap.hashCode();
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.toString:()Ljava/lang/String;");
    }

    public static class Builder {

        protected Builder() {
        }

        final DataComponentPatch pumpkinPatch = new DataComponentPatch(null);

        public <T> DataComponentPatch.Builder set(DataComponentType<T> type, T value) {
            pumpkinPatch.pumpkinMap.put(type, Optional.of(value));
            return this;
        }

        public <T> DataComponentPatch.Builder remove(DataComponentType<T> type) {
            pumpkinPatch.pumpkinMap.put(type, Optional.empty());
            return this;
        }

        public <T> DataComponentPatch.Builder set(TypedDataComponent<T> component) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.set:(Lnet/minecraft/core/component/TypedDataComponent;)Lnet/minecraft/core/component/DataComponentPatch$Builder;");
        }

        public <T> DataComponentPatch.Builder set(Iterable<TypedDataComponent<?>> components) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.set:(Ljava/lang/Iterable;)Lnet/minecraft/core/component/DataComponentPatch$Builder;");
        }

        public DataComponentPatch build() {
            return pumpkinPatch;
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
