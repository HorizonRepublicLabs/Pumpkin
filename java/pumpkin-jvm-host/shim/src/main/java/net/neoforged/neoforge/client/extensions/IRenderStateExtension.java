package net.neoforged.neoforge.client.extensions;

import net.minecraft.util.context.ContextKey;

public interface IRenderStateExtension {

    <T> T getRenderData(ContextKey<T> key);

    <T> void setRenderData(ContextKey<T> key, T data);
}
