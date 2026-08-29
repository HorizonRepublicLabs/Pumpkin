package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetCameraPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetCameraPacket(Entity camera) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetCameraPacket.<init>:(Lnet/minecraft/world/entity/Entity;)V");
    }

    private ClientboundSetCameraPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetCameraPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetCameraPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetCameraPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetCameraPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetCameraPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public Entity getEntity(Level level) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetCameraPacket.getEntity:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;");
    }

    public ClientboundSetCameraPacket() {
    }
}
