package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundMoveVehiclePacket(Vec3 position, float yRot, float xRot, boolean onGround) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundMoveVehiclePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMoveVehiclePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMoveVehiclePacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
