package net.neoforged.neoforge.client.event.sound;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import dev.pumpkin.shim.Unimplemented;

public class PlaySoundEvent extends SoundEvent {

    public PlaySoundEvent(SoundEngine manager, SoundInstance sound) {
    }

    public String getName() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/sound/PlaySoundEvent.getName:()Ljava/lang/String;");
    }

    public SoundInstance getOriginalSound() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/sound/PlaySoundEvent.getOriginalSound:()Lnet/minecraft/client/resources/sounds/SoundInstance;");
    }

    public SoundInstance getSound() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/sound/PlaySoundEvent.getSound:()Lnet/minecraft/client/resources/sounds/SoundInstance;");
    }

    public void setSound(SoundInstance newSound) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/sound/PlaySoundEvent.setSound:(Lnet/minecraft/client/resources/sounds/SoundInstance;)V");
    }

    public PlaySoundEvent() {
    }
}
