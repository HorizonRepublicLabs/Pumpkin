package net.minecraft.client.renderer.texture;

import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractTexture implements AutoCloseable {

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/AbstractTexture.close:()V");
    }

    protected AbstractTexture() {
    }
}
