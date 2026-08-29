package net.minecraft.client.renderer.block.dispatch.multipart;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.DynamicBlockStateModel;
import dev.pumpkin.shim.Unimplemented;

public class MultiPartModel implements BlockStateModel, DynamicBlockStateModel {

    private MultiPartModel(MultiPartModel.SharedBakedState shared, BlockState blockState) {
    }

    public Material.Baked particleMaterial() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel.particleMaterial:()Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    public int materialFlags() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel.materialFlags:()I");
    }

    public void collectParts(net.minecraft.client.renderer.block.BlockAndTintGetter level, net.minecraft.core.BlockPos pos, BlockState state, RandomSource random, List<BlockStateModelPart> output) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel.collectParts:(Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;Ljava/util/List;)V");
    }

    public Object createGeometryKey(net.minecraft.client.renderer.block.BlockAndTintGetter level, net.minecraft.core.BlockPos pos, BlockState state, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel.createGeometryKey:(Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)Ljava/lang/Object;");
    }

    private record GeometryKey(List<Object> subKeys, MultiPartModel multiPart) {
    }

    public Material.Baked particleMaterial(net.minecraft.client.renderer.block.BlockAndTintGetter level, net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel.particleMaterial:(Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    public int materialFlags(net.minecraft.client.renderer.block.BlockAndTintGetter level, net.minecraft.core.BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel.materialFlags:(Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)I");
    }

    public record Selector<T>(Predicate<BlockState> condition, T model) {

        public <S> MultiPartModel.Selector<S> with(S newModel) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel$Selector.with:(Ljava/lang/Object;)Lnet/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel$Selector;");
        }
    }

    private static final class SharedBakedState {

        public SharedBakedState(List<MultiPartModel.Selector<BlockStateModel>> selectors) {
        }

        protected SharedBakedState() {
        }
    }

    public static class Unbaked implements BlockStateModel.UnbakedRoot {

        public Unbaked(List<MultiPartModel.Selector<BlockStateModel.Unbaked>> selectors) {
        }

        public Object visualEqualityGroup(BlockState blockState) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel$Unbaked.visualEqualityGroup:(Lnet/minecraft/world/level/block/state/BlockState;)Ljava/lang/Object;");
        }

        public void resolveDependencies(ResolvableModel.Resolver resolver) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel$Unbaked.resolveDependencies:(Lnet/minecraft/client/resources/model/ResolvableModel$Resolver;)V");
        }

        public BlockStateModel bake(BlockState blockState, ModelBaker modelBakery) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel$Unbaked.bake:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/client/resources/model/ModelBaker;)Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel;");
        }

        public Unbaked() {
        }
    }

    public MultiPartModel() {
    }
}
