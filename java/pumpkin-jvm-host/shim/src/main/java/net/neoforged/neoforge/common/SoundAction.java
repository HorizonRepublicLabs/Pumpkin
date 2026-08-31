package net.neoforged.neoforge.common;

import dev.pumpkin.shim.Unimplemented;

public final class SoundAction {

    // Pumpkin divergence: real, interned by name -- NeoForge's own contract.
    private static final java.util.concurrent.ConcurrentHashMap<String, SoundAction> PUMPKIN_INTERNED =
            new java.util.concurrent.ConcurrentHashMap<>();

    private String pumpkinName;

    public static SoundAction get(String name) {
        SoundAction action = PUMPKIN_INTERNED.computeIfAbsent(name, key -> new SoundAction());
        action.pumpkinName = name;
        return action;
    }

    private SoundAction(final String name) {
    }

    public String name() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/SoundAction.name:()Ljava/lang/String;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/SoundAction.toString:()Ljava/lang/String;");
    }

    public SoundAction() {
    }
}
