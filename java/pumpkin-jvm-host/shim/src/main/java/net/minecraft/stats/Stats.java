package net.minecraft.stats;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import dev.pumpkin.shim.Unimplemented;

public class Stats {

    public static final StatType<Item> ITEM_CRAFTED = null;

    public static final StatType<Item> ITEM_USED = null;

    public static final StatType<Identifier> CUSTOM = null;

    public static final Identifier DAMAGE_BLOCKED_BY_SHIELD = null;

    public static final Identifier FILL_CAULDRON = null;

    public static final Identifier USE_CAULDRON = null;

    public static final Identifier OPEN_CHEST = null;

    public static final Identifier OPEN_BARREL = null;

    public Stats() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/stats/Stats");
        }
    }
}
