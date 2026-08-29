package net.minecraft.stats;

import net.minecraft.world.item.Item;
import dev.pumpkin.shim.Unimplemented;

public class Stats {

    public static final StatType<Item> ITEM_USED = null;

    protected Stats() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/stats/Stats");
        }
    }
}
