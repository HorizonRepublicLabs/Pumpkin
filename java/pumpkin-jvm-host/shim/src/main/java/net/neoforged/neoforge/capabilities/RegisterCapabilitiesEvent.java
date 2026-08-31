package net.neoforged.neoforge.capabilities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterCapabilitiesEvent extends Event implements IModBusEvent {

    RegisterCapabilitiesEvent() {
    }

    public <T, C extends Object> void registerBlock(BlockCapability<T, C> capability, IBlockCapabilityProvider<T, C> provider, Block... blocks) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/RegisterCapabilitiesEvent.registerBlock:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/neoforged/neoforge/capabilities/IBlockCapabilityProvider;[Lnet/minecraft/world/level/block/Block;)V");
    }

    public <T, C extends Object, BE extends BlockEntity> void registerBlockEntity(BlockCapability<T, C> capability, BlockEntityType<BE> blockEntityType, ICapabilityProvider<? super BE, C, T> provider) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/RegisterCapabilitiesEvent.registerBlockEntity:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/neoforged/neoforge/capabilities/ICapabilityProvider;)V");
    }

    public void setProxyable(BlockCapability<?, ?> capability) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/RegisterCapabilitiesEvent.setProxyable:(Lnet/neoforged/neoforge/capabilities/BlockCapability;)V");
    }

    public <T, C extends Object, E extends Entity> void registerEntity(EntityCapability<T, C> capability, EntityType<E> entityType, ICapabilityProvider<? super E, C, T> provider) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/RegisterCapabilitiesEvent.registerEntity:(Lnet/neoforged/neoforge/capabilities/EntityCapability;Lnet/minecraft/world/entity/EntityType;Lnet/neoforged/neoforge/capabilities/ICapabilityProvider;)V");
    }

    public <T, C extends Object> void registerItem(ItemCapability<T, C> capability, ICapabilityProvider<ItemStack, C, T> provider, ItemLike... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/RegisterCapabilitiesEvent.registerItem:(Lnet/neoforged/neoforge/capabilities/ItemCapability;Lnet/neoforged/neoforge/capabilities/ICapabilityProvider;[Lnet/minecraft/world/level/ItemLike;)V");
    }
}
