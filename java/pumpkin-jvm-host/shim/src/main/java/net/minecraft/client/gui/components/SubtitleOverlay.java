package net.minecraft.client.gui.components;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEventListener;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class SubtitleOverlay implements SoundEventListener {

    private final List<SubtitleOverlay.Subtitle> audibleSubtitles = null;

    public SubtitleOverlay(Minecraft minecraft) {
    }

    public void onPlaySound(SoundInstance sound, WeighedSoundEvents soundEvent, float range) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/SubtitleOverlay.onPlaySound:(Lnet/minecraft/client/resources/sounds/SoundInstance;Lnet/minecraft/client/sounds/WeighedSoundEvents;F)V");
    }

    private record SoundPlayedAt(Vec3 location, long time) {
    }

    private static class Subtitle {

        public Subtitle(Component text, float range, Vec3 location) {
        }

        public Component getText() {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/SubtitleOverlay$Subtitle.getText:()Lnet/minecraft/network/chat/Component;");
        }

        protected Subtitle() {
        }
    }

    public SubtitleOverlay() {
    }
}
