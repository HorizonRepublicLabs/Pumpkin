package net.minecraft.util;

import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class ARGB {

    public static int alpha(int color) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.alpha:(I)I");
    }

    public static int red(int color) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.red:(I)I");
    }

    public static int green(int color) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.green:(I)I");
    }

    public static int blue(int color) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.blue:(I)I");
    }

    // Pumpkin divergence: real body, copied from vanilla. Pure arithmetic over primitives
    // -- there is nothing here for the shim to be missing, so stubbing it would refuse to
    // answer a question that has one right answer.
    public static int color(int alpha, int red, int green, int blue) {
        return (alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
    }

    // Pumpkin divergence: real body, copied from vanilla.
    public static int color(int red, int green, int blue) {
        return color(255, red, green, blue);
    }

    public static int color(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.color:(Lnet/minecraft/world/phys/Vec3;)I");
    }

    public static int multiply(int lhs, int rhs) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.multiply:(II)I");
    }

    public static int srgbLerp(float alpha, int p0, int p1) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.srgbLerp:(FII)I");
    }

    public static int opaque(int color) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.opaque:(I)I");
    }

    public static int transparent(int color) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.transparent:(I)I");
    }

    // Pumpkin divergence: real body, copied from vanilla. This is the one four
    // MysticalAgriculture classes call to build their tier and crop colours.
    public static int color(int alpha, int rgb) {
        return alpha << 24 | rgb & 16777215;
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

    public static int black(float alpha) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.black:(F)I");
    }

    public static int black(int alpha) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.black:(I)I");
    }

    public static int colorFromFloat(float alpha, float red, float green, float blue) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.colorFromFloat:(FFFF)I");
    }

    public static int as8BitChannel(float value) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.as8BitChannel:(F)I");
    }

    public static float redFloat(int color) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.redFloat:(I)F");
    }

    public static float greenFloat(int color) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.greenFloat:(I)F");
    }

    public static float blueFloat(int color) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.blueFloat:(I)F");
    }

    public ARGB() {
    }
}
