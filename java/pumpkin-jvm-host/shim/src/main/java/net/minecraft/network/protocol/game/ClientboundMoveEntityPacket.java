package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public abstract class ClientboundMoveEntityPacket implements Packet<ClientGamePacketListener> {

    protected ClientboundMoveEntityPacket(int entityId, short xa, short ya, short za, byte yRot, byte xRot, boolean onGround, boolean hasRot, boolean hasPos) {
    }

    public abstract PacketType<? extends ClientboundMoveEntityPacket> type();

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket.toString:()Ljava/lang/String;");
    }

    public Entity getEntity(Level level) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket.getEntity:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;");
    }

    public static class Pos extends ClientboundMoveEntityPacket {

        public Pos(int id, short xa, short ya, short za, boolean onGround) {
        }

        private static ClientboundMoveEntityPacket.Pos read(FriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket$Pos.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/protocol/game/ClientboundMoveEntityPacket$Pos;");
        }

        private void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket$Pos.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public PacketType<ClientboundMoveEntityPacket.Pos> type() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket$Pos.type:()Lnet/minecraft/network/protocol/PacketType;");
        }

        public Pos() {
        }
    }

    public static class PosRot extends ClientboundMoveEntityPacket {

        public PosRot(int id, short xa, short ya, short za, byte yRot, byte xRot, boolean onGround) {
        }

        private static ClientboundMoveEntityPacket.PosRot read(FriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket$PosRot.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/protocol/game/ClientboundMoveEntityPacket$PosRot;");
        }

        private void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket$PosRot.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public PacketType<ClientboundMoveEntityPacket.PosRot> type() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket$PosRot.type:()Lnet/minecraft/network/protocol/PacketType;");
        }

        public PosRot() {
        }
    }

    public static class Rot extends ClientboundMoveEntityPacket {

        public Rot(int id, byte yRot, byte xRot, boolean onGround) {
        }

        private static ClientboundMoveEntityPacket.Rot read(FriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket$Rot.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/protocol/game/ClientboundMoveEntityPacket$Rot;");
        }

        private void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket$Rot.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public PacketType<ClientboundMoveEntityPacket.Rot> type() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveEntityPacket$Rot.type:()Lnet/minecraft/network/protocol/PacketType;");
        }

        public Rot() {
        }
    }

    public ClientboundMoveEntityPacket() {
    }
}
