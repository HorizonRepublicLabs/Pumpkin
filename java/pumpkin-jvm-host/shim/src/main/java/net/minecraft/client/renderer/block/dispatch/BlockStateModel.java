package net.minecraft.client.renderer.block.dispatch;

import java.util.List;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.extensions.BlockStateModelExtension;
import dev.pumpkin.shim.Unimplemented;

public interface BlockStateModel extends BlockStateModelExtension {

    void collectParts(RandomSource random, List<BlockStateModelPart> output);

    Material.Baked particleMaterial();

    int materialFlags();

    class SimpleCachedUnbakedRoot implements BlockStateModel.UnbakedRoot {

        public SimpleCachedUnbakedRoot(BlockStateModel.Unbaked contents) {
        }

        public void resolveDependencies(ResolvableModel.Resolver resolver) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockStateModel$SimpleCachedUnbakedRoot.resolveDependencies:(Lnet/minecraft/client/resources/model/ResolvableModel$Resolver;)V");
        }

        public BlockStateModel bake(BlockState blockState, ModelBaker modelBakery) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockStateModel$SimpleCachedUnbakedRoot.bake:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/client/resources/model/ModelBaker;)Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel;");
        }

        public Object visualEqualityGroup(BlockState blockState) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockStateModel$SimpleCachedUnbakedRoot.visualEqualityGroup:(Lnet/minecraft/world/level/block/state/BlockState;)Ljava/lang/Object;");
        }

        protected SimpleCachedUnbakedRoot() {
        }
    }

    interface Unbaked extends ResolvableModel {

        BlockStateModel bake(ModelBaker modelBakery);
    }

    interface UnbakedRoot extends ResolvableModel {

        BlockStateModel bake(BlockState blockState, ModelBaker modelBakery);

        Object visualEqualityGroup(BlockState blockState);
    }
}
