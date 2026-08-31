package net.minecraft.world.entity.ai.memory;

import java.util.Iterator;
import java.util.Map;
import dev.pumpkin.shim.Unimplemented;

public final class MemoryMap implements Iterable<MemoryMap.Value<?>> {

    private MemoryMap(Map<MemoryModuleType<?>, ExpirableValue<?>> memories) {
    }

    public <U> ExpirableValue<U> get(MemoryModuleType<U> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/MemoryMap.get:(Lnet/minecraft/world/entity/ai/memory/MemoryModuleType;)Lnet/minecraft/world/entity/ai/memory/ExpirableValue;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/MemoryMap.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/MemoryMap.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/MemoryMap.toString:()Ljava/lang/String;");
    }

    public Iterator<MemoryMap.Value<?>> iterator() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/MemoryMap.iterator:()Ljava/util/Iterator;");
    }

    public static class Builder {

        public <U> MemoryMap.Builder add(MemoryModuleType<U> type, ExpirableValue<U> value) {
            throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/MemoryMap$Builder.add:(Lnet/minecraft/world/entity/ai/memory/MemoryModuleType;Lnet/minecraft/world/entity/ai/memory/ExpirableValue;)Lnet/minecraft/world/entity/ai/memory/MemoryMap$Builder;");
        }

        public MemoryMap build() {
            throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/MemoryMap$Builder.build:()Lnet/minecraft/world/entity/ai/memory/MemoryMap;");
        }

        public Builder() {
        }
    }

    public record Value<U>(MemoryModuleType<U> type, ExpirableValue<U> value) {
    }

    public MemoryMap() {
    }
}
