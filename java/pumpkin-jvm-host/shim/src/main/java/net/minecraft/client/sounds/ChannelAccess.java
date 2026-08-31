package net.minecraft.client.sounds;

import com.mojang.blaze3d.audio.Channel;
import com.mojang.blaze3d.audio.Library;
import java.util.concurrent.Executor;
import dev.pumpkin.shim.Unimplemented;

public class ChannelAccess {

    public ChannelAccess(Library library, Executor executor) {
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/client/sounds/ChannelAccess.clear:()V");
    }

    public class ChannelHandle {

        public boolean isStopped() {
            throw Unimplemented.forMember("net/minecraft/client/sounds/ChannelAccess$ChannelHandle.isStopped:()Z");
        }

        public ChannelHandle(Channel channel) {
        }

        public void release() {
            throw Unimplemented.forMember("net/minecraft/client/sounds/ChannelAccess$ChannelHandle.release:()V");
        }

        public ChannelHandle() {
        }
    }

    public ChannelAccess() {
    }
}
