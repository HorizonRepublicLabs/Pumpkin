package net.minecraft.client.multiplayer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public class ServerData {

    public ServerData(String name, String ip, ServerData.Type type) {
    }

    public static ServerData read(CompoundTag tag) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ServerData.read:(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/client/multiplayer/ServerData;");
    }

    public ServerData.Type type() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ServerData.type:()Lnet/minecraft/client/multiplayer/ServerData$Type;");
    }

    public enum ServerPackStatus {

        ENABLED, DISABLED, PROMPT;

        public Component getName() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ServerData$ServerPackStatus.getName:()Lnet/minecraft/network/chat/Component;");
        }
    }

    public enum State {

        INITIAL, PINGING, UNREACHABLE, INCOMPATIBLE, SUCCESSFUL
    }

    public enum Type {

        LAN, REALM, OTHER
    }

    public ServerData() {
    }
}
