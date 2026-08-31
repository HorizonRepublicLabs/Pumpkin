package net.neoforged.neoforge.client.event;

import java.util.List;
import java.util.function.Supplier;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.client.gui.PictureInPictureRendererRegistration;
import dev.pumpkin.shim.Unimplemented;

public final class RegisterPictureInPictureRenderersEvent extends Event implements IModBusEvent {

    public RegisterPictureInPictureRenderersEvent(List<PictureInPictureRendererRegistration<?>> renderers) {
    }

    public <T extends PictureInPictureRenderState> void register(Class<T> stateClass, Supplier<PictureInPictureRenderer<T>> factory) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterPictureInPictureRenderersEvent.register:(Ljava/lang/Class;Ljava/util/function/Supplier;)V");
    }

    public RegisterPictureInPictureRenderersEvent() {
    }
}
