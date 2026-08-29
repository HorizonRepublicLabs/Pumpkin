package net.minecraft.world.level;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum GameType implements StringRepresentable {

    SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR;

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/world/level/GameType.getId:()I");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/GameType.getName:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/GameType.getSerializedName:()Ljava/lang/String;");
    }

    public boolean isCreative() {
        throw Unimplemented.forMember("net/minecraft/world/level/GameType.isCreative:()Z");
    }

    public static GameType byId(int id) {
        throw Unimplemented.forMember("net/minecraft/world/level/GameType.byId:(I)Lnet/minecraft/world/level/GameType;");
    }
}
