package net.neoforged.neoforge.client;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import dev.pumpkin.shim.Unimplemented;

public class ClientHooks {

    public static SoundInstance playSound(SoundEngine manager, SoundInstance sound) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/ClientHooks.playSound:(Lnet/minecraft/client/sounds/SoundEngine;Lnet/minecraft/client/resources/sounds/SoundInstance;)Lnet/minecraft/client/resources/sounds/SoundInstance;");
    }

    public ClientHooks() {
    }
}
