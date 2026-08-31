package net.minecraft.world.entity.ai.memory;

import com.mojang.serialization.Codec;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public class MemoryModuleType<U> {

    public MemoryModuleType(Optional<Codec<U>> codec) {
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/MemoryModuleType.toString:()Ljava/lang/String;");
    }

    private static <U> MemoryModuleType<U> register(String name, Codec<U> codec) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/MemoryModuleType.register:(Ljava/lang/String;Lcom/mojang/serialization/Codec;)Lnet/minecraft/world/entity/ai/memory/MemoryModuleType;");
    }

    private static <U> MemoryModuleType<U> register(String name) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/MemoryModuleType.register:(Ljava/lang/String;)Lnet/minecraft/world/entity/ai/memory/MemoryModuleType;");
    }

    public MemoryModuleType() {
    }
}
