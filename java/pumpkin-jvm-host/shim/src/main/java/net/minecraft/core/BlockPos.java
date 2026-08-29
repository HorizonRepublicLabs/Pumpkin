package net.minecraft.core;

import java.util.stream.Stream;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import dev.pumpkin.shim.Unimplemented;

public class BlockPos extends Vec3i {

    public BlockPos(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.<init>:(III)V");
    }

    public BlockPos(Vec3i vec3i) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.<init>:(Lnet/minecraft/core/Vec3i;)V");
    }

    public static long offset(long blockNode, Direction offset) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.offset:(JLnet/minecraft/core/Direction;)J");
    }

    public static long offset(long blockNode, int stepX, int stepY, int stepZ) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.offset:(JIII)J");
    }

    public static int getX(long blockNode) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.getX:(J)I");
    }

    public static int getY(long blockNode) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.getY:(J)I");
    }

    public static int getZ(long blockNode) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.getZ:(J)I");
    }

    public static BlockPos containing(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.containing:(DDD)Lnet/minecraft/core/BlockPos;");
    }

    public static BlockPos containing(Position pos) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.containing:(Lnet/minecraft/core/Position;)Lnet/minecraft/core/BlockPos;");
    }

    public long asLong() {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.asLong:()J");
    }

    public static long asLong(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.asLong:(III)J");
    }

    public BlockPos offset(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.offset:(III)Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos offset(Vec3i vec) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.offset:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos above() {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.above:()Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos above(int steps) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.above:(I)Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos below() {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.below:()Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos below(int steps) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.below:(I)Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos relative(Direction direction) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.relative:(Lnet/minecraft/core/Direction;)Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos relative(Direction direction, int steps) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.relative:(Lnet/minecraft/core/Direction;I)Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos relative(Direction.Axis axis, int steps) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.relative:(Lnet/minecraft/core/Direction$Axis;I)Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos immutable() {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.immutable:()Lnet/minecraft/core/BlockPos;");
    }

    public static Iterable<BlockPos> betweenClosed(AABB box) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.betweenClosed:(Lnet/minecraft/world/phys/AABB;)Ljava/lang/Iterable;");
    }

    public static Iterable<BlockPos> betweenClosed(BlockPos a, BlockPos b) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.betweenClosed:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Ljava/lang/Iterable;");
    }

    public static Stream<BlockPos> betweenClosedStream(BlockPos a, BlockPos b) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.betweenClosedStream:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Ljava/util/stream/Stream;");
    }

    public static Stream<BlockPos> betweenClosedStream(BoundingBox boundingBox) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.betweenClosedStream:(Lnet/minecraft/world/level/levelgen/structure/BoundingBox;)Ljava/util/stream/Stream;");
    }

    public static Stream<BlockPos> betweenClosedStream(AABB box) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.betweenClosedStream:(Lnet/minecraft/world/phys/AABB;)Ljava/util/stream/Stream;");
    }

    public static Stream<BlockPos> betweenClosedStream(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.betweenClosedStream:(IIIIII)Ljava/util/stream/Stream;");
    }

    public static Iterable<BlockPos> betweenClosed(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.betweenClosed:(IIIIII)Ljava/lang/Iterable;");
    }

    public static class MutableBlockPos extends BlockPos {

        public MutableBlockPos() {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.<init>:()V");
        }

        public MutableBlockPos(int x, int y, int z) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.<init>:(III)V");
        }

        public MutableBlockPos(double x, double y, double z) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.<init>:(DDD)V");
        }

        public BlockPos offset(int x, int y, int z) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.offset:(III)Lnet/minecraft/core/BlockPos;");
        }

        public BlockPos multiply(int scale) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.multiply:(I)Lnet/minecraft/core/BlockPos;");
        }

        public BlockPos relative(Direction direction, int steps) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.relative:(Lnet/minecraft/core/Direction;I)Lnet/minecraft/core/BlockPos;");
        }

        public BlockPos relative(Direction.Axis axis, int steps) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.relative:(Lnet/minecraft/core/Direction$Axis;I)Lnet/minecraft/core/BlockPos;");
        }

        public BlockPos rotate(Rotation rotation) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.rotate:(Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/core/BlockPos;");
        }

        public BlockPos.MutableBlockPos set(int x, int y, int z) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.set:(III)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos set(double x, double y, double z) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.set:(DDD)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos set(Vec3i vec) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.set:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos set(long pos) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.set:(J)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos set(AxisCycle transform, int x, int y, int z) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.set:(Lnet/minecraft/core/AxisCycle;III)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos setX(int x) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.setX:(I)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos setY(int y) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.setY:(I)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos immutable() {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.immutable:()Lnet/minecraft/core/BlockPos;");
        }
    }

    public enum TraversalNodeStatus {

        ACCEPT, SKIP, STOP
    }

    protected BlockPos() {
    }
}
