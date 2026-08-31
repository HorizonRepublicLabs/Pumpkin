package net.neoforged.neoforge.client.renderstate;

import com.google.common.reflect.TypeToken;
import java.util.function.BiConsumer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterRenderStateModifiersEvent extends Event implements IModBusEvent {

    public RegisterRenderStateModifiersEvent() {
    }

    public <E extends Entity, S extends EntityRenderState> void registerEntityModifier(TypeToken<? extends EntityRenderer<? extends E, ? extends S>> baseRenderer, BiConsumer<E, S> modifier) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/renderstate/RegisterRenderStateModifiersEvent.registerEntityModifier:(Lcom/google/common/reflect/TypeToken;Ljava/util/function/BiConsumer;)V");
    }

    public <E extends Entity, S extends EntityRenderState> void registerEntityModifier(Class<? extends EntityRenderer<? extends E, ? extends S>> baseRenderer, BiConsumer<E, S> modifier) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/renderstate/RegisterRenderStateModifiersEvent.registerEntityModifier:(Ljava/lang/Class;Ljava/util/function/BiConsumer;)V");
    }

    private record Container<X>() {
    }
}
