package net.minecraft.client.renderer.entity;

import net.minecraft.client.gui.Font;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MapRenderer;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.resources.model.EquipmentAssetManager;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public interface EntityRendererProvider<T extends Entity> {

    EntityRenderer<T, ?> create(EntityRendererProvider.Context context);

    class Context {

        public Context(EntityRenderDispatcher entityRenderDispatcher, BlockModelResolver blockModelResolver, ItemModelResolver itemModelResolver, MapRenderer mapRenderer, ResourceManager resourceManager, EntityModelSet modelSet, EquipmentAssetManager equipmentAssets, AtlasManager atlasManager, Font font, PlayerSkinRenderCache playerSkinRenderCache) {
        }

        public BlockModelResolver getBlockModelResolver() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRendererProvider$Context.getBlockModelResolver:()Lnet/minecraft/client/renderer/block/BlockModelResolver;");
        }

        public EntityModelSet getModelSet() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRendererProvider$Context.getModelSet:()Lnet/minecraft/client/model/geom/EntityModelSet;");
        }

        public EquipmentLayerRenderer getEquipmentRenderer() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRendererProvider$Context.getEquipmentRenderer:()Lnet/minecraft/client/renderer/entity/layers/EquipmentLayerRenderer;");
        }

        public ModelPart bakeLayer(ModelLayerLocation id) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRendererProvider$Context.bakeLayer:(Lnet/minecraft/client/model/geom/ModelLayerLocation;)Lnet/minecraft/client/model/geom/ModelPart;");
        }

        protected Context() {
        }
    }
}
