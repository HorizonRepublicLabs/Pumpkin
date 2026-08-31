package net.minecraft.world.item;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import dev.pumpkin.shim.Unimplemented;

public enum DyeColor implements StringRepresentable {

    WHITE,
    ORANGE,
    MAGENTA,
    LIGHT_BLUE,
    YELLOW,
    LIME,
    PINK,
    GRAY,
    LIGHT_GRAY,
    CYAN,
    PURPLE,
    BLUE,
    BROWN,
    GREEN,
    RED,
    BLACK;

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/world/item/DyeColor.getId:()I");
    }

    // Pumpkin divergence: vanilla body -- the lowercase constant name.
    public String getName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }

    // Pumpkin divergence: vanilla's own dye-to-map-color table, over the constants
    // this shim's pruned enum carries.
    public MapColor getMapColor() {
        return switch (this) {
            case WHITE -> net.minecraft.world.level.material.MapColor.SNOW;
            case ORANGE -> net.minecraft.world.level.material.MapColor.COLOR_ORANGE;
            case MAGENTA -> net.minecraft.world.level.material.MapColor.COLOR_MAGENTA;
            case LIGHT_BLUE -> net.minecraft.world.level.material.MapColor.COLOR_LIGHT_BLUE;
            case YELLOW -> net.minecraft.world.level.material.MapColor.COLOR_YELLOW;
            case LIME -> net.minecraft.world.level.material.MapColor.COLOR_LIGHT_GREEN;
            case PINK -> net.minecraft.world.level.material.MapColor.COLOR_PINK;
            case GRAY -> net.minecraft.world.level.material.MapColor.COLOR_GRAY;
            case LIGHT_GRAY -> net.minecraft.world.level.material.MapColor.COLOR_LIGHT_GRAY;
            case CYAN -> net.minecraft.world.level.material.MapColor.COLOR_CYAN;
            case PURPLE -> net.minecraft.world.level.material.MapColor.COLOR_PURPLE;
            case BLUE -> net.minecraft.world.level.material.MapColor.COLOR_BLUE;
            case BROWN -> net.minecraft.world.level.material.MapColor.COLOR_BROWN;
            case GREEN -> net.minecraft.world.level.material.MapColor.COLOR_GREEN;
            case RED -> net.minecraft.world.level.material.MapColor.COLOR_RED;
            case BLACK -> net.minecraft.world.level.material.MapColor.COLOR_BLACK;
        };
    }

    public static DyeColor byId(int id) {
        throw Unimplemented.forMember("net/minecraft/world/item/DyeColor.byId:(I)Lnet/minecraft/world/item/DyeColor;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/item/DyeColor.toString:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/item/DyeColor.getSerializedName:()Ljava/lang/String;");
    }
}
