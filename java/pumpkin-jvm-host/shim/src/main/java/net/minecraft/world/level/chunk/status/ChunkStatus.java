package net.minecraft.world.level.chunk.status;

import java.util.EnumSet;
import net.minecraft.world.level.levelgen.Heightmap;
import dev.pumpkin.shim.Unimplemented;

public class ChunkStatus {

    public static final ChunkStatus FULL = null;

    protected ChunkStatus(ChunkStatus parent, EnumSet<Heightmap.Types> heightmapsAfter, ChunkType chunkType) {
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
