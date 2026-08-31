package net.minecraft.server.level;

import net.minecraft.world.level.ChunkPos;
import dev.pumpkin.shim.Unimplemented;

public record ColumnPos(int x, int z) {

    public ChunkPos toChunkPos() {
        throw Unimplemented.forMember("net/minecraft/server/level/ColumnPos.toChunkPos:()Lnet/minecraft/world/level/ChunkPos;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/server/level/ColumnPos.toString:()Ljava/lang/String;");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/server/level/ColumnPos.hashCode:()I");
    }
}
