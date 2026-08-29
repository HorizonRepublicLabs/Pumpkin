package net.minecraft.world.item;

import net.minecraft.util.StringRepresentable;
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

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/item/DyeColor.getName:()Ljava/lang/String;");
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
