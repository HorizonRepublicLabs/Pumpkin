package net.neoforged.neoforge.client.renderstate;

import net.minecraft.util.context.ContextKey;
import net.neoforged.neoforge.client.extensions.IRenderStateExtension;
import dev.pumpkin.shim.Unimplemented;

public abstract class BaseRenderState implements IRenderStateExtension {

    public <T> T getRenderData(ContextKey<T> key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/renderstate/BaseRenderState.getRenderData:(Lnet/minecraft/util/context/ContextKey;)Ljava/lang/Object;");
    }

    public <T> void setRenderData(ContextKey<T> key, T data) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/renderstate/BaseRenderState.setRenderData:(Lnet/minecraft/util/context/ContextKey;Ljava/lang/Object;)V");
    }

    protected BaseRenderState() {
    }
}
