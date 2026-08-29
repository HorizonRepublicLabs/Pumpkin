package net.minecraft.core.component;

import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;
import net.neoforged.neoforge.common.extensions.IDataComponentMapBuilderExtensions;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public interface DataComponentMap extends Iterable<TypedDataComponent<?>>, DataComponentGetter {

    DataComponentMap EMPTY = Stubs.of(DataComponentMap.class, "net/minecraft/core/component/DataComponentMap");

    static DataComponentMap.Builder builder() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap.builder:()Lnet/minecraft/core/component/DataComponentMap$Builder;");
    }

    Set<DataComponentType<?>> keySet();

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

        public <T> T get(DataComponentType<? extends T> type) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
        }

        public <T> DataComponentMap.Builder set(DataComponentType<T> type, T value) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder.set:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/core/component/DataComponentMap$Builder;");
        }

        public DataComponentMap build() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder.build:()Lnet/minecraft/core/component/DataComponentMap;");
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
