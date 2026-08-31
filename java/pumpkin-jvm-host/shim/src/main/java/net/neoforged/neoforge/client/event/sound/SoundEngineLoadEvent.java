package net.neoforged.neoforge.client.event.sound;

import net.minecraft.client.sounds.SoundEngine;
import net.neoforged.fml.event.IModBusEvent;

public class SoundEngineLoadEvent extends SoundEvent implements IModBusEvent {

    public SoundEngineLoadEvent(SoundEngine manager) {
    }

    public SoundEngineLoadEvent() {
    }
}
