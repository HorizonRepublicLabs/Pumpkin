package net.minecraft.util;

import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class ARGB {

    // Pumpkin divergence: vanilla arithmetic.
    public static int alpha(int color) {
        return color >>> 24;
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static int red(int color) {
        return color >> 16 & 0xFF;
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static int green(int color) {
        return color >> 8 & 0xFF;
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static int blue(int color) {
        return color & 0xFF;
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

    // Pumpkin divergence: vanilla arithmetic.
    public static int multiply(int lhs, int rhs) {
        return color((alpha(lhs) * alpha(rhs)) / 255, (red(lhs) * red(rhs)) / 255, (green(lhs) * green(rhs)) / 255, (blue(lhs) * blue(rhs)) / 255);
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static int srgbLerp(float alpha, int p0, int p1) {
        return color((int) (alpha(p0) + alpha * (alpha(p1) - alpha(p0))), (int) (red(p0) + alpha * (red(p1) - red(p0))), (int) (green(p0) + alpha * (green(p1) - green(p0))), (int) (blue(p0) + alpha * (blue(p1) - blue(p0))));
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static int opaque(int color) {
        return color | 0xFF000000;
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static int transparent(int color) {
        return color & 0xFFFFFF;
    }

    // Pumpkin divergence: real body, copied from vanilla. This is the one four
    // MysticalAgriculture classes call to build their tier and crop colours.
    public static int color(int alpha, int rgb) {
        return alpha << 24 | rgb & 16777215;
    }

    public static int color(float alpha, int rgb) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.color:(FI)I");
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static int white(float alpha) {
        return as8BitChannel(alpha) << 24 | 0xFFFFFF;
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

    // Pumpkin divergence: vanilla arithmetic.
    public static int colorFromFloat(float alpha, float red, float green, float blue) {
        return color(as8BitChannel(alpha), as8BitChannel(red), as8BitChannel(green), as8BitChannel(blue));
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static int as8BitChannel(float value) {
        return (int) Math.floor(value * 255.0F);
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static float redFloat(int color) {
        return red(color) / 255.0F;
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static float greenFloat(int color) {
        return green(color) / 255.0F;
    }

    // Pumpkin divergence: vanilla arithmetic.
    public static float blueFloat(int color) {
        return blue(color) / 255.0F;
    }

    public ARGB() {
    }
}
