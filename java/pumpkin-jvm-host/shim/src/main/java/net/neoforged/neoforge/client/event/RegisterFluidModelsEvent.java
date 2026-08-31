package net.neoforged.neoforge.client.event;

import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.MaterialBaker;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public final class RegisterFluidModelsEvent extends Event implements IModBusEvent {

    public RegisterFluidModelsEvent(Map<Fluid, FluidModel> models, MaterialBaker materials) {
    }

    public void register(FluidModel.Unbaked model, Supplier<? extends Fluid> fluid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterFluidModelsEvent.register:(Lnet/minecraft/client/renderer/block/FluidModel$Unbaked;Ljava/util/function/Supplier;)V");
    }

    public void register(FluidModel.Unbaked model, Fluid fluid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterFluidModelsEvent.register:(Lnet/minecraft/client/renderer/block/FluidModel$Unbaked;Lnet/minecraft/world/level/material/Fluid;)V");
    }

    public void register(FluidModel.Unbaked model, Supplier<? extends Fluid> stillFluid, Supplier<? extends Fluid> flowingFluid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterFluidModelsEvent.register:(Lnet/minecraft/client/renderer/block/FluidModel$Unbaked;Ljava/util/function/Supplier;Ljava/util/function/Supplier;)V");
    }

    public void register(FluidModel.Unbaked model, Fluid stillFluid, Fluid flowingFluid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterFluidModelsEvent.register:(Lnet/minecraft/client/renderer/block/FluidModel$Unbaked;Lnet/minecraft/world/level/material/Fluid;Lnet/minecraft/world/level/material/Fluid;)V");
    }

    private void register(Fluid fluid, FluidModel model) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterFluidModelsEvent.register:(Lnet/minecraft/world/level/material/Fluid;Lnet/minecraft/client/renderer/block/FluidModel;)V");
    }

    public RegisterFluidModelsEvent() {
    }
}
