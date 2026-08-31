package net.minecraft.client.renderer.item;

import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvedModel;
import net.minecraft.client.resources.model.cuboid.ItemTransforms;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.minecraft.world.item.ItemDisplayContext;
import dev.pumpkin.shim.Unimplemented;

public record ModelRenderProperties(boolean usesBlockLight, Material.Baked particleMaterial, ItemTransforms transforms) {

    public static ModelRenderProperties fromResolvedModel(ModelBaker baker, ResolvedModel resolvedModel, TextureSlots textureSlots) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ModelRenderProperties.fromResolvedModel:(Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/resources/model/ResolvedModel;Lnet/minecraft/client/resources/model/sprite/TextureSlots;)Lnet/minecraft/client/renderer/item/ModelRenderProperties;");
    }

    public void applyToLayer(ItemStackRenderState.LayerRenderState layer, ItemDisplayContext displayContext) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ModelRenderProperties.applyToLayer:(Lnet/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState;Lnet/minecraft/world/item/ItemDisplayContext;)V");
    }
}
