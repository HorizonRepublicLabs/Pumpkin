package net.minecraft.core;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum Direction implements StringRepresentable {

    DOWN,
    UP,
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public static Stream<Direction> stream() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.stream:()Ljava/util/stream/Stream;");
    }

    public int get2DDataValue() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.get2DDataValue:()I");
    }

    public Direction.AxisDirection getAxisDirection() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getAxisDirection:()Lnet/minecraft/core/Direction$AxisDirection;");
    }

    public Direction getOpposite() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getOpposite:()Lnet/minecraft/core/Direction;");
    }

    public Direction getClockWise(Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getClockWise:(Lnet/minecraft/core/Direction$Axis;)Lnet/minecraft/core/Direction;");
    }

    public Direction getCounterClockWise(Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getCounterClockWise:(Lnet/minecraft/core/Direction$Axis;)Lnet/minecraft/core/Direction;");
    }

    public Direction getClockWise() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getClockWise:()Lnet/minecraft/core/Direction;");
    }

    public Direction getCounterClockWise() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getCounterClockWise:()Lnet/minecraft/core/Direction;");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getName:()Ljava/lang/String;");
    }

    public Direction.Axis getAxis() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getAxis:()Lnet/minecraft/core/Direction$Axis;");
    }

    public static Direction getRandom(RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getRandom:(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/core/Direction;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.toString:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/core/Direction.getSerializedName:()Ljava/lang/String;");
    }

    public static Direction get(Direction.AxisDirection axisDirection, Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/core/Direction.get:(Lnet/minecraft/core/Direction$AxisDirection;Lnet/minecraft/core/Direction$Axis;)Lnet/minecraft/core/Direction;");
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

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/core/Direction$Axis.getSerializedName:()Ljava/lang/String;");
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
