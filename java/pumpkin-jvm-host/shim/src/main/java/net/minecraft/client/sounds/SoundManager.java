package net.minecraft.client.sounds;

import java.util.Collection;
import java.util.Map;
import net.minecraft.client.Options;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import dev.pumpkin.shim.Unimplemented;

public class SoundManager extends SimplePreparableReloadListener<SoundManager.Preparations> {

    public SoundManager(Options options) {
    }

    protected SoundManager.Preparations prepare(ResourceManager manager, ProfilerFiller profiler) {
        throw Unimplemented.forMember("net/minecraft/client/sounds/SoundManager.prepare:(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Lnet/minecraft/client/sounds/SoundManager$Preparations;");
    }

    protected void apply(SoundManager.Preparations preparations, ResourceManager manager, ProfilerFiller profiler) {
        throw Unimplemented.forMember("net/minecraft/client/sounds/SoundManager.apply:(Lnet/minecraft/client/sounds/SoundManager$Preparations;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V");
    }

    public Collection<Identifier> getAvailableSounds() {
        throw Unimplemented.forMember("net/minecraft/client/sounds/SoundManager.getAvailableSounds:()Ljava/util/Collection;");
    }

    public SoundEngine.PlayResult play(SoundInstance instance) {
        throw Unimplemented.forMember("net/minecraft/client/sounds/SoundManager.play:(Lnet/minecraft/client/resources/sounds/SoundInstance;)Lnet/minecraft/client/sounds/SoundEngine$PlayResult;");
    }

    public boolean isActive(SoundInstance instance) {
        throw Unimplemented.forMember("net/minecraft/client/sounds/SoundManager.isActive:(Lnet/minecraft/client/resources/sounds/SoundInstance;)Z");
    }

    protected static class Preparations {

        public void apply(Map<Identifier, WeighedSoundEvents> registry, Map<Identifier, Resource> soundCache, SoundEngine engine) {
            throw Unimplemented.forMember("net/minecraft/client/sounds/SoundManager$Preparations.apply:(Ljava/util/Map;Ljava/util/Map;Lnet/minecraft/client/sounds/SoundEngine;)V");
        }

        protected Preparations() {
        }
    }

    public SoundManager() {
    }
}
