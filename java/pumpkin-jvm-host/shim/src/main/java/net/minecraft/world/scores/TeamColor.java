package net.minecraft.world.scores;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum TeamColor implements StringRepresentable {

    BLACK,
    DARK_BLUE,
    DARK_GREEN,
    DARK_AQUA,
    DARK_RED,
    DARK_PURPLE,
    GOLD,
    GRAY,
    DARK_GRAY,
    BLUE,
    GREEN,
    AQUA,
    RED,
    LIGHT_PURPLE,
    YELLOW,
    WHITE;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/scores/TeamColor.getSerializedName:()Ljava/lang/String;");
    }
}
