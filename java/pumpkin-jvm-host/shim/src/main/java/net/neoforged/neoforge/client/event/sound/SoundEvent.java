package net.neoforged.neoforge.client.event.sound;

import com.mojang.blaze3d.audio.Channel;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class SoundEvent extends Event {

    protected SoundEvent(SoundEngine engine) {
    }

    public SoundEngine getEngine() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/sound/SoundEvent.getEngine:()Lnet/minecraft/client/sounds/SoundEngine;");
    }

    public static abstract class SoundSourceEvent extends SoundEvent {

        protected SoundSourceEvent(SoundEngine engine, SoundInstance sound, Channel channel) {
        }

        public SoundInstance getSound() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/sound/SoundEvent$SoundSourceEvent.getSound:()Lnet/minecraft/client/resources/sounds/SoundInstance;");
        }

        public String getName() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/sound/SoundEvent$SoundSourceEvent.getName:()Ljava/lang/String;");
        }

        public SoundSourceEvent() {
        }
    }

    public SoundEvent() {
    }
}
