package net.minecraft.util;

import net.minecraft.world.phys.Vec3;

// Pumpkin divergence: real throughout -- Mth is pure math, and the vanilla bodies are
// the well-known formulas. sin/cos skip vanilla's lookup table for Math's own answers,
// a difference below the table's own quantisation error.
public class Mth {

    public static final float SQRT_OF_TWO = (float) Math.sqrt(2.0);

    public static float sin(double i) {
        return (float) Math.sin(i);
    }

    public static float cos(double i) {
        return (float) Math.cos(i);
    }

    public static float sqrt(float x) {
        return (float) Math.sqrt(x);
    }

    public static int floor(float v) {
        return (int) Math.floor(v);
    }

    public static int floor(double v) {
        return (int) Math.floor(v);
    }

    public static int ceil(float v) {
        return (int) Math.ceil(v);
    }

    public static int ceil(double v) {
        return (int) Math.ceil(v);
    }

    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static long clamp(long value, long min, long max) {
        return Math.min(Math.max(value, min), max);
    }

    public static float clamp(float value, float min, float max) {
        return value < min ? min : Math.min(value, max);
    }

    public static double clamp(double value, double min, double max) {
        return value < min ? min : Math.min(value, max);
    }

    public static int nextInt(RandomSource random, int minInclusive, int maxInclusive) {
        return minInclusive >= maxInclusive
                ? minInclusive
                : random.nextInt(maxInclusive - minInclusive + 1) + minInclusive;
    }

    public static double nextDouble(RandomSource random, double min, double max) {
        return min >= max ? min : random.nextDouble() * (max - min) + min;
    }

    public static boolean equal(float a, float b) {
        return Math.abs(b - a) < 1.0E-5F;
    }

    public static boolean equal(double a, double b) {
        return Math.abs(b - a) < 9.999999747378752E-6;
    }

    public static int wrapDegrees(int angle) {
        int wrapped = angle % 360;
        if (wrapped >= 180) {
            wrapped -= 360;
        }
        if (wrapped < -180) {
            wrapped += 360;
        }
        return wrapped;
    }

    public static float wrapDegrees(long angle) {
        return wrapDegrees((float) angle);
    }

    public static float wrapDegrees(float angle) {
        float wrapped = angle % 360.0F;
        if (wrapped >= 180.0F) {
            wrapped -= 360.0F;
        }
        if (wrapped < -180.0F) {
            wrapped += 360.0F;
        }
        return wrapped;
    }

    public static double wrapDegrees(double angle) {
        double wrapped = angle % 360.0;
        if (wrapped >= 180.0) {
            wrapped -= 360.0;
        }
        if (wrapped < -180.0) {
            wrapped += 360.0;
        }
        return wrapped;
    }

    public static double atan2(double y, double x) {
        return Math.atan2(y, x);
    }

    public static int lerpDiscrete(float alpha1, int p0, int p1) {
        return p0 + floor(alpha1 * (float) (p1 - p0));
    }

    public static float lerp(float alpha1, float p0, float p1) {
        return p0 + alpha1 * (p1 - p0);
    }

    public static Vec3 lerp(double alpha, Vec3 p1, Vec3 p2) {
        return new Vec3(
                lerp(alpha, p1.x, p2.x), lerp(alpha, p1.y, p2.y), lerp(alpha, p1.z, p2.z));
    }

    public static double lerp(double alpha1, double p0, double p1) {
        return p0 + alpha1 * (p1 - p0);
    }

    public static int sign(double number) {
        if (number == 0.0) {
            return 0;
        }
        return number > 0.0 ? 1 : -1;
    }

    public static float rotLerp(float a, float from, float to) {
        return from + a * wrapDegrees(to - from);
    }

    public static double rotLerp(double a, double from, double to) {
        return from + a * wrapDegrees(to - from);
    }

    public static float randomBetween(RandomSource random, float min, float maxExclusive) {
        return random.nextFloat() * (maxExclusive - min) + min;
    }

    public static double length(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    public static float length(float x, float y) {
        return (float) Math.sqrt(x * x + y * y);
    }

    public static double length(double x, double y, double z) {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Mth() {
    }
}
