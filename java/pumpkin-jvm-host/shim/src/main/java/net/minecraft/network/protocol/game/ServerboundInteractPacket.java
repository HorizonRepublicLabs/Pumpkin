package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundInteractPacket(int entityId, InteractionHand hand, Vec3 location, boolean usingSecondaryAction) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundInteractPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundInteractPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundInteractPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
