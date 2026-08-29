package net.minecraft.world;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum Difficulty implements StringRepresentable {

    PEACEFUL, EASY, NORMAL, HARD;

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/world/Difficulty.getId:()I");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/Difficulty.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public static Difficulty byId(int id) {
        throw Unimplemented.forMember("net/minecraft/world/Difficulty.byId:(I)Lnet/minecraft/world/Difficulty;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/Difficulty.getSerializedName:()Ljava/lang/String;");
    }
}
