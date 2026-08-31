package net.minecraft.core.component;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;
import net.neoforged.neoforge.common.extensions.IDataComponentMapBuilderExtensions;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public interface DataComponentMap extends Iterable<TypedDataComponent<?>>, DataComponentGetter {

    DataComponentMap EMPTY = Stubs.of(DataComponentMap.class, "net/minecraft/core/component/DataComponentMap");

    // Pumpkin divergence: a throwing codec, not null -- DFU composes through it
    // at class-init; it throws by name on first real use.
    Codec<DataComponentMap> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/component/DataComponentMap.CODEC");

    // Pumpkin divergence: real body. A component map is a real map -- small surface,
    // genuine behaviour, nothing to stub.
    static DataComponentMap.Builder builder() {
        return new Builder();
    }

    Set<DataComponentType<?>> keySet();

    default boolean has(DataComponentType<?> type) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap.has:(Lnet/minecraft/core/component/DataComponentType;)Z");
    }

    default Iterator<TypedDataComponent<?>> iterator() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap.iterator:()Ljava/util/Iterator;");
    }

    default Stream<TypedDataComponent<?>> stream() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap.stream:()Ljava/util/stream/Stream;");
    }

    default int size() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap.size:()I");
    }

    default boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap.isEmpty:()Z");
    }

    class Builder implements IDataComponentMapBuilderExtensions {

        protected Builder() {
        }

        // Pumpkin divergence: real bodies over a plain LinkedHashMap.
        final java.util.Map<DataComponentType<?>, Object> pumpkinMap = new java.util.LinkedHashMap<>();

        @SuppressWarnings("unchecked")
        public <T> T get(DataComponentType<? extends T> type) {
            return (T) pumpkinMap.get(type);
        }

        public <T> DataComponentMap.Builder set(DataComponentType<T> type, T value) {
            pumpkinMap.put(type, value);
            return this;
        }

        // Pumpkin divergence: real body -- copy the other map's entries in.
        public DataComponentMap.Builder addAll(DataComponentMap map) {
            for (DataComponentType<?> type : map.keySet()) {
                pumpkinMap.put(type, map.get(type));
            }
            return this;
        }

        public DataComponentMap build() {
            final java.util.Map<DataComponentType<?>, Object> built =
                    java.util.Collections.unmodifiableMap(new java.util.LinkedHashMap<>(pumpkinMap));
            return new DataComponentMap() {
                @Override
                @SuppressWarnings("unchecked")
                public <T> T get(DataComponentType<? extends T> type) {
                    return (T) built.get(type);
                }

                @Override
                public boolean has(DataComponentType<?> type) {
                    return built.containsKey(type);
                }

                @Override
                public Set<DataComponentType<?>> keySet() {
                    return built.keySet();
                }

                @Override
                public boolean isEmpty() {
                    return built.isEmpty();
                }

                @Override
                public int size() {
                    return built.size();
                }
            };
        }

        private record SimpleMap(Reference2ObjectMap<DataComponentType<?>, Object> map) implements DataComponentMap {

            public <T> T get(DataComponentType<? extends T> type) {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder$SimpleMap.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
            }

            public boolean has(DataComponentType<?> type) {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder$SimpleMap.has:(Lnet/minecraft/core/component/DataComponentType;)Z");
            }

            public Set<DataComponentType<?>> keySet() {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder$SimpleMap.keySet:()Ljava/util/Set;");
            }

            public Iterator<TypedDataComponent<?>> iterator() {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder$SimpleMap.iterator:()Ljava/util/Iterator;");
            }

            public int size() {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder$SimpleMap.size:()I");
            }

            public String toString() {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder$SimpleMap.toString:()Ljava/lang/String;");
            }
        }
    }
}
