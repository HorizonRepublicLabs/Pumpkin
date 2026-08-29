package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundPlayerCommandPacket implements Packet<ServerGamePacketListener> {

    public ServerboundPlayerCommandPacket(Entity entity, ServerboundPlayerCommandPacket.Action action) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerCommandPacket.<init>:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/network/protocol/game/ServerboundPlayerCommandPacket$Action;)V");
    }

    public ServerboundPlayerCommandPacket(Entity entity, ServerboundPlayerCommandPacket.Action action, int data) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerCommandPacket.<init>:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/network/protocol/game/ServerboundPlayerCommandPacket$Action;I)V");
    }

    private ServerboundPlayerCommandPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerCommandPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerCommandPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundPlayerCommandPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerCommandPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerCommandPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerCommandPacket.getId:()I");
    }

    public enum Action {

        STOP_SLEEPING,
        START_SPRINTING,
        STOP_SPRINTING,
        START_RIDING_JUMP,
        STOP_RIDING_JUMP,
        OPEN_INVENTORY,
        START_FALL_FLYING
    }

    public ServerboundPlayerCommandPacket() {
    }
}
