package net.minecraft.world.scores;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum DisplaySlot implements StringRepresentable {

    LIST,
    SIDEBAR,
    BELOW_NAME,
    TEAM_BLACK,
    TEAM_DARK_BLUE,
    TEAM_DARK_GREEN,
    TEAM_DARK_AQUA,
    TEAM_DARK_RED,
    TEAM_DARK_PURPLE,
    TEAM_GOLD,
    TEAM_GRAY,
    TEAM_DARK_GRAY,
    TEAM_BLUE,
    TEAM_GREEN,
    TEAM_AQUA,
    TEAM_RED,
    TEAM_LIGHT_PURPLE,
    TEAM_YELLOW,
    TEAM_WHITE;

    public int id() {
        throw Unimplemented.forMember("net/minecraft/world/scores/DisplaySlot.id:()I");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/scores/DisplaySlot.getSerializedName:()Ljava/lang/String;");
    }
}
