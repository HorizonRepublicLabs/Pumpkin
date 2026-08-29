package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.function.Supplier;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MapRenderer;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.EquipmentAssetManager;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class EntityRenderDispatcher implements ResourceManagerReloadListener {

    public EntityRenderDispatcher(Minecraft minecraft, TextureManager textureManager, BlockModelResolver blockModelResolver, ItemModelResolver itemModelResolver, MapRenderer mapRenderer, AtlasManager atlasManager, Font font, Options options, Supplier<EntityModelSet> entityModels, EquipmentAssetManager equipmentAssets, PlayerSkinRenderCache playerSkinRenderCache) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderDispatcher.<init>:(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/client/renderer/block/BlockModelResolver;Lnet/minecraft/client/renderer/item/ItemModelResolver;Lnet/minecraft/client/renderer/MapRenderer;Lnet/minecraft/client/resources/model/sprite/AtlasManager;Lnet/minecraft/client/gui/Font;Lnet/minecraft/client/Options;Ljava/util/function/Supplier;Lnet/minecraft/client/resources/model/EquipmentAssetManager;Lnet/minecraft/client/renderer/PlayerSkinRenderCache;)V");
    }

    public void prepare(Camera camera, Entity crosshairPickEntity) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderDispatcher.prepare:(Lnet/minecraft/client/Camera;Lnet/minecraft/world/entity/Entity;)V");
    }

    public <E extends Entity> EntityRenderState extractEntity(E entity, float partialTicks) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderDispatcher.extractEntity:(Lnet/minecraft/world/entity/Entity;F)Lnet/minecraft/client/renderer/entity/state/EntityRenderState;");
    }

    public <S extends EntityRenderState> void submit(S renderState, CameraRenderState camera, double x, double y, double z, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderDispatcher.submit:(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;Lnet/minecraft/client/renderer/state/level/CameraRenderState;DDDLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;)V");
    }

    public void onResourceManagerReload(ResourceManager resourceManager) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/EntityRenderDispatcher.onResourceManagerReload:(Lnet/minecraft/server/packs/resources/ResourceManager;)V");
    }

    protected EntityRenderDispatcher() {
    }
}
