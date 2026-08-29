package net.neoforged.neoforge.client.model.generators.blockstate;

import java.util.Map;
import java.util.function.UnaryOperator;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import dev.pumpkin.shim.Unimplemented;

public final class UnbakedMutator {

    private UnbakedMutator(Map<Class<?>, Handler<?>> handlers) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/generators/blockstate/UnbakedMutator.<init>:(Ljava/util/Map;)V");
    }

    public <T extends BlockStateModel.Unbaked> T apply(T unbaked) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/generators/blockstate/UnbakedMutator.apply:(Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel$Unbaked;)Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel$Unbaked;");
    }

    public static final class Builder {

        protected Builder() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/generators/blockstate/UnbakedMutator$Builder.<init>:()V");
        }

        public <T extends BlockStateModel.Unbaked> Builder add(Class<T> supportedClass, UnaryOperator<T> operator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/generators/blockstate/UnbakedMutator$Builder.add:(Ljava/lang/Class;Ljava/util/function/UnaryOperator;)Lnet/neoforged/neoforge/client/model/generators/blockstate/UnbakedMutator$Builder;");
        }

        public UnbakedMutator build() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/generators/blockstate/UnbakedMutator$Builder.build:()Lnet/neoforged/neoforge/client/model/generators/blockstate/UnbakedMutator;");
        }
    }

    private record Handler<T>(Class<T> supportedClass, UnaryOperator<T> operator) {

        public T apply(BlockStateModel.Unbaked unbaked) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/generators/blockstate/UnbakedMutator$Handler.apply:(Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel$Unbaked;)Ljava/lang/Object;");
        }
    }

    public UnbakedMutator() {
    }
}
