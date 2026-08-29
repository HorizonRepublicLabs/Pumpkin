package net.minecraft.world.inventory;

import dev.pumpkin.shim.Unimplemented;

public enum ContainerInput {

    PICKUP,
    QUICK_MOVE,
    SWAP,
    CLONE,
    THROW,
    QUICK_CRAFT,
    PICKUP_ALL;

    public int id() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ContainerInput.id:()I");
    }
}
