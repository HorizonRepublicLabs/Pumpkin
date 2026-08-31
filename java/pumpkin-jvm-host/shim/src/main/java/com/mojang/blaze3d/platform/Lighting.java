package com.mojang.blaze3d.platform;

import dev.pumpkin.shim.Unimplemented;

public class Lighting implements AutoCloseable {

    public Lighting() {
    }

    public void setupFor(Lighting.Entry entry) {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Lighting.setupFor:(Lcom/mojang/blaze3d/platform/Lighting$Entry;)V");
    }

    public void close() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Lighting.close:()V");
    }

    public enum Entry {

        LEVEL, ITEMS_FLAT, ITEMS_3D, ENTITY_IN_UI, PLAYER_SKIN
    }
}
