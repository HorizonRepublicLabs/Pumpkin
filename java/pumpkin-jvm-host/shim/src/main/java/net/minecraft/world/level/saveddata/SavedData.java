package net.minecraft.world.level.saveddata;

import dev.pumpkin.shim.Unimplemented;

public abstract class SavedData {

    public void setDirty() {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/SavedData.setDirty:()V");
    }

    public void setDirty(boolean dirty) {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/SavedData.setDirty:(Z)V");
    }

    public SavedData() {
    }
}
