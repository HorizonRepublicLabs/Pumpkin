package net.minecraft.client.resources.model;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.block.BlockStateModelSet;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.block.FluidStateModelSet;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.texture.SpriteLoader;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.MaterialBaker;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import dev.pumpkin.shim.Unimplemented;

public class ModelManager implements PreparableReloadListener {

    private static final FileToIdConverter MODEL_LISTER = null;

    public ModelManager(BlockColors blockColors, AtlasManager atlasManager, PlayerSkinRenderCache playerSkinRenderCache) {
    }

    public BlockStateModelSet getBlockStateModelSet() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelManager.getBlockStateModelSet:()Lnet/minecraft/client/renderer/block/BlockStateModelSet;");
    }

    public FluidStateModelSet getFluidStateModelSet() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelManager.getFluidStateModelSet:()Lnet/minecraft/client/renderer/block/FluidStateModelSet;");
    }

    public final CompletableFuture<Void> reload(PreparableReloadListener.SharedState currentReload, Executor taskExecutor, PreparableReloadListener.PreparationBarrier preparationBarrier, Executor reloadExecutor) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelManager.reload:(Lnet/minecraft/server/packs/resources/PreparableReloadListener$SharedState;Ljava/util/concurrent/Executor;Lnet/minecraft/server/packs/resources/PreparableReloadListener$PreparationBarrier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

    private void apply(ModelManager.ReloadState preparations) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelManager.apply:(Lnet/minecraft/client/resources/model/ModelManager$ReloadState;)V");
    }

    private static class BlockOnlyMaterialBaker extends MaterialBaker {

        public BlockOnlyMaterialBaker(SpriteLoader.Preparations blockAtlas) {
        }

        protected Material.Baked bake(Material material) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelManager$BlockOnlyMaterialBaker.bake:(Lnet/minecraft/client/resources/model/sprite/Material;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
        }

        protected BlockOnlyMaterialBaker() {
        }
    }

    private static class CombinedBlockItemMaterialBaker extends MaterialBaker {

        public CombinedBlockItemMaterialBaker(SpriteLoader.Preparations blockAtlas, SpriteLoader.Preparations itemAtlas) {
        }

        protected Material.Baked bake(Material material) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/ModelManager$CombinedBlockItemMaterialBaker.bake:(Lnet/minecraft/client/resources/model/sprite/Material;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
        }

        protected CombinedBlockItemMaterialBaker() {
        }
    }

    private record ReloadState(ModelBakery.BakingResult bakedModels, Object2IntMap<BlockState> modelGroups, Map<BlockState, BlockStateModel> blockStateModels, Map<BlockState, BlockModel> blockModels, Map<Fluid, FluidModel> fluidModels, EntityModelSet entityModelSet) {
    }

    private record ResolvedModels(ResolvedModel missing, Map<Identifier, ResolvedModel> models) {
    }

    public ModelManager() {
    }
}
