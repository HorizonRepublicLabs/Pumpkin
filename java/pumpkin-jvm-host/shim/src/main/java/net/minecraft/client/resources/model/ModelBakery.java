package net.minecraft.client.resources.model;

import com.google.common.collect.Interner;
import java.util.Map;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.MissingItemModel;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.sprite.MaterialBaker;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public class ModelBakery {

    public ModelBakery(EntityModelSet entityModelSet, SpriteGetter sprites, PlayerSkinRenderCache playerSkinRenderCache, Map<BlockState, BlockStateModel.UnbakedRoot> unbakedBlockStateModels, Map<Identifier, ClientItem> clientInfos, Map<Identifier, ResolvedModel> resolvedModels, ResolvedModel missingModel) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery.<init>:(Lnet/minecraft/client/model/geom/EntityModelSet;Lnet/minecraft/client/resources/model/sprite/SpriteGetter;Lnet/minecraft/client/renderer/PlayerSkinRenderCache;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lnet/minecraft/client/resources/model/ResolvedModel;)V");
    }

    public ModelBakery(EntityModelSet entityModelSet, SpriteGetter sprites, PlayerSkinRenderCache playerSkinRenderCache, Map<BlockState, BlockStateModel.UnbakedRoot> unbakedBlockStateModels, Map<Identifier, ClientItem> clientInfos, Map<Identifier, ResolvedModel> resolvedModels, ResolvedModel missingModel, net.neoforged.neoforge.client.model.standalone.StandaloneModelLoader.LoadedModels standaloneModels, net.neoforged.neoforge.client.entity.animation.json.AnimationLoader.PendingAnimations pendingAnimations) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery.<init>:(Lnet/minecraft/client/model/geom/EntityModelSet;Lnet/minecraft/client/resources/model/sprite/SpriteGetter;Lnet/minecraft/client/renderer/PlayerSkinRenderCache;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lnet/minecraft/client/resources/model/ResolvedModel;Lnet/neoforged/neoforge/client/model/standalone/StandaloneModelLoader$LoadedModels;Lnet/neoforged/neoforge/client/entity/animation/json/AnimationLoader$PendingAnimations;)V");
    }

    public record BakingResult(ModelBakery.MissingModels missingModels, Map<BlockState, BlockStateModel> blockStateModels, Map<Identifier, ItemModel> itemStackModels, Map<Identifier, ClientItem.Properties> itemProperties, net.neoforged.neoforge.client.model.standalone.StandaloneModelLoader.BakedModels standaloneModels) {

        public BakingResult(ModelBakery.MissingModels missingModels, Map<BlockState, BlockStateModel> blockStateModels, Map<Identifier, ItemModel> itemStackModels, Map<Identifier, ClientItem.Properties> itemProperties) {
            this((ModelBakery.MissingModels) null, (Map<BlockState, BlockStateModel>) null, (Map<Identifier, ItemModel>) null, (Map<Identifier, ClientItem.Properties>) null, (net.neoforged.neoforge.client.model.standalone.StandaloneModelLoader.BakedModels) null);
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$BakingResult.<init>:(Lnet/minecraft/client/resources/model/ModelBakery$MissingModels;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V");
        }

        public BlockStateModel getBlockStateModel(BlockState blockState) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$BakingResult.getBlockStateModel:(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel;");
        }
    }

    private static class InternerImpl implements ModelBaker.Interner {

        public Vector3fc vector(Vector3fc v) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$InternerImpl.vector:(Lorg/joml/Vector3fc;)Lorg/joml/Vector3fc;");
        }

        public BakedQuad.MaterialInfo materialInfo(BakedQuad.MaterialInfo material) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$InternerImpl.materialInfo:(Lnet/minecraft/client/resources/model/geometry/BakedQuad$MaterialInfo;)Lnet/minecraft/client/resources/model/geometry/BakedQuad$MaterialInfo;");
        }

        public net.neoforged.neoforge.client.model.quad.BakedNormals normals(net.neoforged.neoforge.client.model.quad.BakedNormals normals) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$InternerImpl.normals:(Lnet/neoforged/neoforge/client/model/quad/BakedNormals;)Lnet/neoforged/neoforge/client/model/quad/BakedNormals;");
        }

        public net.neoforged.neoforge.client.model.quad.BakedColors colors(net.neoforged.neoforge.client.model.quad.BakedColors colors) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$InternerImpl.colors:(Lnet/neoforged/neoforge/client/model/quad/BakedColors;)Lnet/neoforged/neoforge/client/model/quad/BakedColors;");
        }

        protected InternerImpl() {
        }
    }

    public record MissingModels(BlockStateModelPart blockPart, BlockStateModel block, MissingItemModel item, FluidModel fluid) {

        public static ModelBakery.MissingModels bake(ResolvedModel unbaked, MaterialBaker materials, ModelBaker.Interner interner) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$MissingModels.bake:(Lnet/minecraft/client/resources/model/ResolvedModel;Lnet/minecraft/client/resources/model/sprite/MaterialBaker;Lnet/minecraft/client/resources/model/ModelBaker$Interner;)Lnet/minecraft/client/resources/model/ModelBakery$MissingModels;");
        }
    }

    private class ModelBakerImpl implements ModelBaker {

        private ModelBakerImpl(MaterialBaker materials, ModelBaker.Interner interner, ModelBakery.MissingModels missingModels) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$ModelBakerImpl.<init>:(Lnet/minecraft/client/resources/model/sprite/MaterialBaker;Lnet/minecraft/client/resources/model/ModelBaker$Interner;Lnet/minecraft/client/resources/model/ModelBakery$MissingModels;)V");
        }

        public BlockStateModelPart missingBlockModelPart() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$ModelBakerImpl.missingBlockModelPart:()Lnet/minecraft/client/renderer/block/dispatch/BlockStateModelPart;");
        }

        public MaterialBaker materials() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$ModelBakerImpl.materials:()Lnet/minecraft/client/resources/model/sprite/MaterialBaker;");
        }

        public ModelBaker.Interner interner() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$ModelBakerImpl.interner:()Lnet/minecraft/client/resources/model/ModelBaker$Interner;");
        }

        public ResolvedModel getModel(Identifier location) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$ModelBakerImpl.getModel:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/resources/model/ResolvedModel;");
        }

        public <T> T compute(ModelBaker.SharedOperationKey<T> key) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelBakery$ModelBakerImpl.compute:(Lnet/minecraft/client/resources/model/ModelBaker$SharedOperationKey;)Ljava/lang/Object;");
        }

        protected ModelBakerImpl() {
        }
    }

    public ModelBakery() {
    }
}
