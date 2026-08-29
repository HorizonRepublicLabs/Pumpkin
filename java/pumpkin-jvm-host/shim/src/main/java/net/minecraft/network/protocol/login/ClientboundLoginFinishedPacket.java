package net.minecraft.network.protocol.login;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundLoginFinishedPacket(GameProfile gameProfile, UUID sessionId) implements Packet<ClientLoginPacketListener> {

    public PacketType<ClientboundLoginFinishedPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundLoginFinishedPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientLoginPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundLoginFinishedPacket.handle:(Lnet/minecraft/network/protocol/login/ClientLoginPacketListener;)V");
    }

    public boolean isTerminal() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientboundLoginFinishedPacket.isTerminal:()Z");
    }
}
