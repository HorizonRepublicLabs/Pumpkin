package net.minecraft.world.inventory;

import dev.pumpkin.shim.Unimplemented;

public class SimpleContainerData implements ContainerData {

    public SimpleContainerData(int count) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/SimpleContainerData.<init>:(I)V");
    }

    public int get(int dataId) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/SimpleContainerData.get:(I)I");
    }

    public void set(int dataId, int value) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/SimpleContainerData.set:(II)V");
    }

    public int getCount() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/SimpleContainerData.getCount:()I");
    }

    protected SimpleContainerData() {
    }
}
