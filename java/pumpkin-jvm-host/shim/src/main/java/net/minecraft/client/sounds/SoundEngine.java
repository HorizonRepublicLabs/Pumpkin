package net.minecraft.client.sounds;

import java.util.List;
import java.util.Map;
import net.minecraft.client.Options;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.server.packs.resources.ResourceProvider;
import dev.pumpkin.shim.Unimplemented;

public class SoundEngine {

    private final Map<SoundInstance, ChannelAccess.ChannelHandle> instanceToChannel = null;

    private final List<SoundEventListener> listeners = null;

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
