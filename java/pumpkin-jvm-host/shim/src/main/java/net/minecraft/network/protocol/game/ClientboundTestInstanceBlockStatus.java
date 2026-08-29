package net.minecraft.network.protocol.game;

import java.util.Optional;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundTestInstanceBlockStatus(Component status, Optional<Vec3i> size) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundTestInstanceBlockStatus> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTestInstanceBlockStatus.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTestInstanceBlockStatus.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
