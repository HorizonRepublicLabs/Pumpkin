package net.minecraft.client.data.models;

import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.util.random.WeightedList;
import dev.pumpkin.shim.Unimplemented;

public record MultiVariant(WeightedList<Variant> variants, WeightedList<net.neoforged.neoforge.client.model.generators.blockstate.CustomBlockStateModelBuilder> customBlockStateModels) {

    public MultiVariant(WeightedList<Variant> variants) {
        this((WeightedList<Variant>) null, (WeightedList<net.neoforged.neoforge.client.model.generators.blockstate.CustomBlockStateModelBuilder>) null);
        throw Unimplemented.forMember("net/minecraft/client/data/models/MultiVariant.<init>:(Lnet/minecraft/util/random/WeightedList;)V");
    }

    public MultiVariant with(VariantMutator mutator) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/MultiVariant.with:(Lnet/minecraft/client/renderer/block/dispatch/VariantMutator;)Lnet/minecraft/client/data/models/MultiVariant;");
    }

    public MultiVariant with(net.neoforged.neoforge.client.model.generators.blockstate.UnbakedMutator mutator) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/MultiVariant.with:(Lnet/neoforged/neoforge/client/model/generators/blockstate/UnbakedMutator;)Lnet/minecraft/client/data/models/MultiVariant;");
    }

    public BlockStateModel.Unbaked toUnbaked() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/MultiVariant.toUnbaked:()Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel$Unbaked;");
    }
}
