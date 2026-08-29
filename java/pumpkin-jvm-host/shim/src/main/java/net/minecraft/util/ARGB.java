package net.minecraft.util;

import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class ARGB {

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

    public static int colorFromFloat(float alpha, float red, float green, float blue) {
        throw Unimplemented.forMember("net/minecraft/util/ARGB.colorFromFloat:(FFFF)I");
    }

    public ARGB() {
    }
}
