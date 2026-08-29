package net.minecraft.client.renderer.texture;

import java.io.IOException;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import dev.pumpkin.shim.Unimplemented;

public abstract class ReloadableTexture extends AbstractTexture {

    public ReloadableTexture(Identifier resourceId) {
    }

    public void apply(TextureContents contents) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/ReloadableTexture.apply:(Lnet/minecraft/client/renderer/texture/TextureContents;)V");
    }

    public abstract TextureContents loadContents(ResourceManager resourceManager) throws IOException;

    public ReloadableTexture() {
    }
}
