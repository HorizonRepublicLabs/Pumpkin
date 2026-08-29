package net.minecraft.client.entity;

import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.animal.parrot.Parrot;
import net.minecraft.world.entity.decoration.Mannequin;
import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class ClientMannequin extends Mannequin implements ClientAvatarEntity {

    public ClientMannequin(Level level, PlayerSkinRenderCache skinRenderCache) {
        throw Unimplemented.forMember("net/minecraft/client/entity/ClientMannequin.<init>:(Lnet/minecraft/world/level/Level;Lnet/minecraft/client/renderer/PlayerSkinRenderCache;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/entity/ClientMannequin.tick:()V");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/client/entity/ClientMannequin.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public ClientAvatarState avatarState() {
        throw Unimplemented.forMember("net/minecraft/client/entity/ClientMannequin.avatarState:()Lnet/minecraft/client/entity/ClientAvatarState;");
    }

    public PlayerSkin getSkin() {
        throw Unimplemented.forMember("net/minecraft/client/entity/ClientMannequin.getSkin:()Lnet/minecraft/world/entity/player/PlayerSkin;");
    }

    public Component belowNameDisplay() {
        throw Unimplemented.forMember("net/minecraft/client/entity/ClientMannequin.belowNameDisplay:()Lnet/minecraft/network/chat/Component;");
    }

    public Parrot.Variant getParrotVariantOnShoulder(boolean left) {
        throw Unimplemented.forMember("net/minecraft/client/entity/ClientMannequin.getParrotVariantOnShoulder:(Z)Lnet/minecraft/world/entity/animal/parrot/Parrot$Variant;");
    }

    public boolean showExtraEars() {
        throw Unimplemented.forMember("net/minecraft/client/entity/ClientMannequin.showExtraEars:()Z");
    }

    public ClientMannequin() {
    }
}
