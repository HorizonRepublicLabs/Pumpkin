package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public record ChunkPos(int x, int z) {

    public static final long INVALID_CHUNK_POS = 0L;

    // Pumpkin divergence: real bodies for the pure coordinate maths below -- a chunk
    // position is its block position shifted by four, packed as two ints in a long,
    // exactly vanilla's arithmetic.
    public static ChunkPos containing(BlockPos pos) {
        return new ChunkPos(pos.getX() >> 4, pos.getZ() >> 4);
    }

    public static ChunkPos unpack(long key) {
        return new ChunkPos((int) key, (int) (key >> 32));
    }

    public boolean isValid() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.isValid:()Z");
    }

    public static boolean isValid(int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.isValid:(II)Z");
    }

    public long pack() {
        return pack(this.x, this.z);
    }

    public static long pack(int x, int z) {
        return (x & 0xFFFFFFFFL) | ((z & 0xFFFFFFFFL) << 32);
    }

    public static long pack(BlockPos pos) {
        return pack(pos.getX() >> 4, pos.getZ() >> 4);
    }

    public static int getX(long pos) {
        return (int) pos;
    }

    public static int getZ(long pos) {
        return (int) (pos >> 32);
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
