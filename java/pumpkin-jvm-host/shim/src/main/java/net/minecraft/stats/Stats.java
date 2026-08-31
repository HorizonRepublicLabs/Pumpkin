package net.minecraft.stats;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class Stats {

    // Pumpkin divergence: StatType instances are inert stand-ins (every method throws
    // by member key); the Identifier constants are the real vanilla stat names.
    public static final StatType<Item> ITEM_CRAFTED = new StatType<>();

    public static final StatType<Item> ITEM_USED = new StatType<>();

    public static final StatType<Identifier> CUSTOM = new StatType<>();

    public static final Identifier DAMAGE_BLOCKED_BY_SHIELD = Identifier.withDefaultNamespace("damage_blocked_by_shield");

    public static final Identifier FILL_CAULDRON = Identifier.withDefaultNamespace("fill_cauldron");

    public static final Identifier USE_CAULDRON = Identifier.withDefaultNamespace("use_cauldron");

    public static final Identifier OPEN_CHEST = Identifier.withDefaultNamespace("open_chest");

    public static final Identifier OPEN_BARREL = Identifier.withDefaultNamespace("open_barrel");

    public Stats() {
    }
}
