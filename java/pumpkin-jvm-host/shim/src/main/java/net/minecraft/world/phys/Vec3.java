package net.minecraft.world.phys;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import net.minecraft.network.codec.StreamCodec;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class Vec3 implements Position {

    public static final StreamCodec<ByteBuf, Vec3> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public static final Vec3 ZERO = null;

    // Pumpkin divergence: a vector really carries its coordinates; the pruner had
    // zeroed them because the originals were assigned in a stripped constructor.
    public final double x;

    public final double y;

    public final double z;

    public static Vec3 atLowerCornerOf(Vec3i pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.atLowerCornerOf:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/world/phys/Vec3;");
    }

    public static Vec3 atCenterOf(Vec3i pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.atCenterOf:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/world/phys/Vec3;");
    }

    public static Vec3 upFromBottomCenterOf(Vec3i pos, double yOffset) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.upFromBottomCenterOf:(Lnet/minecraft/core/Vec3i;D)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(Vector3fc vec) {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Vec3(Vec3i vec) {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Vec3 normalize() {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.normalize:()Lnet/minecraft/world/phys/Vec3;");
    }

    public double dot(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.dot:(Lnet/minecraft/world/phys/Vec3;)D");
    }

    public Vec3 subtract(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.subtract:(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 subtract(double value) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.subtract:(D)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 subtract(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.subtract:(DDD)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 add(double value) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.add:(D)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 add(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.add:(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 add(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.add:(DDD)Lnet/minecraft/world/phys/Vec3;");
    }

    public double distanceTo(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.distanceTo:(Lnet/minecraft/world/phys/Vec3;)D");
    }

    public double distanceToSqr(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.distanceToSqr:(Lnet/minecraft/world/phys/Vec3;)D");
    }

    public double distanceToSqr(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.distanceToSqr:(DDD)D");
    }

    public Vec3 scale(double scale) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.scale:(D)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 multiply(Vec3 scale) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.multiply:(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 multiply(double xScale, double yScale, double zScale) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.multiply:(DDD)Lnet/minecraft/world/phys/Vec3;");
    }

    public double length() {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.length:()D");
    }

    public double lengthSqr() {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.lengthSqr:()D");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.toString:()Ljava/lang/String;");
    }

    public Vec3 xRot(float radians) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.xRot:(F)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 yRot(float radians) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.yRot:(F)Lnet/minecraft/world/phys/Vec3;");
    }

    public double get(Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.get:(Lnet/minecraft/core/Direction$Axis;)D");
    }

    public Vec3 with(Direction.Axis axis, double value) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.with:(Lnet/minecraft/core/Direction$Axis;D)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 relative(Direction direction, double distance) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.relative:(Lnet/minecraft/core/Direction;D)Lnet/minecraft/world/phys/Vec3;");
    }

    // Pumpkin divergence: vanilla bodies -- the record-style accessors over the fields.
    public final double x() {
        return x;
    }

    public final double y() {
        return y;
    }

    public final double z() {
        return z;
    }

    public Vector3f toVector3f() {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.toVector3f:()Lorg/joml/Vector3f;");
    }

    public Vec3() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }
}
