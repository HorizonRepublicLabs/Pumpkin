package net.minecraft.server.level;

import java.util.Set;
import dev.pumpkin.shim.Unimplemented;

public final class PlayerMap {

    // Pumpkin divergence: real body over a real (and forever empty) store -- the
    // bridge tracks no ServerPlayer instances, so nobody is ever watching a chunk
    // from the JVM's point of view.
    private final Set<ServerPlayer> pumpkinPlayers = new java.util.HashSet<>();

    public Set<ServerPlayer> getAllPlayers() {
        return pumpkinPlayers;
    }

    public PlayerMap() {
    }
}
