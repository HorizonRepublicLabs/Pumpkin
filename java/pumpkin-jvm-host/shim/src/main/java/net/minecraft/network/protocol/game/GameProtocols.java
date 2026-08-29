package net.minecraft.network.protocol.game;

import dev.pumpkin.shim.Unimplemented;

public class GameProtocols {

    public interface Context {

        boolean hasInfiniteMaterials();
    }

    protected GameProtocols() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/GameProtocols");
        }
    }
}
