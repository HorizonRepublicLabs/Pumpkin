package net.minecraft.client.renderer.feature.phase;

import net.minecraft.client.renderer.feature.submit.SubmitNode;

public interface FeatureRenderPhase<Submit extends SubmitNode> {

    void submit(Submit submit);

    void sortInto(FeatureRenderPhase.Output output);

    boolean isEmpty();

    interface Output {

        void accept(SubmitNode submit, boolean strictlyOrdered);
    }
}
