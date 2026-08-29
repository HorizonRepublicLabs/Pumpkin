package net.minecraft.client.resources.model.geometry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public record BakedQuad(Vector3fc position0, Vector3fc position1, Vector3fc position2, Vector3fc position3, long packedUV0, long packedUV1, long packedUV2, long packedUV3, Direction direction, BakedQuad.MaterialInfo materialInfo, net.neoforged.neoforge.client.model.quad.BakedNormals bakedNormals, net.neoforged.neoforge.client.model.quad.BakedColors bakedColors) {

    public BakedQuad(Vector3fc position0, Vector3fc position1, Vector3fc position2, Vector3fc position3, long packedUV0, long packedUV1, long packedUV2, long packedUV3, Direction direction, BakedQuad.MaterialInfo materialInfo) {
        this((Vector3fc) null, (Vector3fc) null, (Vector3fc) null, (Vector3fc) null, (long) 0L, (long) 0L, (long) 0L, (long) 0L, (Direction) null, (BakedQuad.MaterialInfo) null, (net.neoforged.neoforge.client.model.quad.BakedNormals) null, (net.neoforged.neoforge.client.model.quad.BakedColors) null);
        throw Unimplemented.forMember("net/minecraft/client/resources/model/geometry/BakedQuad.<init>:(Lorg/joml/Vector3fc;Lorg/joml/Vector3fc;Lorg/joml/Vector3fc;Lorg/joml/Vector3fc;JJJJLnet/minecraft/core/Direction;Lnet/minecraft/client/resources/model/geometry/BakedQuad$MaterialInfo;)V");
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface MaterialFlags {
    }

    public record MaterialInfo(TextureAtlasSprite sprite, ChunkSectionLayer layer, RenderType itemRenderType, int tintIndex, boolean shade, int lightEmission, boolean ambientOcclusion) {

        public MaterialInfo(TextureAtlasSprite sprite, ChunkSectionLayer layer, RenderType itemRenderType, int tintIndex, boolean shade, int lightEmission) {
            this((TextureAtlasSprite) null, (ChunkSectionLayer) null, (RenderType) null, (int) 0, (boolean) false, (int) 0, (boolean) false);
            throw Unimplemented.forMember("net/minecraft/client/resources/model/geometry/BakedQuad$MaterialInfo.<init>:(Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;Lnet/minecraft/client/renderer/chunk/ChunkSectionLayer;Lnet/minecraft/client/renderer/rendertype/RenderType;IZI)V");
        }
    }
}
