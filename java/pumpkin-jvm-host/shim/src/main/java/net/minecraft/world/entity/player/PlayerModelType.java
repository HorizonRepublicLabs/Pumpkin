package net.minecraft.world.entity.player;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum PlayerModelType implements StringRepresentable {

    SLIM, WIDE;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/PlayerModelType.getSerializedName:()Ljava/lang/String;");
    }
}
