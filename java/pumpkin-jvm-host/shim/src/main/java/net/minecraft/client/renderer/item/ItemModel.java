package net.minecraft.client.renderer.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.util.RegistryContextSwapper;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4fc;
import dev.pumpkin.shim.Unimplemented;

public interface ItemModel {

    void update(ItemStackRenderState output, ItemStack item, ItemModelResolver resolver, ItemDisplayContext displayContext, ClientLevel level, ItemOwner owner, int seed);

    record BakingContext(ModelBaker blockModelBaker, EntityModelSet entityModelSet, SpriteGetter sprites, PlayerSkinRenderCache playerSkinRenderCache, MissingItemModel missingItemModel, RegistryContextSwapper contextSwapper, net.neoforged.neoforge.client.entity.animation.json.AnimationLoader.PendingAnimations pendingAnimations) implements SpecialModelRenderer.BakingContext {

        public BakingContext(ModelBaker blockModelBaker, EntityModelSet entityModelSet, SpriteGetter sprites, PlayerSkinRenderCache playerSkinRenderCache, MissingItemModel missingItemModel, RegistryContextSwapper contextSwapper) {
            this((ModelBaker) null, (EntityModelSet) null, (SpriteGetter) null, (PlayerSkinRenderCache) null, (MissingItemModel) null, (RegistryContextSwapper) null, (net.neoforged.neoforge.client.entity.animation.json.AnimationLoader.PendingAnimations) null);
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemModel$BakingContext.<init>:(Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/model/geom/EntityModelSet;Lnet/minecraft/client/resources/model/sprite/SpriteGetter;Lnet/minecraft/client/renderer/PlayerSkinRenderCache;Lnet/minecraft/client/renderer/item/MissingItemModel;Lnet/minecraft/util/RegistryContextSwapper;)V");
        }
    }

    interface Unbaked extends ResolvableModel {

        MapCodec<? extends ItemModel.Unbaked> type();

        ItemModel bake(ItemModel.BakingContext context, Matrix4fc transformation);
    }
}
