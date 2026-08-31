package net.minecraft.client.resources.sounds;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class SimpleSoundInstance extends AbstractSoundInstance {

    public SimpleSoundInstance(SoundEvent sound, SoundSource source, float volume, float pitch, RandomSource random, BlockPos pos) {
    }

    public static SimpleSoundInstance forUI(SoundEvent sound, float pitch) {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/SimpleSoundInstance.forUI:(Lnet/minecraft/sounds/SoundEvent;F)Lnet/minecraft/client/resources/sounds/SimpleSoundInstance;");
    }

    public static SimpleSoundInstance forUI(Holder<SoundEvent> sound, float pitch) {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/SimpleSoundInstance.forUI:(Lnet/minecraft/core/Holder;F)Lnet/minecraft/client/resources/sounds/SimpleSoundInstance;");
    }

    public static SimpleSoundInstance forUI(SoundEvent sound, float pitch, float volume) {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/SimpleSoundInstance.forUI:(Lnet/minecraft/sounds/SoundEvent;FF)Lnet/minecraft/client/resources/sounds/SimpleSoundInstance;");
    }

    public SimpleSoundInstance(SoundEvent sound, SoundSource source, float volume, float pitch, RandomSource random, double x, double y, double z) {
    }

    private SimpleSoundInstance(SoundEvent sound, SoundSource source, float volume, float pitch, RandomSource random, boolean looping, int delay, SoundInstance.Attenuation attenuation, double x, double y, double z) {
    }

    public SimpleSoundInstance(Identifier location, SoundSource source, float volume, float pitch, RandomSource random, boolean looping, int delay, SoundInstance.Attenuation attenuation, double x, double y, double z, boolean relative) {
    }

    public SimpleSoundInstance() {
    }
}
