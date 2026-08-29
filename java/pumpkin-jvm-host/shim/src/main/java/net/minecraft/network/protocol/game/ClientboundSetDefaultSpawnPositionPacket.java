package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.storage.LevelData;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSetDefaultSpawnPositionPacket(LevelData.RespawnData respawnData) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundSetDefaultSpawnPositionPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetDefaultSpawnPositionPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetDefaultSpawnPositionPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
