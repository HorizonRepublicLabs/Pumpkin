package net.minecraft.client.renderer.texture;

import com.mojang.blaze3d.platform.NativeImage;
import java.io.Closeable;
import java.io.IOException;
import net.minecraft.client.resources.metadata.texture.TextureMetadataSection;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import dev.pumpkin.shim.Unimplemented;

public record TextureContents(NativeImage image, TextureMetadataSection metadata) implements Closeable {

    public static TextureContents load(ResourceManager resourceManager, Identifier location) throws IOException {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureContents.load:(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/texture/TextureContents;");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/TextureContents.close:()V");
    }
}
