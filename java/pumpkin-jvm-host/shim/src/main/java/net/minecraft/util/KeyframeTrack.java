package net.minecraft.util;

import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public record KeyframeTrack<T>(List<Keyframe<T>> keyframes, EasingType easingType) {

    public static class Builder<T> {

        public KeyframeTrack<T> build() {
            throw Unimplemented.forMember("net/minecraft/util/KeyframeTrack$Builder.build:()Lnet/minecraft/util/KeyframeTrack;");
        }

        public Builder() {
        }
    }
}
