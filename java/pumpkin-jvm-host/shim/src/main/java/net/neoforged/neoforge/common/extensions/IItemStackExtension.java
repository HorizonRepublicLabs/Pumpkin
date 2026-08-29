package net.neoforged.neoforge.common.extensions;

import net.neoforged.neoforge.capabilities.ItemCapability;
import dev.pumpkin.shim.Unimplemented;

public interface IItemStackExtension extends ItemInstanceExtension {

    default <T, C extends Object> T getCapability(ItemCapability<T, C> capability, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemStackExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/ItemCapability;Ljava/lang/Object;)Ljava/lang/Object;");
    }
}
