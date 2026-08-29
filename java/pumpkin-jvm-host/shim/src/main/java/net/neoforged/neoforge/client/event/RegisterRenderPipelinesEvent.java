package net.neoforged.neoforge.client.event;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.util.function.Consumer;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterRenderPipelinesEvent extends Event implements IModBusEvent {

    public RegisterRenderPipelinesEvent(Consumer<RenderPipeline> registrar) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterRenderPipelinesEvent.<init>:(Ljava/util/function/Consumer;)V");
    }

    public void registerPipeline(RenderPipeline pipeline) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterRenderPipelinesEvent.registerPipeline:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)V");
    }

    protected RegisterRenderPipelinesEvent() {
    }
}
