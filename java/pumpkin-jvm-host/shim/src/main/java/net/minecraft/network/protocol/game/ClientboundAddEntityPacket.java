package net.minecraft.network.protocol.game;

import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundAddEntityPacket implements Packet<ClientGamePacketListener> {

    public ClientboundAddEntityPacket(Entity entity, ServerEntity serverEntity) {
    }

    public ClientboundAddEntityPacket(Entity entity, ServerEntity serverEntity, int data) {
    }

    public ClientboundAddEntityPacket(Entity entity, int data, BlockPos pos) {
    }

    public ClientboundAddEntityPacket(int id, UUID uuid, double x, double y, double z, float xRot, float yRot, EntityType<?> type, int data, Vec3 movement, double yHeadRot) {
    }

    private ClientboundAddEntityPacket(RegistryFriendlyByteBuf input) {
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAddEntityPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundAddEntityPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAddEntityPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAddEntityPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAddEntityPacket.getId:()I");
    }

    public UUID getUUID() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAddEntityPacket.getUUID:()Ljava/util/UUID;");
    }

    public EntityType<?> getType() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAddEntityPacket.getType:()Lnet/minecraft/world/entity/EntityType;");
    }

    public double getX() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAddEntityPacket.getX:()D");
    }

    public double getY() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAddEntityPacket.getY:()D");
    }

    public double getZ() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAddEntityPacket.getZ:()D");
    }

    public ClientboundAddEntityPacket() {
    }
}
