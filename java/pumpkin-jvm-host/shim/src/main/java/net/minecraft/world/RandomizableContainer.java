package net.minecraft.world;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;

public interface RandomizableContainer extends Container {

    ResourceKey<LootTable> getLootTable();

    void setLootTable(final ResourceKey<LootTable> lootTable);

    long getLootTableSeed();

    void setLootTableSeed(final long lootTableSeed);

    BlockPos getBlockPos();

    Level getLevel();
}
