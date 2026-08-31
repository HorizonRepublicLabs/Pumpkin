package net.minecraft.world.item.equipment;

import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import dev.pumpkin.shim.Unimplemented;

public record ArmorMaterial(int durability, Map<ArmorType, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient, ResourceKey<EquipmentAsset> assetId) {

    public ItemAttributeModifiers createAttributes(ArmorType type) {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorMaterial.createAttributes:(Lnet/minecraft/world/item/equipment/ArmorType;)Lnet/minecraft/world/item/component/ItemAttributeModifiers;");
    }
}
