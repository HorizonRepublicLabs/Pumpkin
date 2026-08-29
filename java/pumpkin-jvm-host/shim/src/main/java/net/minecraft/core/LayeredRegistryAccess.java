package net.minecraft.core;

import java.util.List;

public class LayeredRegistryAccess<T> {

    public LayeredRegistryAccess(List<T> keys) {
    }

    private LayeredRegistryAccess(List<T> keys, List<RegistryAccess.Frozen> values) {
    }

    public LayeredRegistryAccess() {
    }
}
