package net.neoforged.neoforge.client.submit;

import java.util.function.Function;
import net.minecraft.client.renderer.SubmitNodeCollection;
import net.minecraft.client.renderer.feature.phase.FeatureRenderPhase;
import net.minecraft.client.renderer.feature.submit.SubmitNode;
import dev.pumpkin.shim.Unimplemented;

public final class RenderPhaseKey<T extends SubmitNode> {

    public RenderPhaseKey(Function<SubmitNodeCollection, FeatureRenderPhase<T>> phaseGetter) {
    }

    public FeatureRenderPhase<T> resolve(SubmitNodeCollection collection) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/submit/RenderPhaseKey.resolve:(Lnet/minecraft/client/renderer/SubmitNodeCollection;)Lnet/minecraft/client/renderer/feature/phase/FeatureRenderPhase;");
    }

    public RenderPhaseKey() {
    }
}
