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

    // Pumpkin divergence: real bodies -- providers land in PumpkinCapabilities, and
    // the level/item lookups consult them. Public ctor: the host posts the event.
    public RegisterCapabilitiesEvent() {
    }

    public <T, C extends Object> void registerBlock(BlockCapability<T, C> capability, IBlockCapabilityProvider<T, C> provider, Block... blocks) {
        for (Block block : blocks) {
            dev.pumpkin.bridge.PumpkinCapabilities.put(
                    dev.pumpkin.bridge.PumpkinCapabilities.BLOCK, capability, block, provider);
        }
    }

    public <T, C extends Object, BE extends BlockEntity> void registerBlockEntity(BlockCapability<T, C> capability, BlockEntityType<BE> blockEntityType, ICapabilityProvider<? super BE, C, T> provider) {
        dev.pumpkin.bridge.PumpkinCapabilities.put(
                dev.pumpkin.bridge.PumpkinCapabilities.BLOCK_ENTITY, capability, blockEntityType, provider);
    }

    // Proxying (capability answered through a covering block) is a lookup refinement
    // Pumpkin's flat lookup does not model; accepting the declaration loses nothing.
    public void setProxyable(BlockCapability<?, ?> capability) {
    }

    public <T, C extends Object, E extends Entity> void registerEntity(EntityCapability<T, C> capability, EntityType<E> entityType, ICapabilityProvider<? super E, C, T> provider) {
        dev.pumpkin.bridge.PumpkinCapabilities.put(
                dev.pumpkin.bridge.PumpkinCapabilities.ENTITY, capability, entityType, provider);
    }

    public <T, C extends Object> void registerItem(ItemCapability<T, C> capability, ICapabilityProvider<ItemStack, C, T> provider, ItemLike... items) {
        for (ItemLike item : items) {
            dev.pumpkin.bridge.PumpkinCapabilities.put(
                    dev.pumpkin.bridge.PumpkinCapabilities.ITEM, capability, item.asItem(), provider);
        }
    }
}
