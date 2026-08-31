package net.neoforged.neoforge.client.event;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.client.IRenderableSection;
import dev.pumpkin.shim.Unimplemented;

public final class SubmitCustomGeometryEvent extends Event {

    public SubmitCustomGeometryEvent(LevelRenderState levelRenderState, SubmitNodeCollector submitNodeCollector, PoseStack poseStack, Iterable<? extends IRenderableSection> renderableSections) {
    }

    public LevelRenderState getLevelRenderState() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/SubmitCustomGeometryEvent.getLevelRenderState:()Lnet/minecraft/client/renderer/state/level/LevelRenderState;");
    }

    public SubmitNodeCollector getSubmitNodeCollector() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/SubmitCustomGeometryEvent.getSubmitNodeCollector:()Lnet/minecraft/client/renderer/SubmitNodeCollector;");
    }

    public PoseStack getPoseStack() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/SubmitCustomGeometryEvent.getPoseStack:()Lcom/mojang/blaze3d/vertex/PoseStack;");
    }

    public SubmitCustomGeometryEvent() {
    }
}
