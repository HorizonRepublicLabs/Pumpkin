package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundRenameItemPacket implements Packet<ServerGamePacketListener> {

    public ServerboundRenameItemPacket(String name) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundRenameItemPacket.<init>:(Ljava/lang/String;)V");
    }

    private ServerboundRenameItemPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundRenameItemPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundRenameItemPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundRenameItemPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundRenameItemPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundRenameItemPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundRenameItemPacket.getName:()Ljava/lang/String;");
    }

    public ServerboundRenameItemPacket() {
    }
}
