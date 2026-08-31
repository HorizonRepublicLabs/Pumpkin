package net.minecraft.client.renderer.block.dispatch;

import com.mojang.math.Quadrant;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record Variant(Identifier modelLocation, Variant.SimpleModelState modelState) implements BlockStateModelPart.Unbaked {

    public static final MapCodec<Variant> MAP_CODEC =
            dev.pumpkin.shim.Stubs.throwingMapCodec("net.minecraft.client.renderer.block.dispatch.Variant.MAP_CODEC");

    public Variant(Identifier modelLocation) {
        this((Identifier) null, (Variant.SimpleModelState) null);
    }

    public Variant with(VariantMutator mutator) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/Variant.with:(Lnet/minecraft/client/renderer/block/dispatch/VariantMutator;)Lnet/minecraft/client/renderer/block/dispatch/Variant;");
    }

    public BlockStateModelPart bake(ModelBaker modelBakery) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/Variant.bake:(Lnet/minecraft/client/resources/model/ModelBaker;)Lnet/minecraft/client/renderer/block/dispatch/BlockStateModelPart;");
    }

    public void resolveDependencies(ResolvableModel.Resolver resolver) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/Variant.resolveDependencies:(Lnet/minecraft/client/resources/model/ResolvableModel$Resolver;)V");
    }

    public record SimpleModelState(Quadrant x, Quadrant y, Quadrant z, boolean uvLock) {

        public static final MapCodec<Variant.SimpleModelState> MAP_CODEC =
                dev.pumpkin.shim.Stubs.throwingMapCodec("net.minecraft.client.renderer.block.dispatch.Variant.MAP_CODEC");

        public static final Variant.SimpleModelState DEFAULT = null;

        public ModelState asModelState() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/Variant$SimpleModelState.asModelState:()Lnet/minecraft/client/renderer/block/dispatch/ModelState;");
        }
    }
}
