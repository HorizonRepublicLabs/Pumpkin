package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public record ChunkPos(int x, int z) {

    public static ChunkPos unpack(long key) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.unpack:(J)Lnet/minecraft/world/level/ChunkPos;");
    }

    public boolean isValid() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.isValid:()Z");
    }

    public static boolean isValid(int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.isValid:(II)Z");
    }

    public static long pack(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.pack:(Lnet/minecraft/core/BlockPos;)J");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/ChunkPos.hashCode:()I");
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
