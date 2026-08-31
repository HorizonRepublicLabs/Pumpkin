package net.minecraft.world.entity;

import java.util.Map;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public record EquipmentTable(ResourceKey<LootTable> lootTable, Map<EquipmentSlot, Float> slotDropChances) {

    public EquipmentTable(ResourceKey<LootTable> lootTable, float dropChance) {
        this((ResourceKey<LootTable>) null, (Map<EquipmentSlot, Float>) null);
    }
}
