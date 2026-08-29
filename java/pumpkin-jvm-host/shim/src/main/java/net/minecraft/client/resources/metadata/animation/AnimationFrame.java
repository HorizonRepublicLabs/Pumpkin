package net.minecraft.client.resources.metadata.animation;

import java.util.Optional;

public record AnimationFrame(int index, Optional<Integer> time) {

    public AnimationFrame(int index) {
        this((int) 0, (Optional<Integer>) null);
    }
}
