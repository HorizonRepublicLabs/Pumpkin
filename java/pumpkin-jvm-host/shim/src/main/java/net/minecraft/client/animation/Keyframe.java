package net.minecraft.client.animation;

import org.joml.Vector3fc;

public record Keyframe(float timestamp, Vector3fc preTarget, Vector3fc postTarget, AnimationChannel.Interpolation interpolation) {

    public Keyframe(float timestamp, Vector3fc postTarget, AnimationChannel.Interpolation interpolation) {
        this((float) 0.0F, (Vector3fc) null, (Vector3fc) null, (AnimationChannel.Interpolation) null);
    }
}
