package net.minecraft.client.renderer.feature;

import net.minecraft.client.renderer.feature.submit.SubmitNode;
import dev.pumpkin.shim.Unimplemented;

public class FeatureRendererMap implements AutoCloseable {

    public <Submit extends SubmitNode> FeatureRenderer<Submit> get(FeatureRendererType<Submit> type) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/FeatureRendererMap.get:(Lnet/minecraft/client/renderer/feature/FeatureRendererType;)Lnet/minecraft/client/renderer/feature/FeatureRenderer;");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/FeatureRendererMap.close:()V");
    }

    public FeatureRendererMap() {
    }
}
