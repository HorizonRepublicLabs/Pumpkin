package net.neoforged.neoforge.common;

import dev.pumpkin.shim.Unimplemented;

public final class ItemAbility {

    // Pumpkin divergence: real, interned by name -- NeoForge's own contract, and mods
    // compare abilities by identity.
    private static final java.util.concurrent.ConcurrentHashMap<String, ItemAbility> PUMPKIN_INTERNED =
            new java.util.concurrent.ConcurrentHashMap<>();

    public static ItemAbility get(String name) {
        return PUMPKIN_INTERNED.computeIfAbsent(name, ItemAbility::new);
    }

    public String name() {
        return pumpkinName;
    }

    public String toString() {
        return pumpkinName;
    }

    private String pumpkinName;

    private ItemAbility(String name) {
        this.pumpkinName = name;
    }

    public ItemAbility() {
    }
}
