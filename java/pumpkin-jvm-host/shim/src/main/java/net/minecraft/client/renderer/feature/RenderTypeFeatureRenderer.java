package net.minecraft.client.renderer.feature;

import java.util.List;
import net.minecraft.client.renderer.StagedVertexBuffer;
import net.minecraft.client.renderer.feature.submit.SubmitNode;
import dev.pumpkin.shim.Unimplemented;

public abstract class RenderTypeFeatureRenderer<Submit extends SubmitNode> implements FeatureRenderer<Submit> {

    protected abstract void buildGroup(FeatureFrameContext context, List<Submit> submits);

    public final void prepareGroup(FeatureFrameContext context, List<Submit> submits, boolean strictlyOrdered) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/RenderTypeFeatureRenderer.prepareGroup:(Lnet/minecraft/client/renderer/feature/FeatureFrameContext;Ljava/util/List;Z)V");
    }

    public void executeGroup(FeatureFrameContext context, int groupIndex, List<Submit> submits, boolean strictlyOrdered) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/RenderTypeFeatureRenderer.executeGroup:(Lnet/minecraft/client/renderer/feature/FeatureFrameContext;ILjava/util/List;Z)V");
    }

    public void finishExecute(FeatureFrameContext context) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/RenderTypeFeatureRenderer.finishExecute:(Lnet/minecraft/client/renderer/feature/FeatureFrameContext;)V");
    }

    private static class Group {

        private Group(StagedVertexBuffer stagedBuffer, boolean canReorder) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/feature/RenderTypeFeatureRenderer$Group.<init>:(Lnet/minecraft/client/renderer/StagedVertexBuffer;Z)V");
        }

        protected Group() {
        }
    }

    public RenderTypeFeatureRenderer() {
    }
}
