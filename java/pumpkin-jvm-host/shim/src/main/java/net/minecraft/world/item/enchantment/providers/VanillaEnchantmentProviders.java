package net.minecraft.world.item.enchantment.providers;

import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public interface VanillaEnchantmentProviders {

    ResourceKey<EnchantmentProvider> MOB_SPAWN_EQUIPMENT = null;

    static ResourceKey<EnchantmentProvider> create(String id) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/providers/VanillaEnchantmentProviders.create:(Ljava/lang/String;)Lnet/minecraft/resources/ResourceKey;");
    }
}
