package net.minecraft.client.renderer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record PostChainConfig(Map<Identifier, PostChainConfig.InternalTarget> internalTargets, List<PostChainConfig.Pass> passes) {

    public interface Input {

        String samplerName();

        Set<Identifier> referencedTargets();
    }

    public record InternalTarget(Optional<Integer> width, Optional<Integer> height, boolean persistent, int clearColor) {
    }

    public record Pass(Identifier vertexShaderId, Identifier fragmentShaderId, List<PostChainConfig.Input> inputs, Identifier outputTarget, Map<String, List<UniformValue>> uniforms) {

        public Stream<Identifier> referencedTargets() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/PostChainConfig$Pass.referencedTargets:()Ljava/util/stream/Stream;");
        }
    }

    public record TargetInput(String samplerName, Identifier targetId, boolean useDepthBuffer, boolean bilinear) implements PostChainConfig.Input {

        public Set<Identifier> referencedTargets() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/PostChainConfig$TargetInput.referencedTargets:()Ljava/util/Set;");
        }
    }

    public record TextureInput(String samplerName, Identifier location, int width, int height, boolean bilinear) implements PostChainConfig.Input {

        public Set<Identifier> referencedTargets() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/PostChainConfig$TextureInput.referencedTargets:()Ljava/util/Set;");
        }
    }
}
