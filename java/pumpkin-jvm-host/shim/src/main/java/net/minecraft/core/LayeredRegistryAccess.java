package net.minecraft.core;

import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public class LayeredRegistryAccess<T> {

    public LayeredRegistryAccess(List<T> keys) {
        throw Unimplemented.forMember("net/minecraft/core/LayeredRegistryAccess.<init>:(Ljava/util/List;)V");
    }

    private LayeredRegistryAccess(List<T> keys, List<RegistryAccess.Frozen> values) {
        throw Unimplemented.forMember("net/minecraft/core/LayeredRegistryAccess.<init>:(Ljava/util/List;Ljava/util/List;)V");
    }

    protected LayeredRegistryAccess() {
    }
}
