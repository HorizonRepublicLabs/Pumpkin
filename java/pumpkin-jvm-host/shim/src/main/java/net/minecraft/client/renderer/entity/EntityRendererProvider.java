package net.minecraft.client.renderer.entity;

import net.minecraft.client.gui.Font;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MapRenderer;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.block.BlockModelResolver;
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
            throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRendererProvider$Context.<init>:(Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;Lnet/minecraft/client/renderer/block/BlockModelResolver;Lnet/minecraft/client/renderer/item/ItemModelResolver;Lnet/minecraft/client/renderer/MapRenderer;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/client/model/geom/EntityModelSet;Lnet/minecraft/client/resources/model/EquipmentAssetManager;Lnet/minecraft/client/resources/model/sprite/AtlasManager;Lnet/minecraft/client/gui/Font;Lnet/minecraft/client/renderer/PlayerSkinRenderCache;)V");
        }

        protected Context() {
        }
    }
}
