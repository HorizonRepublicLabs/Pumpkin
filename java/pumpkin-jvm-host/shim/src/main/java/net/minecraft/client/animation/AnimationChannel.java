package net.minecraft.client.animation;

import net.minecraft.client.model.geom.ModelPart;
import org.joml.Vector3f;
import dev.pumpkin.shim.Unimplemented;

public record AnimationChannel(AnimationChannel.Target target, Keyframe... keyframes) {

    public interface Interpolation {

        Vector3f apply(final Vector3f vector, final float alpha, final Keyframe[] keyframes, final int prev, final int next, final float targetScale);
    }

    public static class Interpolations {

        static {
            if (true) {
                throw Unimplemented.forMember("net/minecraft/client/animation/AnimationChannel$Interpolations");
            }
        }
    }

    public interface Target {

        void apply(final ModelPart animationBone, final Vector3f target);
    }

    public static class Targets {

        static {
            if (true) {
                throw Unimplemented.forMember("net/minecraft/client/animation/AnimationChannel$Targets");
            }
        }
    }
}
