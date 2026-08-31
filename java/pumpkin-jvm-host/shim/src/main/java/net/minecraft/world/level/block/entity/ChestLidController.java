package net.minecraft.world.level.block.entity;

import dev.pumpkin.shim.Unimplemented;

public class ChestLidController {

    private boolean shouldBeOpen;

    public void tickLid() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/ChestLidController.tickLid:()V");
    }

    public float getOpenness(float a) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/ChestLidController.getOpenness:(F)F");
    }

    public void shouldBeOpen(boolean shouldBeOpen) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/ChestLidController.shouldBeOpen:(Z)V");
    }

    public ChestLidController() {
    }
}
