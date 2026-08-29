package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetExperiencePacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetExperiencePacket(float experienceProgress, int totalExperience, int experienceLevel) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetExperiencePacket.<init>:(FII)V");
    }

    private ClientboundSetExperiencePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetExperiencePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetExperiencePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetExperiencePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetExperiencePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetExperiencePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundSetExperiencePacket() {
    }
}
