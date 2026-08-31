package net.minecraft.core;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.entity.EntityAccess;
import dev.pumpkin.shim.Unimplemented;

public class SectionPos extends Vec3i {

    private SectionPos(int x, int y, int z) {
    }

    public static SectionPos of(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.of:(III)Lnet/minecraft/core/SectionPos;");
    }

    public static SectionPos of(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.of:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/SectionPos;");
    }

    public static SectionPos of(ChunkPos pos, int sectionY) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.of:(Lnet/minecraft/world/level/ChunkPos;I)Lnet/minecraft/core/SectionPos;");
    }

    public static SectionPos of(EntityAccess entity) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.of:(Lnet/minecraft/world/level/entity/EntityAccess;)Lnet/minecraft/core/SectionPos;");
    }

    public static SectionPos of(Position pos) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.of:(Lnet/minecraft/core/Position;)Lnet/minecraft/core/SectionPos;");
    }

    public static SectionPos of(long sectionNode) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.of:(J)Lnet/minecraft/core/SectionPos;");
    }

    public static int blockToSectionCoord(int blockCoord) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.blockToSectionCoord:(I)I");
    }

    public static int blockToSectionCoord(double coord) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.blockToSectionCoord:(D)I");
    }

    public static int sectionRelative(int blockCoord) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.sectionRelative:(I)I");
    }

    public static int sectionToBlockCoord(int sectionCoord) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.sectionToBlockCoord:(I)I");
    }

    public static int sectionToBlockCoord(int sectionCoord, int offset) {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.sectionToBlockCoord:(II)I");
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

    public BlockPos origin() {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.origin:()Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos center() {
        throw Unimplemented.forMember("net/minecraft/core/SectionPos.center:()Lnet/minecraft/core/BlockPos;");
    }

    public SectionPos() {
    }
}
