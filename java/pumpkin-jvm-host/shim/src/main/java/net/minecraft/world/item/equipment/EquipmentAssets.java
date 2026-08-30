package net.minecraft.world.item.equipment;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface EquipmentAssets {

    // Pumpkin divergence: real value, named as vanilla names it -- a registry key is a
    // pair of names, and ModEquipmentAssets dereferences this at class-init.
    ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID =
            net.minecraft.resources.ResourceKey.createRegistryKey(
                    net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "equipment_asset"));
}
