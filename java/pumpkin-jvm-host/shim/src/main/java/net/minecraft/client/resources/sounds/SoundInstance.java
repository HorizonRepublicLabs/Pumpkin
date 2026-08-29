package net.minecraft.client.resources.sounds;

import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;

public interface SoundInstance {

    Identifier getIdentifier();

    WeighedSoundEvents resolve(SoundManager soundManager);

    Sound getSound();

    SoundSource getSource();

    boolean isLooping();

    boolean isRelative();

    int getDelay();

    float getVolume();

    float getPitch();

    double getX();

    double getY();

    double getZ();

    SoundInstance.Attenuation getAttenuation();

    enum Attenuation {

        NONE, LINEAR
    }
}
