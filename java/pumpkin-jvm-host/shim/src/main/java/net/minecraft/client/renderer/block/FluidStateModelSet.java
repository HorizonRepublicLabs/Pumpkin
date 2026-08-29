package net.minecraft.client.renderer.block;

import java.util.Map;
import net.minecraft.client.resources.model.sprite.MaterialBaker;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import dev.pumpkin.shim.Unimplemented;

public class FluidStateModelSet {

    public FluidStateModelSet(Map<Fluid, FluidModel> modelByFluid, FluidModel missingModel) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/FluidStateModelSet.<init>:(Ljava/util/Map;Lnet/minecraft/client/renderer/block/FluidModel;)V");
    }

    public static Map<Fluid, FluidModel> bake(MaterialBaker materials) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/FluidStateModelSet.bake:(Lnet/minecraft/client/resources/model/sprite/MaterialBaker;)Ljava/util/Map;");
    }

    public FluidModel get(FluidState state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/FluidStateModelSet.get:(Lnet/minecraft/world/level/material/FluidState;)Lnet/minecraft/client/renderer/block/FluidModel;");
    }

    protected FluidStateModelSet() {
    }
}
