package net.neoforged.fml.util.thread;

import net.neoforged.fml.LogicalSide;

/** The side the current thread is effectively on. Pumpkin is a dedicated server, and
 * every mod-facing thread here runs server logic -- the honest constant answer. */
public final class EffectiveSide {
    private EffectiveSide() {
    }

    public static LogicalSide get() {
        return LogicalSide.SERVER;
    }
}
