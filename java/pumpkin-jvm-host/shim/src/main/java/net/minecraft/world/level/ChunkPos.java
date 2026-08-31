package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public record ChunkPos(int x, int z) {

    public static final long INVALID_CHUNK_POS = 0L;

    public static ChunkPos containing(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.containing:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/ChunkPos;");
    }

    public static ChunkPos unpack(long key) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.unpack:(J)Lnet/minecraft/world/level/ChunkPos;");
    }

    public boolean isValid() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.isValid:()Z");
    }

    public static boolean isValid(int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.isValid:(II)Z");
    }

    public long pack() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.pack:()J");
    }

    public static long pack(int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.pack:(II)J");
    }

    public static long pack(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.pack:(Lnet/minecraft/core/BlockPos;)J");
    }

    public static int getX(long pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.getX:(J)I");
    }

    public static int getZ(long pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.getZ:(J)I");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.hashCode:()I");
    }

    public int getMinBlockX() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.getMinBlockX:()I");
    }

    public int getMinBlockZ() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.getMinBlockZ:()I");
    }

    public int getMaxBlockX() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.getMaxBlockX:()I");
    }

    public int getMaxBlockZ() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.getMaxBlockZ:()I");
    }

    public boolean contains(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.contains:(Lnet/minecraft/core/BlockPos;)Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.toString:()Ljava/lang/String;");
    }

    public int distanceSquared(ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.distanceSquared:(Lnet/minecraft/world/level/ChunkPos;)I");
    }

    public int distanceSquared(long pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.distanceSquared:(J)I");
    }
}
