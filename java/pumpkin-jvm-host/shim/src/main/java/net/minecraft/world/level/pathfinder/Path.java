package net.minecraft.world.level.pathfinder;

import java.util.List;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import dev.pumpkin.shim.Unimplemented;

public final class Path {

    public Path(List<Node> nodes, BlockPos target, boolean reached) {
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/Path.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/Path.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/Path.toString:()Ljava/lang/String;");
    }

    public BlockPos getTarget() {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/Path.getTarget:()Lnet/minecraft/core/BlockPos;");
    }

    public Path copy() {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/Path.copy:()Lnet/minecraft/world/level/pathfinder/Path;");
    }

    public record DebugData(Node[] openSet, Node[] closedSet, Set<Target> targetNodes) {

        public void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/Path$DebugData.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public static Path.DebugData read(FriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/Path$DebugData.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/world/level/pathfinder/Path$DebugData;");
        }
    }

    public Path() {
    }
}
