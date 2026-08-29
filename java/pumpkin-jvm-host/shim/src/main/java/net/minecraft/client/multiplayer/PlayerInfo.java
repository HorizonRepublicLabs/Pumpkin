package net.minecraft.client.multiplayer;

import com.mojang.authlib.GameProfile;
import net.minecraft.world.entity.player.PlayerSkin;
import dev.pumpkin.shim.Unimplemented;

public class PlayerInfo {

    public PlayerInfo(GameProfile profile, boolean enforcesSecureChat) {
    }

    public GameProfile getProfile() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/PlayerInfo.getProfile:()Lcom/mojang/authlib/GameProfile;");
    }

    public PlayerSkin getSkin() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/PlayerInfo.getSkin:()Lnet/minecraft/world/entity/player/PlayerSkin;");
    }

    public PlayerInfo() {
    }
}
