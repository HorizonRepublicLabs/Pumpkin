package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundStopSoundPacket implements Packet<ClientGamePacketListener> {

    public ClientboundStopSoundPacket(Identifier name, SoundSource source) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundStopSoundPacket.<init>:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/sounds/SoundSource;)V");
    }

    private ClientboundStopSoundPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundStopSoundPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundStopSoundPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundStopSoundPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundStopSoundPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundStopSoundPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public Identifier getName() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundStopSoundPacket.getName:()Lnet/minecraft/resources/Identifier;");
    }

    public SoundSource getSource() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundStopSoundPacket.getSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    public ClientboundStopSoundPacket() {
    }
}
