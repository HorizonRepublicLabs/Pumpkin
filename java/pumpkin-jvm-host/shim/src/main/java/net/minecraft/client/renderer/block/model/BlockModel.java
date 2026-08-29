package net.minecraft.client.renderer.block.model;

import java.util.function.Function;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Matrix4fc;
import dev.pumpkin.shim.Unimplemented;

public interface BlockModel {

    void update(BlockModelRenderState output, BlockState blockState, BlockDisplayContext displayContext, long seed);

    record BakingContext(EntityModelSet entityModelSet, SpriteGetter sprites, PlayerSkinRenderCache playerSkinRenderCache, Function<BlockState, BlockStateModel> modelGetter, BlockModel missingBlockModel, net.neoforged.neoforge.client.entity.animation.json.AnimationLoader.PendingAnimations pendingAnimations) implements SpecialModelRenderer.BakingContext {

        public BakingContext(EntityModelSet entityModelSet, SpriteGetter sprites, PlayerSkinRenderCache playerSkinRenderCache, Function<BlockState, BlockStateModel> modelGetter, BlockModel missingBlockModel) {
            this((EntityModelSet) null, (SpriteGetter) null, (PlayerSkinRenderCache) null, (Function<BlockState, BlockStateModel>) null, (BlockModel) null, (net.neoforged.neoforge.client.entity.animation.json.AnimationLoader.PendingAnimations) null);
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/model/BlockModel$BakingContext.<init>:(Lnet/minecraft/client/model/geom/EntityModelSet;Lnet/minecraft/client/resources/model/sprite/SpriteGetter;Lnet/minecraft/client/renderer/PlayerSkinRenderCache;Ljava/util/function/Function;Lnet/minecraft/client/renderer/block/model/BlockModel;)V");
        }
    }

    interface Unbaked {

        BlockModel bake(BlockModel.BakingContext context, Matrix4fc transformation);
    }
}
