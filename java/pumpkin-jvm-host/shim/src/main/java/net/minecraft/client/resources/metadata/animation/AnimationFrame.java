package net.minecraft.client.resources.metadata.animation;

import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public record AnimationFrame(int index, Optional<Integer> time) {

    public AnimationFrame(int index) {
        this((int) 0, (Optional<Integer>) null);
        throw Unimplemented.forMember("net/minecraft/client/resources/metadata/animation/AnimationFrame.<init>:(I)V");
    }
}
