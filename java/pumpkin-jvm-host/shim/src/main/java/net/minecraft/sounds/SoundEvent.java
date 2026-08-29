package net.minecraft.sounds;

import java.util.Optional;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record SoundEvent(Identifier location, Optional<Float> fixedRange) {

    private static SoundEvent create(Identifier location, Optional<Float> range) {
        throw Unimplemented.forMember("net/minecraft/sounds/SoundEvent.create:(Lnet/minecraft/resources/Identifier;Ljava/util/Optional;)Lnet/minecraft/sounds/SoundEvent;");
    }

    public static SoundEvent createVariableRangeEvent(Identifier location) {
        throw Unimplemented.forMember("net/minecraft/sounds/SoundEvent.createVariableRangeEvent:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/sounds/SoundEvent;");
    }
}
