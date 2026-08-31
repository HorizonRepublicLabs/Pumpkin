package net.neoforged.neoforge.client.extensions;

import net.minecraft.util.context.ContextKey;
import dev.pumpkin.shim.Unimplemented;

public interface IRenderStateExtension {

    <T> T getRenderData(ContextKey<T> key);

    <T> void setRenderData(ContextKey<T> key, T data);

    default <T> T getRenderDataOrDefault(ContextKey<T> key, T defaultVal) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/IRenderStateExtension.getRenderDataOrDefault:(Lnet/minecraft/util/context/ContextKey;Ljava/lang/Object;)Ljava/lang/Object;");
    }
}
