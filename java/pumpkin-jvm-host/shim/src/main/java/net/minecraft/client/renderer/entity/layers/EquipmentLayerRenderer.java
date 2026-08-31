package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.EquipmentAssetManager;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.ArmorTrim;

public class EquipmentLayerRenderer {

    public EquipmentLayerRenderer(EquipmentAssetManager equipmentAssets, TextureAtlas armorTrimAtlas) {
    }

    private record LayerTextureKey(EquipmentClientInfo.LayerType layerType, EquipmentClientInfo.Layer layer) {
    }

    private record TrimSpriteKey(ArmorTrim trim, EquipmentClientInfo.LayerType layerType, ResourceKey<EquipmentAsset> equipmentAssetId) {
    }

    public EquipmentLayerRenderer() {
    }
}
