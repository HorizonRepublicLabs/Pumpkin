package net.minecraft.core;

import dev.pumpkin.shim.Unimplemented;

public class SectionPos extends Vec3i {

    private SectionPos(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.<init>:(III)V");
    }

    public int x() {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.x:()I");
    }

    public int y() {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.y:()I");
    }

    public int z() {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.z:()I");
    }

    public BlockPos center() {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.center:()Lnet/minecraft/core/BlockPos;");
    }

    public SectionPos() {
    }
}
