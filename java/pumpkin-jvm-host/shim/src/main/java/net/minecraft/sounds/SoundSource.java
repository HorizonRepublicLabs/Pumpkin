package net.minecraft.sounds;

import dev.pumpkin.shim.Unimplemented;

public enum SoundSource {

    MASTER,
    MUSIC,
    RECORDS,
    WEATHER,
    BLOCKS,
    HOSTILE,
    NEUTRAL,
    PLAYERS,
    AMBIENT,
    VOICE,
    UI;

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/sounds/SoundSource.getName:()Ljava/lang/String;");
    }
}
