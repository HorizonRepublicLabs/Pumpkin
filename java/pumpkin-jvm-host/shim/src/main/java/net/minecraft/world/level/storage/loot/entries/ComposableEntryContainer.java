package net.minecraft.world.level.storage.loot.entries;

import java.util.function.Consumer;
import net.minecraft.world.level.storage.loot.LootContext;

public interface ComposableEntryContainer {

    boolean expand(final LootContext context, final Consumer<LootPoolEntry> output);
}
