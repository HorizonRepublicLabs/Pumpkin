package net.minecraft.client.animation;

import java.util.List;
import java.util.Map;
import net.minecraft.client.model.geom.ModelPart;
import dev.pumpkin.shim.Unimplemented;

public record AnimationDefinition(float lengthInSeconds, boolean looping, Map<String, List<AnimationChannel>> boneAnimations) {

    public KeyframeAnimation bake(ModelPart root) {
        throw Unimplemented.forMember("net/minecraft/client/animation/AnimationDefinition.bake:(Lnet/minecraft/client/model/geom/ModelPart;)Lnet/minecraft/client/animation/KeyframeAnimation;");
    }

    public static class Builder {

        private Builder(float length) {
        }

        public AnimationDefinition build() {
            throw Unimplemented.forMember("net/minecraft/client/animation/AnimationDefinition$Builder.build:()Lnet/minecraft/client/animation/AnimationDefinition;");
        }

        public Builder() {
        }
    }
}
