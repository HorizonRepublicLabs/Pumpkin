package net.minecraft.world.scores;

import net.minecraft.network.chat.numbers.NumberFormat;

public interface ReadOnlyScoreInfo {

    int value();

    boolean isLocked();

    NumberFormat numberFormat();
}
