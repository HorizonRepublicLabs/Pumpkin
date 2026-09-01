package net.minecraft.core;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class Vec3i implements Comparable<Vec3i> {

    public static final Codec<Vec3i> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.core.Vec3i.CODEC");

    public static final StreamCodec<ByteBuf, Vec3i> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public static final Vec3i ZERO = null;

    // Pumpkin divergence: a vector really carries its coordinates, like Vec3.
    private int pumpkinX;

    private int pumpkinY;

    private int pumpkinZ;

    public Vec3i(int x, int y, int z) {
        this.pumpkinX = x;
        this.pumpkinY = y;
        this.pumpkinZ = z;
    }

    // Pumpkin divergence: no vanilla counterpart -- MutableBlockPos writes through this.
    void pumpkinSetAll(int x, int y, int z) {
        this.pumpkinX = x;
        this.pumpkinY = y;
        this.pumpkinZ = z;
    }

    // Pumpkin divergence: real value semantics over the carried coordinates.
    public boolean equals(Object o) {
        return o instanceof Vec3i other && other.pumpkinX == pumpkinX
                && other.pumpkinY == pumpkinY && other.pumpkinZ == pumpkinZ;
    }

    public int hashCode() {
        return (pumpkinY + pumpkinZ * 31) * 31 + pumpkinX;
    }

    public int compareTo(Vec3i pos) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.compareTo:(Lnet/minecraft/core/Vec3i;)I");
    }

    public int getX() {
        return pumpkinX;
    }

    public int getY() {
        return pumpkinY;
    }

    public int getZ() {
        return pumpkinZ;
    }

    protected Vec3i setX(int x) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.setX:(I)Lnet/minecraft/core/Vec3i;");
    }

    protected Vec3i setY(int y) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.setY:(I)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i offset(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.offset:(III)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i offset(Vec3i vec) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.offset:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i subtract(Vec3i vec) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.subtract:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i above() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.above:()Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i above(int steps) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.above:(I)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i below() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.below:()Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i below(int steps) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.below:(I)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i north() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.north:()Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i north(int steps) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.north:(I)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i west() {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.west:()Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i west(int steps) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.west:(I)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i relative(Direction direction) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.relative:(Lnet/minecraft/core/Direction;)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i relative(Direction direction, int steps) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.relative:(Lnet/minecraft/core/Direction;I)Lnet/minecraft/core/Vec3i;");
    }

    public Vec3i relative(Direction.Axis axis, int steps) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.relative:(Lnet/minecraft/core/Direction$Axis;I)Lnet/minecraft/core/Vec3i;");
    }

    public double distSqr(Vec3i pos) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.distSqr:(Lnet/minecraft/core/Vec3i;)D");
    }

    public int get(Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/core/Vec3i.get:(Lnet/minecraft/core/Direction$Axis;)I");
    }

    public String toString() {
        return "(" + pumpkinX + ", " + pumpkinY + ", " + pumpkinZ + ")";
    }

    public Vec3i() {
    }
}
