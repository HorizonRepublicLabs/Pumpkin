package net.minecraft.world.level.border;

import dev.pumpkin.shim.Unimplemented;

public enum BorderStatus {

    GROWING, SHRINKING, STATIONARY;

    public int getColor() {
        throw Unimplemented.forMember("net/minecraft/world/level/border/BorderStatus.getColor:()I");
    }
}
