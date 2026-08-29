package net.minecraft.client.entity;

import net.minecraft.world.entity.animal.parrot.Parrot;
import net.minecraft.world.entity.player.PlayerSkin;

public interface ClientAvatarEntity {

    ClientAvatarState avatarState();

    PlayerSkin getSkin();

    Parrot.Variant getParrotVariantOnShoulder(boolean left);

    boolean showExtraEars();
}
