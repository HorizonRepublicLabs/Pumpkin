package net.minecraft.util;

import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class Mth {

    public static float sin(double i) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.sin:(D)F");
    }

    public static float cos(double i) {
        throw Unimplemented.forMember("net/minecraft/util/Mth.cos:(D)F");
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

    protected Mth() {
    }
}
