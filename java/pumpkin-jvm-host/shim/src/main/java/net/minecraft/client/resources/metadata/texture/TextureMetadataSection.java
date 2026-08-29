package net.minecraft.client.resources.metadata.texture;

import net.minecraft.client.renderer.texture.MipmapStrategy;

public record TextureMetadataSection(boolean blur, boolean clamp, MipmapStrategy mipmapStrategy, float alphaCutoffBias) {
}
