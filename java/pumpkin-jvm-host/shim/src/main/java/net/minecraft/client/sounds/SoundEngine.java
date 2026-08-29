package net.minecraft.client.sounds;

import net.minecraft.client.Options;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.server.packs.resources.ResourceProvider;
import dev.pumpkin.shim.Unimplemented;

public class SoundEngine {

    public SoundEngine(SoundManager soundManager, Options options, ResourceProvider resourceProvider) {
    }

    public boolean isActive(SoundInstance instance) {
        throw Unimplemented.forMember("net/minecraft/client/sounds/SoundEngine.isActive:(Lnet/minecraft/client/resources/sounds/SoundInstance;)Z");
    }

    public enum PlayResult {

        STARTED, STARTED_SILENTLY, NOT_STARTED
    }

    public SoundEngine() {
    }
}
