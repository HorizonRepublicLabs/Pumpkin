package net.minecraft.network.protocol.common;

import java.util.UUID;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundResourcePackPacket(UUID id, ServerboundResourcePackPacket.Action action) implements Packet<ServerCommonPacketListener> {

    private ServerboundResourcePackPacket(FriendlyByteBuf input) {
        this((UUID) null, (ServerboundResourcePackPacket.Action) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundResourcePackPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundResourcePackPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundResourcePackPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundResourcePackPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundResourcePackPacket.handle:(Lnet/minecraft/network/protocol/common/ServerCommonPacketListener;)V");
    }

    public enum Action {

        SUCCESSFULLY_LOADED,
        DECLINED,
        FAILED_DOWNLOAD,
        ACCEPTED,
        DOWNLOADED,
        INVALID_URL,
        FAILED_RELOAD,
        DISCARDED
    }
}
