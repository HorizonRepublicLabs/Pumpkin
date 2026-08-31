package net.neoforged.neoforge.client.extensions;

import dev.pumpkin.shim.Unimplemented;

public interface ModelStateExtension {

    default boolean mayApplyArbitraryRotation() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/ModelStateExtension.mayApplyArbitraryRotation:()Z");
    }
}
