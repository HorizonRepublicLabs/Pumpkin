package net.minecraft.stats;

import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import dev.pumpkin.shim.Unimplemented;

public class Stat<T> extends ObjectiveCriteria {

    protected Stat(StatType<T> type, T value, StatFormatter formatter) {
        throw Unimplemented.forMember("net/minecraft/stats/Stat.<init>:(Lnet/minecraft/stats/StatType;Ljava/lang/Object;Lnet/minecraft/stats/StatFormatter;)V");
    }

    public StatType<T> getType() {
        throw Unimplemented.forMember("net/minecraft/stats/Stat.getType:()Lnet/minecraft/stats/StatType;");
    }

    public String format(int value) {
        throw Unimplemented.forMember("net/minecraft/stats/Stat.format:(I)Ljava/lang/String;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/stats/Stat.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/stats/Stat.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/stats/Stat.toString:()Ljava/lang/String;");
    }

    protected Stat() {
    }
}
