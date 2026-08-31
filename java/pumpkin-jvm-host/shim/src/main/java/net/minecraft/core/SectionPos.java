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

    // Pumpkin divergence: vanilla bodies -- section coordinate arithmetic.
    public static int blockToSectionCoord(int blockCoord) {
        return blockCoord >> 4;
    }

    public static int blockToSectionCoord(double coord) {
        return blockToSectionCoord((int) Math.floor(coord));
    }

    public static int sectionRelative(int blockCoord) {
        return blockCoord & 15;
    }

    public static int sectionToBlockCoord(int sectionCoord) {
        return sectionCoord << 4;
    }

    public static int sectionToBlockCoord(int sectionCoord, int offset) {
        return sectionToBlockCoord(sectionCoord) + offset;
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
