package net.minecraft.network.protocol.game;

import java.util.UUID;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundTeleportToEntityPacket implements Packet<ServerGamePacketListener> {

    public ServerboundTeleportToEntityPacket(UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundTeleportToEntityPacket.<init>:(Ljava/util/UUID;)V");
    }

    private ServerboundTeleportToEntityPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundTeleportToEntityPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundTeleportToEntityPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundTeleportToEntityPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundTeleportToEntityPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundTeleportToEntityPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public Entity getEntity(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundTeleportToEntityPacket.getEntity:(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraft/world/entity/Entity;");
    }

    protected ServerboundTeleportToEntityPacket() {
    }
}
