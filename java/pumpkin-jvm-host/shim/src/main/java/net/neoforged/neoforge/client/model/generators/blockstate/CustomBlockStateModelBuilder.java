package net.neoforged.neoforge.client.model.generators.blockstate;

import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.neoforged.neoforge.client.model.block.CustomUnbakedBlockStateModel;
import dev.pumpkin.shim.Unimplemented;

public abstract class CustomBlockStateModelBuilder {

    protected CustomBlockStateModelBuilder() {
    }

    public abstract CustomBlockStateModelBuilder with(VariantMutator variantMutator);

    public abstract CustomBlockStateModelBuilder with(UnbakedMutator variantMutator);

    public abstract CustomUnbakedBlockStateModel toUnbaked();

    public static final class Simple extends CustomBlockStateModelBuilder {

        public Simple(CustomUnbakedBlockStateModel blockStateModel) {
        }

        public Simple with(VariantMutator variantMutator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/generators/blockstate/CustomBlockStateModelBuilder$Simple.with:(Lnet/minecraft/client/renderer/block/dispatch/VariantMutator;)Lnet/neoforged/neoforge/client/model/generators/blockstate/CustomBlockStateModelBuilder$Simple;");
        }

        public CustomBlockStateModelBuilder with(UnbakedMutator variantMutator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/generators/blockstate/CustomBlockStateModelBuilder$Simple.with:(Lnet/neoforged/neoforge/client/model/generators/blockstate/UnbakedMutator;)Lnet/neoforged/neoforge/client/model/generators/blockstate/CustomBlockStateModelBuilder;");
        }

        public CustomUnbakedBlockStateModel toUnbaked() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/generators/blockstate/CustomBlockStateModelBuilder$Simple.toUnbaked:()Lnet/neoforged/neoforge/client/model/block/CustomUnbakedBlockStateModel;");
        }

        public Simple() {
        }
    }
}
