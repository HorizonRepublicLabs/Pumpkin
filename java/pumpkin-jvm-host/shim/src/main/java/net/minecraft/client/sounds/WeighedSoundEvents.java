package net.minecraft.client.sounds;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class WeighedSoundEvents implements Weighted<Sound> {

    public WeighedSoundEvents(Identifier location, String subtitle) {
    }

    public int getWeight() {
        throw Unimplemented.forMember("net/minecraft/client/sounds/WeighedSoundEvents.getWeight:()I");
    }

    public Sound getSound(RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/client/sounds/WeighedSoundEvents.getSound:(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/client/resources/sounds/Sound;");
    }

    public void preloadIfRequired(SoundEngine soundEngine) {
        throw Unimplemented.forMember("net/minecraft/client/sounds/WeighedSoundEvents.preloadIfRequired:(Lnet/minecraft/client/sounds/SoundEngine;)V");
    }

    public WeighedSoundEvents() {
    }
}
