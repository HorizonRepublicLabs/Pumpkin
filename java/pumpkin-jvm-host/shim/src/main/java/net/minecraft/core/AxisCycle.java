package net.minecraft.core;

import dev.pumpkin.shim.Unimplemented;

public enum AxisCycle {

    NONE {

        public int cycle(int x, int y, int z, Direction.Axis axis) {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$NONE.cycle:()");
        }

        public double cycle(double x, double y, double z, Direction.Axis axis) {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$NONE.cycle:()");
        }

        public Direction.Axis cycle(Direction.Axis axis) {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$NONE.cycle:()");
        }

        public AxisCycle inverse() {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$NONE.inverse:()");
        }
    }
    , FORWARD {

        public int cycle(int x, int y, int z, Direction.Axis axis) {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$FORWARD.cycle:()");
        }

        public double cycle(double x, double y, double z, Direction.Axis axis) {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$FORWARD.cycle:()");
        }

        public Direction.Axis cycle(Direction.Axis axis) {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$FORWARD.cycle:()");
        }

        public AxisCycle inverse() {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$FORWARD.inverse:()");
        }
    }
    , BACKWARD {

        public int cycle(int x, int y, int z, Direction.Axis axis) {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$BACKWARD.cycle:()");
        }

        public double cycle(double x, double y, double z, Direction.Axis axis) {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$BACKWARD.cycle:()");
        }

        public Direction.Axis cycle(Direction.Axis axis) {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$BACKWARD.cycle:()");
        }

        public AxisCycle inverse() {
            throw Unimplemented.forMember("net/minecraft/core/AxisCycle$BACKWARD.inverse:()");
        }
    }
    ;

    public abstract int cycle(final int x, final int y, final int z, final Direction.Axis axis);

    public abstract double cycle(final double x, final double y, final double z, final Direction.Axis axis);

    public abstract Direction.Axis cycle(final Direction.Axis axis);

    public abstract AxisCycle inverse();
}
