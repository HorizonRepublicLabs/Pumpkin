package net.minecraft.world.entity.ai.memory;

import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public record ExpirableValue<T>(T value, Optional<Long> timeToLive) {

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/memory/ExpirableValue.toString:()Ljava/lang/String;");
    }
}
