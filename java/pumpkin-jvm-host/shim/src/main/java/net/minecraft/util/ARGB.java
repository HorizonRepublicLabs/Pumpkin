package net.minecraft.util;

import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class ARGB {

    public static int color(int alpha, int red, int green, int blue) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.color:(IIII)I");
    }

    public static int color(int red, int green, int blue) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.color:(III)I");
    }

    public static int color(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.color:(Lnet/minecraft/world/phys/Vec3;)I");
    }

    public static int color(int alpha, int rgb) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.color:(II)I");
    }

    public static int color(float alpha, int rgb) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.color:(FI)I");
    }

    public static int white(float alpha) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.white:(F)I");
    }

    public static int white(int alpha) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.white:(I)I");
    }

    public static int colorFromFloat(float alpha, float red, float green, float blue) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.colorFromFloat:(FFFF)I");
    }

    protected ARGB() {
    }
}
