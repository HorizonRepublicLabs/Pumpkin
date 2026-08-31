package net.minecraft.client.renderer.feature;

import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;
import net.minecraft.client.renderer.StagedVertexBuffer;
import net.minecraft.client.renderer.feature.submit.SubmitNode;
import net.minecraft.client.renderer.rendertype.RenderType;
import dev.pumpkin.shim.Unimplemented;

public abstract class RenderTypeFeatureRenderer<Submit extends SubmitNode> implements FeatureRenderer<Submit> {

    protected abstract void buildGroup(FeatureFrameContext context, List<Submit> submits);

    protected final VertexConsumer getVertexBuilder(RenderType renderType) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/RenderTypeFeatureRenderer.getVertexBuilder:(Lnet/minecraft/client/renderer/rendertype/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;");
    }

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
        }

        protected Group() {
        }
    }

    public RenderTypeFeatureRenderer() {
    }
}
