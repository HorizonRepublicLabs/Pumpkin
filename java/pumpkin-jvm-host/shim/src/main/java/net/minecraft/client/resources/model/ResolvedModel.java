package net.minecraft.client.resources.model;

import net.minecraft.client.resources.model.cuboid.ItemTransforms;
import net.minecraft.client.resources.model.geometry.UnbakedGeometry;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.neoforged.neoforge.client.extensions.ResolvedModelExtension;
import dev.pumpkin.shim.Unimplemented;

public interface ResolvedModel extends ModelDebugName, ResolvedModelExtension {

    UnbakedModel wrapped();

    ResolvedModel parent();

    default TextureSlots getTopTextureSlots() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ResolvedModel.getTopTextureSlots:()Lnet/minecraft/client/resources/model/sprite/TextureSlots;");
    }

    default boolean getTopAmbientOcclusion() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ResolvedModel.getTopAmbientOcclusion:()Z");
    }

    default UnbakedModel.GuiLight getTopGuiLight() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ResolvedModel.getTopGuiLight:()Lnet/minecraft/client/resources/model/UnbakedModel$GuiLight;");
    }

    default UnbakedGeometry getTopGeometry() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ResolvedModel.getTopGeometry:()Lnet/minecraft/client/resources/model/geometry/UnbakedGeometry;");
    }

    static Material.Baked resolveParticleMaterial(TextureSlots textureSlots, ModelBaker baker, ModelDebugName resolvedModel) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ResolvedModel.resolveParticleMaterial:(Lnet/minecraft/client/resources/model/sprite/TextureSlots;Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/resources/model/ModelDebugName;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    default Material.Baked resolveParticleMaterial(TextureSlots textureSlots, ModelBaker baker) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ResolvedModel.resolveParticleMaterial:(Lnet/minecraft/client/resources/model/sprite/TextureSlots;Lnet/minecraft/client/resources/model/ModelBaker;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    default ItemTransforms getTopTransforms() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ResolvedModel.getTopTransforms:()Lnet/minecraft/client/resources/model/cuboid/ItemTransforms;");
    }
}
