package net.minecraft.sounds;

import java.util.Optional;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record SoundEvent(Identifier location, Optional<Float> fixedRange) {

    private static SoundEvent create(Identifier location, Optional<Float> range) {
        throw Unimplemented.forMember("net/minecraft/sounds/SoundEvent.create:(Lnet/minecraft/resources/Identifier;Ljava/util/Optional;)Lnet/minecraft/sounds/SoundEvent;");
    }

    // Pumpkin divergence: real body, copied from vanilla. SoundEvent is a record the shim
    // keeps whole, and the factory is one self-contained line -- the ARGB rule again.
    public static SoundEvent createVariableRangeEvent(Identifier location) {
        return new SoundEvent(location, Optional.empty());
    }
}
