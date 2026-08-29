package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundEntityEventPacket implements Packet<ClientGamePacketListener> {

    public ClientboundEntityEventPacket(Entity entity, byte eventId) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundEntityEventPacket.<init>:(Lnet/minecraft/world/entity/Entity;B)V");
    }

    private ClientboundEntityEventPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundEntityEventPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundEntityEventPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundEntityEventPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundEntityEventPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundEntityEventPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public Entity getEntity(Level level) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundEntityEventPacket.getEntity:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;");
    }

    protected ClientboundEntityEventPacket() {
    }
}
