package net.minecraft.core.component;

import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import dev.pumpkin.shim.Unimplemented;

public final class PatchedDataComponentMap implements DataComponentMap {

    public PatchedDataComponentMap(DataComponentMap prototype) {
    }

    private PatchedDataComponentMap(DataComponentMap prototype, Reference2ObjectMap<DataComponentType<?>, Optional<?>> patch, boolean copyOnWrite) {
    }

    public static PatchedDataComponentMap fromPatch(DataComponentMap prototype, DataComponentPatch patch) {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.fromPatch:(Lnet/minecraft/core/component/DataComponentMap;Lnet/minecraft/core/component/DataComponentPatch;)Lnet/minecraft/core/component/PatchedDataComponentMap;");
    }

    public <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    public <T> T set(DataComponentType<T> type, T value) {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.set:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public <T> T set(TypedDataComponent<T> value) {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.set:(Lnet/minecraft/core/component/TypedDataComponent;)Ljava/lang/Object;");
    }

    public <T> T remove(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.remove:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    public Set<DataComponentType<?>> keySet() {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.keySet:()Ljava/util/Set;");
    }

    public Iterator<TypedDataComponent<?>> iterator() {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.iterator:()Ljava/util/Iterator;");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.size:()I");
    }

    public PatchedDataComponentMap copy() {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.copy:()Lnet/minecraft/core/component/PatchedDataComponentMap;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/core/component/PatchedDataComponentMap.toString:()Ljava/lang/String;");
    }

    public PatchedDataComponentMap() {
    }
}
