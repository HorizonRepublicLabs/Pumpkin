package net.minecraft.world.item.equipment.trim;

import java.util.Map;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import dev.pumpkin.shim.Unimplemented;

public record MaterialAssetGroup(MaterialAssetGroup.AssetInfo base, Map<ResourceKey<EquipmentAsset>, MaterialAssetGroup.AssetInfo> overrides) {

    public static MaterialAssetGroup create(String base) {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/trim/MaterialAssetGroup.create:(Ljava/lang/String;)Lnet/minecraft/world/item/equipment/trim/MaterialAssetGroup;");
    }

    public static MaterialAssetGroup create(String base, Map<ResourceKey<EquipmentAsset>, String> overrides) {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/trim/MaterialAssetGroup.create:(Ljava/lang/String;Ljava/util/Map;)Lnet/minecraft/world/item/equipment/trim/MaterialAssetGroup;");
    }

    public record AssetInfo(String suffix) {
    }
}
