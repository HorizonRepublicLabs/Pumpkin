package net.neoforged.neoforge.client.extensions.common;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.fluids.FluidType;
import dev.pumpkin.shim.Unimplemented;

public final class RegisterClientExtensionsEvent extends Event implements IModBusEvent {

    RegisterClientExtensionsEvent() {
    }

    public void registerBlock(IClientBlockExtensions extensions, Block... blocks) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/RegisterClientExtensionsEvent.registerBlock:(Lnet/neoforged/neoforge/client/extensions/common/IClientBlockExtensions;[Lnet/minecraft/world/level/block/Block;)V");
    }

    public final void registerBlock(IClientBlockExtensions extensions, Holder<Block>... blocks) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/RegisterClientExtensionsEvent.registerBlock:(Lnet/neoforged/neoforge/client/extensions/common/IClientBlockExtensions;[Lnet/minecraft/core/Holder;)V");
    }

    public void registerItem(IClientItemExtensions extensions, Item... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/RegisterClientExtensionsEvent.registerItem:(Lnet/neoforged/neoforge/client/extensions/common/IClientItemExtensions;[Lnet/minecraft/world/item/Item;)V");
    }

    public final void registerItem(IClientItemExtensions extensions, Holder<Item>... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/RegisterClientExtensionsEvent.registerItem:(Lnet/neoforged/neoforge/client/extensions/common/IClientItemExtensions;[Lnet/minecraft/core/Holder;)V");
    }

    public void registerFluidType(IClientFluidTypeExtensions extensions, FluidType... fluidTypes) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/RegisterClientExtensionsEvent.registerFluidType:(Lnet/neoforged/neoforge/client/extensions/common/IClientFluidTypeExtensions;[Lnet/neoforged/neoforge/fluids/FluidType;)V");
    }

    public final void registerFluidType(IClientFluidTypeExtensions extensions, Holder<FluidType>... fluidTypes) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/common/RegisterClientExtensionsEvent.registerFluidType:(Lnet/neoforged/neoforge/client/extensions/common/IClientFluidTypeExtensions;[Lnet/minecraft/core/Holder;)V");
    }
}
