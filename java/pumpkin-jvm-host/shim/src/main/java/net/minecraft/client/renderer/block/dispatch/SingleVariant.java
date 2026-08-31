package net.minecraft.client.renderer.block.dispatch;

import java.util.List;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class SingleVariant implements BlockStateModel {

    public SingleVariant(BlockStateModelPart model) {
    }

    public void collectParts(RandomSource random, List<BlockStateModelPart> output) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/SingleVariant.collectParts:(Lnet/minecraft/util/RandomSource;Ljava/util/List;)V");
    }

    public Material.Baked particleMaterial() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/SingleVariant.particleMaterial:()Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    public int materialFlags() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/SingleVariant.materialFlags:()I");
    }

    public Object createGeometryKey(net.minecraft.client.renderer.block.BlockAndTintGetter level, net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/SingleVariant.createGeometryKey:(Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)Ljava/lang/Object;");
    }

    public record Unbaked(Variant variant) implements BlockStateModel.Unbaked {

        public BlockStateModel bake(ModelBaker modelBakery) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/SingleVariant$Unbaked.bake:(Lnet/minecraft/client/resources/model/ModelBaker;)Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel;");
        }

        public void resolveDependencies(ResolvableModel.Resolver resolver) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/SingleVariant$Unbaked.resolveDependencies:(Lnet/minecraft/client/resources/model/ResolvableModel$Resolver;)V");
        }
    }

    public SingleVariant() {
    }
}
