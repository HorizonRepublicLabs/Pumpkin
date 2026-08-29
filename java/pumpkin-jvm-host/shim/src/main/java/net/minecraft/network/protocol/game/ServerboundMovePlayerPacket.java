package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class ServerboundMovePlayerPacket implements Packet<ServerGamePacketListener> {

    protected ServerboundMovePlayerPacket(double x, double y, double z, float yRot, float xRot, boolean onGround, boolean horizontalCollision, boolean hasPos, boolean hasRot) {
    }

    public abstract PacketType<? extends ServerboundMovePlayerPacket> type();

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public static class Pos extends ServerboundMovePlayerPacket {

        public Pos(Vec3 pos, boolean onGround, boolean horizontalCollision) {
        }

        public Pos(double x, double y, double z, boolean onGround, boolean horizontalCollision) {
        }

        private static ServerboundMovePlayerPacket.Pos read(FriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$Pos.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/protocol/game/ServerboundMovePlayerPacket$Pos;");
        }

        private void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$Pos.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public PacketType<ServerboundMovePlayerPacket.Pos> type() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$Pos.type:()Lnet/minecraft/network/protocol/PacketType;");
        }

        public Pos() {
        }
    }

    public static class PosRot extends ServerboundMovePlayerPacket {

        public PosRot(Vec3 pos, float yRot, float xRot, boolean onGround, boolean horizontalCollision) {
        }

        public PosRot(double x, double y, double z, float yRot, float xRot, boolean onGround, boolean horizontalCollision) {
        }

        private static ServerboundMovePlayerPacket.PosRot read(FriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$PosRot.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/protocol/game/ServerboundMovePlayerPacket$PosRot;");
        }

        private void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$PosRot.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public PacketType<ServerboundMovePlayerPacket.PosRot> type() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$PosRot.type:()Lnet/minecraft/network/protocol/PacketType;");
        }

        public PosRot() {
        }
    }

    public static class Rot extends ServerboundMovePlayerPacket {

        public Rot(float yRot, float xRot, boolean onGround, boolean horizontalCollision) {
        }

        private static ServerboundMovePlayerPacket.Rot read(FriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$Rot.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/protocol/game/ServerboundMovePlayerPacket$Rot;");
        }

        private void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$Rot.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public PacketType<ServerboundMovePlayerPacket.Rot> type() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$Rot.type:()Lnet/minecraft/network/protocol/PacketType;");
        }

        public Rot() {
        }
    }

    public static class StatusOnly extends ServerboundMovePlayerPacket {

        public StatusOnly(boolean onGround, boolean horizontalCollision) {
        }

        private static ServerboundMovePlayerPacket.StatusOnly read(FriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$StatusOnly.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/protocol/game/ServerboundMovePlayerPacket$StatusOnly;");
        }

        private void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$StatusOnly.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public PacketType<ServerboundMovePlayerPacket.StatusOnly> type() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundMovePlayerPacket$StatusOnly.type:()Lnet/minecraft/network/protocol/PacketType;");
        }

        public StatusOnly() {
        }
    }

    public ServerboundMovePlayerPacket() {
    }
}
