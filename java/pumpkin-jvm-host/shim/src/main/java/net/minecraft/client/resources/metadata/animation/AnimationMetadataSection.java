package net.minecraft.client.resources.metadata.animation;

import java.util.List;
import java.util.Optional;

public record AnimationMetadataSection(Optional<List<AnimationFrame>> frames, Optional<Integer> frameWidth, Optional<Integer> frameHeight, int defaultFrameTime, boolean interpolatedFrames) {
}
