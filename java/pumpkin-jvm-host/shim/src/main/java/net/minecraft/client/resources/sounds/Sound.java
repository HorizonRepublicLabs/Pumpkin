package net.minecraft.client.resources.sounds;

import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.Weighted;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.SampledFloat;
import dev.pumpkin.shim.Unimplemented;

public class Sound implements Weighted<Sound> {

    public Sound(Identifier location, SampledFloat volume, SampledFloat pitch, int weight, Sound.Type type, boolean stream, boolean preload, int attenuationDistance) {
    }

    public SampledFloat getVolume() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/Sound.getVolume:()Lnet/minecraft/util/valueproviders/SampledFloat;");
    }

    public SampledFloat getPitch() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/Sound.getPitch:()Lnet/minecraft/util/valueproviders/SampledFloat;");
    }

    public int getWeight() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/Sound.getWeight:()I");
    }

    public Sound getSound(RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/Sound.getSound:(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/client/resources/sounds/Sound;");
    }

    public void preloadIfRequired(SoundEngine soundEngine) {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/Sound.preloadIfRequired:(Lnet/minecraft/client/sounds/SoundEngine;)V");
    }

    public Sound.Type getType() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/Sound.getType:()Lnet/minecraft/client/resources/sounds/Sound$Type;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/Sound.toString:()Ljava/lang/String;");
    }

    public enum Type {

        FILE, SOUND_EVENT
    }

    public Sound() {
    }
}
