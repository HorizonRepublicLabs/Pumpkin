package net.minecraft.client.resources.sounds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractTickableSoundInstance extends AbstractSoundInstance implements TickableSoundInstance {

    protected AbstractTickableSoundInstance(SoundEvent event, SoundSource source, RandomSource random) {
    }

    public boolean isStopped() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractTickableSoundInstance.isStopped:()Z");
    }

    protected final void stop() {
        throw Unimplemented.forMember("net/minecraft/client/resources/sounds/AbstractTickableSoundInstance.stop:()V");
    }

    public AbstractTickableSoundInstance() {
    }
}
