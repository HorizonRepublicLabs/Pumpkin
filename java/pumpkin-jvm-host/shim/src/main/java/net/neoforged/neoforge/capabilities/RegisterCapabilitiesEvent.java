package net.neoforged.neoforge.capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterCapabilitiesEvent extends Event implements IModBusEvent {

    RegisterCapabilitiesEvent() {
    }

    public <T, C extends Object, BE extends BlockEntity> void registerBlockEntity(BlockCapability<T, C> capability, BlockEntityType<BE> blockEntityType, ICapabilityProvider<? super BE, C, T> provider) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/RegisterCapabilitiesEvent.registerBlockEntity:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/neoforged/neoforge/capabilities/ICapabilityProvider;)V");
    }
}
