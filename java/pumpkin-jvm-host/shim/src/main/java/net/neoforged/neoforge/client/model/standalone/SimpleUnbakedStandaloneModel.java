package net.neoforged.neoforge.client.model.standalone;

import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.ModelState;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.ResolvedModel;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public final class SimpleUnbakedStandaloneModel<T> implements UnbakedStandaloneModel<T> {

    public SimpleUnbakedStandaloneModel(Identifier modelId, SimpleBaker<T> bake) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/standalone/SimpleUnbakedStandaloneModel.<init>:(Lnet/minecraft/resources/Identifier;Lnet/neoforged/neoforge/client/model/standalone/SimpleUnbakedStandaloneModel$SimpleBaker;)V");
    }

    public T bake(ModelBaker baker, ModelDebugName name) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/standalone/SimpleUnbakedStandaloneModel.bake:(Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/resources/model/ModelDebugName;)Ljava/lang/Object;");
    }

    public void resolveDependencies(Resolver resolver) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/standalone/SimpleUnbakedStandaloneModel.resolveDependencies:(Lnet/neoforged/neoforge/client/model/standalone/Resolver;)V");
    }

    public static SimpleUnbakedStandaloneModel<BlockStateModel> blockStateModel(Identifier modelId) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/standalone/SimpleUnbakedStandaloneModel.blockStateModel:(Lnet/minecraft/resources/Identifier;)Lnet/neoforged/neoforge/client/model/standalone/SimpleUnbakedStandaloneModel;");
    }

    public static SimpleUnbakedStandaloneModel<BlockStateModel> blockStateModel(Identifier modelId, ModelState modelState) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/standalone/SimpleUnbakedStandaloneModel.blockStateModel:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/renderer/block/dispatch/ModelState;)Lnet/neoforged/neoforge/client/model/standalone/SimpleUnbakedStandaloneModel;");
    }

    public interface SimpleBaker<T> {

        T bake(ResolvedModel model, ModelBaker baker, ModelDebugName name);
    }

    protected SimpleUnbakedStandaloneModel() {
    }
}
