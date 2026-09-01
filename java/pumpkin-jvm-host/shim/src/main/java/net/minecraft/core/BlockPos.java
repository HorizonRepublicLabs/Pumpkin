package net.minecraft.core;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import java.util.stream.Stream;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class BlockPos extends Vec3i {

    public static final Codec<BlockPos> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.core.BlockPos.CODEC");

    public static final StreamCodec<ByteBuf, BlockPos> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public static final BlockPos ZERO = null;

    // Pumpkin divergence: coordinates flow into Vec3i's real fields.
    public BlockPos(int x, int y, int z) {
        super(x, y, z);
    }

    public BlockPos(Vec3i vec3i) {
        super(vec3i.getX(), vec3i.getY(), vec3i.getZ());
    }

    public static long offset(long blockNode, Direction offset) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.offset:(JLnet/minecraft/core/Direction;)J");
    }

    public static long offset(long blockNode, int stepX, int stepY, int stepZ) {
        return asLong(getX(blockNode) + stepX, getY(blockNode) + stepY, getZ(blockNode) + stepZ);
    }

    // Pumpkin divergence: the vanilla 26/12/26 bit layout, real math throughout.
    public static int getX(long blockNode) {
        return (int) (blockNode << 0 >> 38);
    }

    public static int getY(long blockNode) {
        return (int) (blockNode << 52 >> 52);
    }

    public static int getZ(long blockNode) {
        return (int) (blockNode << 26 >> 38);
    }

    public static BlockPos of(long blockNode) {
        return new BlockPos(getX(blockNode), getY(blockNode), getZ(blockNode));
    }

    // Pumpkin divergence: vanilla bodies -- floor each coordinate into the block grid.
    public static BlockPos containing(double x, double y, double z) {
        return new BlockPos((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    public static BlockPos containing(Position pos) {
        return containing(pos.x(), pos.y(), pos.z());
    }

    public long asLong() {
        return asLong(getX(), getY(), getZ());
    }

    public static long asLong(int x, int y, int z) {
        return ((long) (x & 0x3FFFFFF) << 38) | ((long) (z & 0x3FFFFFF) << 12) | (y & 0xFFF);
    }

    // Pumpkin divergence: vanilla bodies verbatim -- coordinate arithmetic, nothing else.
    public BlockPos offset(int x, int y, int z) {
        return new BlockPos(getX() + x, getY() + y, getZ() + z);
    }

    public BlockPos offset(Vec3i vec) {
        return offset(vec.getX(), vec.getY(), vec.getZ());
    }

    public BlockPos subtract(Vec3i vec) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.subtract:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos above() {
        return offset(0, 1, 0);
    }

    public BlockPos above(int steps) {
        return offset(0, steps, 0);
    }

    public BlockPos below() {
        return offset(0, -1, 0);
    }

    public BlockPos below(int steps) {
        return offset(0, -steps, 0);
    }

    public BlockPos north() {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.north:()Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos north(int steps) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.north:(I)Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos west() {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.west:()Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos west(int steps) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.west:(I)Lnet/minecraft/core/BlockPos;");
    }

    // Pumpkin divergence: vanilla bodies -- step along the direction's unit vector.
    public BlockPos relative(Direction direction) {
        return new BlockPos(getX() + direction.getStepX(), getY() + direction.getStepY(), getZ() + direction.getStepZ());
    }

    public BlockPos relative(Direction direction, int steps) {
        return steps == 0 ? this : new BlockPos(getX() + direction.getStepX() * steps, getY() + direction.getStepY() * steps, getZ() + direction.getStepZ() * steps);
    }

    public BlockPos relative(Direction.Axis axis, int steps) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.relative:(Lnet/minecraft/core/Direction$Axis;I)Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos immutable() {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.immutable:()Lnet/minecraft/core/BlockPos;");
    }

    public BlockPos.MutableBlockPos mutable() {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.mutable:()Lnet/minecraft/core/BlockPos$MutableBlockPos;");
    }

    public static Iterable<BlockPos> betweenClosed(AABB box) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.betweenClosed:(Lnet/minecraft/world/phys/AABB;)Ljava/lang/Iterable;");
    }

    public static Iterable<BlockPos> betweenClosed(BlockPos a, BlockPos b) {
        throw Unimplemented.forMember("net/minecraft/core/BlockPos.betweenClosed:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Ljava/lang/Iterable;");
    }

    // Pumpkin divergence: real body -- every position in the closed box, y rising first
    // so a column scan (the growth accelerator's use) finds the lowest match first.
    public static Stream<BlockPos> betweenClosedStream(BlockPos a, BlockPos b) {
        java.util.List<BlockPos> positions = new java.util.ArrayList<>();
        for (int y = Math.min(a.getY(), b.getY()); y <= Math.max(a.getY(), b.getY()); y++) {
            for (int x = Math.min(a.getX(), b.getX()); x <= Math.max(a.getX(), b.getX()); x++) {
                for (int z = Math.min(a.getZ(), b.getZ()); z <= Math.max(a.getZ(), b.getZ()); z++) {
                    positions.add(new BlockPos(x, y, z));
                }
            }
        }
        return positions.stream();
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
        }

        public MutableBlockPos(int x, int y, int z) {
            super(x, y, z);
        }

        public MutableBlockPos(double x, double y, double z) {
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

        // Pumpkin divergence: vanilla bodies -- write through the carried coordinates.
        public BlockPos.MutableBlockPos set(int x, int y, int z) {
            pumpkinSetAll(x, y, z);
            return this;
        }

        public BlockPos.MutableBlockPos set(double x, double y, double z) {
            return set((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
        }

        public BlockPos.MutableBlockPos set(Vec3i vec) {
            return set(vec.getX(), vec.getY(), vec.getZ());
        }

        public BlockPos.MutableBlockPos set(long pos) {
            return set(BlockPos.getX(pos), BlockPos.getY(pos), BlockPos.getZ(pos));
        }

        public BlockPos.MutableBlockPos set(AxisCycle transform, int x, int y, int z) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.set:(Lnet/minecraft/core/AxisCycle;III)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos setWithOffset(Vec3i pos, Direction direction) {
            return set(pos.getX() + direction.getStepX(), pos.getY() + direction.getStepY(),
                    pos.getZ() + direction.getStepZ());
        }

        public BlockPos.MutableBlockPos setWithOffset(Vec3i pos, int x, int y, int z) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.setWithOffset:(Lnet/minecraft/core/Vec3i;III)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos setWithOffset(Vec3i pos, Vec3i offset) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.setWithOffset:(Lnet/minecraft/core/Vec3i;Lnet/minecraft/core/Vec3i;)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos move(Direction direction) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.move:(Lnet/minecraft/core/Direction;)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos move(Direction direction, int steps) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.move:(Lnet/minecraft/core/Direction;I)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos move(int x, int y, int z) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.move:(III)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
        }

        public BlockPos.MutableBlockPos move(Vec3i pos) {
            throw Unimplemented.forMember("net/minecraft/core/BlockPos$MutableBlockPos.move:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/core/BlockPos$MutableBlockPos;");
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

    public BlockPos() {
    }
}
