package net.minecraft.client.animation;

import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public record Keyframe(float timestamp, Vector3fc preTarget, Vector3fc postTarget, AnimationChannel.Interpolation interpolation) {

    public Keyframe(float timestamp, Vector3fc postTarget, AnimationChannel.Interpolation interpolation) {
        this((float) 0.0F, (Vector3fc) null, (Vector3fc) null, (AnimationChannel.Interpolation) null);
        throw Unimplemented.forMember("net/minecraft/client/animation/Keyframe.<init>:(FLorg/joml/Vector3fc;Lnet/minecraft/client/animation/AnimationChannel$Interpolation;)V");
    }
}
