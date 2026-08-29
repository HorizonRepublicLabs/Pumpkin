package net.minecraft.client.renderer.texture;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.textures.GpuTextureView;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.client.resources.metadata.animation.FrameSize;
import net.minecraft.client.resources.metadata.texture.TextureMetadataSection;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import dev.pumpkin.shim.Unimplemented;

public class SpriteContents implements AutoCloseable, Stitcher.Entry {

    public SpriteContents(Identifier name, FrameSize frameSize, NativeImage image) {
    }

    public SpriteContents(Identifier name, FrameSize frameSize, NativeImage image, Optional<AnimationMetadataSection> animationInfo, List<MetadataSectionType.WithValue<?>> additionalMetadata, Optional<TextureMetadataSection> textureInfo) {
    }

    public int width() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/SpriteContents.width:()I");
    }

    public int height() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/SpriteContents.height:()I");
    }

    public Identifier name() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/SpriteContents.name:()Lnet/minecraft/resources/Identifier;");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/SpriteContents.close:()V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/SpriteContents.toString:()Ljava/lang/String;");
    }

    class AnimatedTexture {

        private AnimatedTexture(List<SpriteContents.FrameInfo> frames, int frameRowSize, boolean interpolateFrames) {
        }

        protected AnimatedTexture() {
        }
    }

    public class AnimationState implements AutoCloseable {

        private AnimationState(SpriteContents.AnimatedTexture animationInfo, Int2ObjectMap<GpuTextureView> frameTexturesByIndex, GpuBufferSlice[] spriteUbosByMip) {
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/texture/SpriteContents$AnimationState.tick:()V");
        }

        public void close() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/texture/SpriteContents$AnimationState.close:()V");
        }

        public AnimationState() {
        }
    }

    private record FrameInfo(int index, int time) {
    }

    public SpriteContents() {
    }
}
