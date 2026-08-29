package net.minecraft.client.player;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.ClientAvatarEntity;
import net.minecraft.client.entity.ClientAvatarState;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.world.level.GameType;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractClientPlayer extends Player implements ClientAvatarEntity {

    public AbstractClientPlayer(ClientLevel level, GameProfile gameProfile) {
    }

    public GameType gameMode() {
        throw Unimplemented.forMember("net/minecraft/client/player/AbstractClientPlayer.gameMode:()Lnet/minecraft/world/level/GameType;");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/player/AbstractClientPlayer.tick:()V");
    }

    public ClientAvatarState avatarState() {
        throw Unimplemented.forMember("net/minecraft/client/player/AbstractClientPlayer.avatarState:()Lnet/minecraft/client/entity/ClientAvatarState;");
    }

    public PlayerSkin getSkin() {
        throw Unimplemented.forMember("net/minecraft/client/player/AbstractClientPlayer.getSkin:()Lnet/minecraft/world/entity/player/PlayerSkin;");
    }

    public Parrot.Variant getParrotVariantOnShoulder(boolean left) {
        throw Unimplemented.forMember("net/minecraft/client/player/AbstractClientPlayer.getParrotVariantOnShoulder:(Z)Lnet/minecraft/world/entity/animal/parrot/Parrot$Variant;");
    }

    public void rideTick() {
        throw Unimplemented.forMember("net/minecraft/client/player/AbstractClientPlayer.rideTick:()V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/client/player/AbstractClientPlayer.aiStep:()V");
    }

    public boolean showExtraEars() {
        throw Unimplemented.forMember("net/minecraft/client/player/AbstractClientPlayer.showExtraEars:()Z");
    }

    public AbstractClientPlayer() {
    }
}
