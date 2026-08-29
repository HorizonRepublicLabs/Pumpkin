package net.neoforged.neoforge.transfer;

import net.neoforged.neoforge.transfer.resource.Resource;

public interface IndexModifier<T extends Resource> {

    void set(int index, T resource, int amount);
}
