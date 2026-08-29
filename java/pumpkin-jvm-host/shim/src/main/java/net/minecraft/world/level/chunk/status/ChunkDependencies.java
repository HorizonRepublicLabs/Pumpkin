package net.minecraft.world.level.chunk.status;

import com.google.common.collect.ImmutableList;
import dev.pumpkin.shim.Unimplemented;

public final class ChunkDependencies {

    public ChunkDependencies(ImmutableList<ChunkStatus> dependencyByRadius) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkDependencies.<init>:(Lcom/google/common/collect/ImmutableList;)V");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkDependencies.size:()I");
    }

    public ChunkStatus get(int distance) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkDependencies.get:(I)Lnet/minecraft/world/level/chunk/status/ChunkStatus;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/status/ChunkDependencies.toString:()Ljava/lang/String;");
    }

    protected ChunkDependencies() {
    }
}
