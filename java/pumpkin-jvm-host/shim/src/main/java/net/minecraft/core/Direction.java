package net.minecraft.core;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public enum Direction implements StringRepresentable {

    DOWN,
    UP,
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public static final StringRepresentable.EnumCodec<Direction> CODEC = null;

    public static final StreamCodec<ByteBuf, Direction> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public static final Codec<Direction> LEGACY_ID_CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.core.Direction.LEGACY_ID_CODEC");

    public static Stream<Direction> stream() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.stream:()Ljava/util/stream/Stream;");
    }

    public Quaternionf getRotation() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getRotation:()Lorg/joml/Quaternionf;");
    }

    public int get2DDataValue() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.get2DDataValue:()I");
    }

    public Direction.AxisDirection getAxisDirection() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getAxisDirection:()Lnet/minecraft/core/Direction$AxisDirection;");
    }

    // Pumpkin divergence: vanilla body.
    public Direction getOpposite() {
        return switch (this) {
            case DOWN -> UP;
            case UP -> DOWN;
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            case EAST -> WEST;
        };
    }

    public Direction getClockWise(Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getClockWise:(Lnet/minecraft/core/Direction$Axis;)Lnet/minecraft/core/Direction;");
    }

    public Direction getCounterClockWise(Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getCounterClockWise:(Lnet/minecraft/core/Direction$Axis;)Lnet/minecraft/core/Direction;");
    }

    // Pumpkin divergence: vanilla body.
    public Direction getClockWise() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> throw new IllegalStateException("no horizontal rotation for " + this);
        };
    }

    // Pumpkin divergence: vanilla body.
    public Direction getCounterClockWise() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
            default -> throw new IllegalStateException("no horizontal rotation for " + this);
        };
    }

    // Pumpkin divergence: vanilla body.
    public int getStepX() {
        return switch (this) {
            case WEST -> -1;
            case EAST -> 1;
            default -> 0;
        };
    }

    // Pumpkin divergence: vanilla body.
    public int getStepY() {
        return switch (this) {
            case DOWN -> -1;
            case UP -> 1;
            default -> 0;
        };
    }

    // Pumpkin divergence: vanilla body.
    public int getStepZ() {
        return switch (this) {
            case NORTH -> -1;
            case SOUTH -> 1;
            default -> 0;
        };
    }

    public Vector3f step() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.step:()Lorg/joml/Vector3f;");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getName:()Ljava/lang/String;");
    }

    public Direction.Axis getAxis() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getAxis:()Lnet/minecraft/core/Direction$Axis;");
    }

    public static Direction from3DDataValue(int data) {
        throw Unimplemented.forMember("net/minecraft/core/Direction.from3DDataValue:(I)Lnet/minecraft/core/Direction;");
    }

    public static Direction fromYRot(double yRot) {
        throw Unimplemented.forMember("net/minecraft/core/Direction.fromYRot:(D)Lnet/minecraft/core/Direction;");
    }

    public static Direction getRandom(RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getRandom:(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/core/Direction;");
    }

    // Pumpkin divergence: vanilla body -- the lowercase name.
    public String toString() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }

    // Pumpkin divergence: real body -- vanilla serializes a direction as its lowercase
    // constant name ("down", "up", "north", ...), which is what state property values carry.
    public String getSerializedName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }

    public static Direction get(Direction.AxisDirection axisDirection, Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/core/Direction.get:(Lnet/minecraft/core/Direction$AxisDirection;Lnet/minecraft/core/Direction$Axis;)Lnet/minecraft/core/Direction;");
    }

    public Vec3i getUnitVec3i() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getUnitVec3i:()Lnet/minecraft/core/Vec3i;");
    }

    public Vector3fc getUnitVec3f() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getUnitVec3f:()Lorg/joml/Vector3fc;");
    }

    public enum Axis implements Predicate<Direction>, StringRepresentable {

        X {

            public int choose(int x, int y, int z) {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$X.choose:()");
            }

            public boolean choose(boolean x, boolean y, boolean z) {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$X.choose:()");
            }

            public double choose(double x, double y, double z) {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$X.choose:()");
            }

            public Direction getPositive() {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$X.getPositive:()");
            }

            public Direction getNegative() {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$X.getNegative:()");
            }
        }
        , Y {

            public int choose(int x, int y, int z) {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$Y.choose:()");
            }

            public double choose(double x, double y, double z) {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$Y.choose:()");
            }

            public boolean choose(boolean x, boolean y, boolean z) {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$Y.choose:()");
            }

            public Direction getPositive() {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$Y.getPositive:()");
            }

            public Direction getNegative() {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$Y.getNegative:()");
            }
        }
        , Z {

            public int choose(int x, int y, int z) {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$Z.choose:()");
            }

            public double choose(double x, double y, double z) {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$Z.choose:()");
            }

            public boolean choose(boolean x, boolean y, boolean z) {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$Z.choose:()");
            }

            public Direction getPositive() {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$Z.getPositive:()");
            }

            public Direction getNegative() {
                throw Unimplemented.forMember("net/minecraft/core/Direction$Axis$Z.getNegative:()");
            }
        }
        ;

        public String getName() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Axis.getName:()Ljava/lang/String;");
        }

        public boolean isVertical() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Axis.isVertical:()Z");
        }

        public boolean isHorizontal() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Axis.isHorizontal:()Z");
        }

        public abstract Direction getPositive();

        public abstract Direction getNegative();

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Axis.toString:()Ljava/lang/String;");
        }

        public static Direction.Axis getRandom(RandomSource random) {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Axis.getRandom:(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/core/Direction$Axis;");
        }

        public boolean test(Direction input) {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Axis.test:(Lnet/minecraft/core/Direction;)Z");
        }

        // Pumpkin divergence: real body -- an axis serializes as "x", "y" or "z".
        public String getSerializedName() {
            return name().toLowerCase(java.util.Locale.ROOT);
        }

        public abstract int choose(final int x, final int y, final int z);

        public abstract double choose(final double x, final double y, final double z);

        public abstract boolean choose(final boolean x, final boolean y, final boolean z);
    }

    public enum AxisDirection {

        POSITIVE, NEGATIVE;

        public int getStep() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$AxisDirection.getStep:()I");
        }

        public String getName() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$AxisDirection.getName:()Ljava/lang/String;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$AxisDirection.toString:()Ljava/lang/String;");
        }
    }

    public enum Plane implements Predicate<Direction>, Iterable<Direction> {

        HORIZONTAL, VERTICAL;

        public boolean test(Direction input) {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Plane.test:(Lnet/minecraft/core/Direction;)Z");
        }

        public Iterator<Direction> iterator() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Plane.iterator:()Ljava/util/Iterator;");
        }

        public Stream<Direction> stream() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Plane.stream:()Ljava/util/stream/Stream;");
        }

        public int length() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Plane.length:()I");
        }
    }
}
