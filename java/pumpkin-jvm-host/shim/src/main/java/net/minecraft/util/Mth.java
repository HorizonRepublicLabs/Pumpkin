package net.minecraft.util;

import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class Mth {

    public static final float SQRT_OF_TWO = 0.0F;

    public static float sin(double i) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.sin:(D)F");
    }

    public static float cos(double i) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.cos:(D)F");
    }

    public static float sqrt(float x) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.sqrt:(F)F");
    }

    public static int floor(float v) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.floor:(F)I");
    }

    public static int floor(double v) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.floor:(D)I");
    }

    public static int ceil(float v) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.ceil:(F)I");
    }

    public static int ceil(double v) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.ceil:(D)I");
    }

    public static int clamp(int value, int min, int max) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.clamp:(III)I");
    }

    public static long clamp(long value, long min, long max) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.clamp:(JJJ)J");
    }

    public static float clamp(float value, float min, float max) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.clamp:(FFF)F");
    }

    public static double clamp(double value, double min, double max) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.clamp:(DDD)D");
    }

    public static int nextInt(RandomSource random, int minInclusive, int maxInclusive) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.nextInt:(Lnet/minecraft/util/RandomSource;II)I");
    }

    public static double nextDouble(RandomSource random, double min, double max) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.nextDouble:(Lnet/minecraft/util/RandomSource;DD)D");
    }

    public static boolean equal(float a, float b) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.equal:(FF)Z");
    }

    public static boolean equal(double a, double b) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.equal:(DD)Z");
    }

    public static int wrapDegrees(int angle) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.wrapDegrees:(I)I");
    }

    public static float wrapDegrees(long angle) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.wrapDegrees:(J)F");
    }

    public static float wrapDegrees(float angle) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.wrapDegrees:(F)F");
    }

    public static double wrapDegrees(double angle) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.wrapDegrees:(D)D");
    }

    public static double atan2(double y, double x) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.atan2:(DD)D");
    }

    public static int lerpDiscrete(float alpha1, int p0, int p1) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.lerpDiscrete:(FII)I");
    }

    public static float lerp(float alpha1, float p0, float p1) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.lerp:(FFF)F");
    }

    public static Vec3 lerp(double alpha, Vec3 p1, Vec3 p2) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.lerp:(DLnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;");
    }

    public static double lerp(double alpha1, double p0, double p1) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.lerp:(DDD)D");
    }

    public static int sign(double number) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.sign:(D)I");
    }

    public static float rotLerp(float a, float from, float to) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.rotLerp:(FFF)F");
    }

    public static double rotLerp(double a, double from, double to) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.rotLerp:(DDD)D");
    }

    public static float randomBetween(RandomSource random, float min, float maxExclusive) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.randomBetween:(Lnet/minecraft/util/RandomSource;FF)F");
    }

    public static double length(double x, double y) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.length:(DD)D");
    }

    public static float length(float x, float y) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.length:(FF)F");
    }

    public static double length(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.length:(DDD)D");
    }

    public Mth() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/util/Mth");
        }
    }
}
