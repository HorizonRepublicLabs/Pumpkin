package net.minecraft.client.resources.sounds;

import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractSoundInstance implements SoundInstance {

    protected AbstractSoundInstance(SoundEvent event, SoundSource source, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.<init>:(Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;Lnet/minecraft/util/RandomSource;)V");
    }

    protected AbstractSoundInstance(Identifier identifier, SoundSource source, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.<init>:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/sounds/SoundSource;Lnet/minecraft/util/RandomSource;)V");
    }

    public Identifier getIdentifier() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.getIdentifier:()Lnet/minecraft/resources/Identifier;");
    }

    public WeighedSoundEvents resolve(SoundManager soundManager) {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.resolve:(Lnet/minecraft/client/sounds/SoundManager;)Lnet/minecraft/client/sounds/WeighedSoundEvents;");
    }

    public Sound getSound() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.getSound:()Lnet/minecraft/client/resources/sounds/Sound;");
    }

    public SoundSource getSource() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.getSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    public boolean isLooping() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.isLooping:()Z");
    }

    public int getDelay() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.getDelay:()I");
    }

    public float getVolume() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.getVolume:()F");
    }

    public float getPitch() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.getPitch:()F");
    }

    public double getX() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.getX:()D");
    }

    public double getY() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.getY:()D");
    }

    public double getZ() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.getZ:()D");
    }

    public SoundInstance.Attenuation getAttenuation() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.getAttenuation:()Lnet/minecraft/client/resources/sounds/SoundInstance$Attenuation;");
    }

    public boolean isRelative() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.isRelative:()Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractSoundInstance.toString:()Ljava/lang/String;");
    }

    protected AbstractSoundInstance() {
    }
}
