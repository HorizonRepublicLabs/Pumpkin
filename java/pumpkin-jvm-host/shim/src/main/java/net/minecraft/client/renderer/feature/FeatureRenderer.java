package net.minecraft.client.renderer.feature;

import java.util.List;
import net.minecraft.client.renderer.feature.submit.SubmitNode;
import dev.pumpkin.shim.Unimplemented;

public interface FeatureRenderer<Submit extends SubmitNode> extends AutoCloseable {

    void prepareGroup(FeatureFrameContext context, List<Submit> submits, boolean strictlyOrdered);

    void executeGroup(FeatureFrameContext context, int groupIndex, List<Submit> submits, boolean strictlyOrdered);

    default void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/FeatureRenderer.close:()V");
    }
}
