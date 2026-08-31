package net.neoforged.neoforge.client.event;

import net.minecraft.client.renderer.feature.FeatureRenderer;
import net.minecraft.client.renderer.feature.FeatureRendererMap;
import net.minecraft.client.renderer.feature.FeatureRendererType;
import net.minecraft.client.renderer.feature.submit.SubmitNode;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public final class RegisterFeatureRenderersEvent extends Event {

    public RegisterFeatureRenderersEvent(FeatureRendererMap featureRenderers) {
    }

    public <S extends SubmitNode> void register(FeatureRendererType<S> type, FeatureRenderer<S> renderer) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterFeatureRenderersEvent.register:(Lnet/minecraft/client/renderer/feature/FeatureRendererType;Lnet/minecraft/client/renderer/feature/FeatureRenderer;)V");
    }

    public RegisterFeatureRenderersEvent() {
    }
}
