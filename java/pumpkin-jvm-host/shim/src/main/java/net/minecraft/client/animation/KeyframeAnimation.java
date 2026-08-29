package net.minecraft.client.animation;

import java.util.List;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;
import org.joml.Vector3f;
import dev.pumpkin.shim.Unimplemented;

public class KeyframeAnimation {

    private KeyframeAnimation(AnimationDefinition definition, List<KeyframeAnimation.Entry> entries) {
        throw Unimplemented.forMember("net/minecraft/client/animation/KeyframeAnimation.<init>:(Lnet/minecraft/client/animation/AnimationDefinition;Ljava/util/List;)V");
    }

    static KeyframeAnimation bake(ModelPart root, AnimationDefinition definition) {
        throw Unimplemented.forMember("net/minecraft/client/animation/KeyframeAnimation.bake:(Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/animation/AnimationDefinition;)Lnet/minecraft/client/animation/KeyframeAnimation;");
    }

    public void apply(AnimationState animationState, float currentTime) {
        throw Unimplemented.forMember("net/minecraft/client/animation/KeyframeAnimation.apply:(Lnet/minecraft/world/entity/AnimationState;F)V");
    }

    public void apply(AnimationState animationState, float currentTime, float speedFactor) {
        throw Unimplemented.forMember("net/minecraft/client/animation/KeyframeAnimation.apply:(Lnet/minecraft/world/entity/AnimationState;FF)V");
    }

    public void apply(long millisSinceStart, float targetScale) {
        throw Unimplemented.forMember("net/minecraft/client/animation/KeyframeAnimation.apply:(JF)V");
    }

    private record Entry(ModelPart part, AnimationChannel.Target target, Keyframe[] keyframes) {

        public void apply(float secondsSinceStart, float targetScale, Vector3f scratchVector) {
            throw Unimplemented.forMember("net/minecraft/client/animation/KeyframeAnimation$Entry.apply:(FFLorg/joml/Vector3f;)V");
        }
    }

    public KeyframeAnimation() {
    }
}
