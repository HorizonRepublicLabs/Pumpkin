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

    // Pumpkin divergence: the built component is declared metadata the Rust side does
    // not consume; the mod only needs the call to complete while registering items.
    public ItemAttributeModifiers createAttributes(ArmorType type) {
        return ItemAttributeModifiers.builder().build();
    }
}
