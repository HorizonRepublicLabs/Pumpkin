package net.minecraft.world.level.chunk.status;

import java.util.EnumSet;
import net.minecraft.world.level.levelgen.Heightmap;
import dev.pumpkin.shim.Unimplemented;

public class ChunkStatus {

    protected ChunkStatus(ChunkStatus parent, EnumSet<Heightmap.Types> heightmapsAfter, ChunkType chunkType) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkStatus.<init>:(Lnet/minecraft/world/level/chunk/status/ChunkStatus;Ljava/util/EnumSet;Lnet/minecraft/world/level/chunk/status/ChunkType;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkStatus.toString:()Ljava/lang/String;");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkStatus.getName:()Ljava/lang/String;");
    }

    public ChunkStatus() {
    }
}
