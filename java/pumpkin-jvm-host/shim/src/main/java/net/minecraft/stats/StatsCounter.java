package net.minecraft.stats;

import dev.pumpkin.shim.Unimplemented;

public class StatsCounter {

    public StatsCounter() {
    }

    public <T> int getValue(StatType<T> type, T key) {
        throw Unimplemented.forMember("net/minecraft/stats/StatsCounter.getValue:(Lnet/minecraft/stats/StatType;Ljava/lang/Object;)I");
    }

    public int getValue(Stat<?> stat) {
        throw Unimplemented.forMember("net/minecraft/stats/StatsCounter.getValue:(Lnet/minecraft/stats/Stat;)I");
    }
}
