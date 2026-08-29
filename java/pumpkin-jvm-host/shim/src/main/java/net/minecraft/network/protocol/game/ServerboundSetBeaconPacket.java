package net.minecraft.network.protocol.game;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.effect.MobEffect;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundSetBeaconPacket(Optional<Holder<MobEffect>> primary, Optional<Holder<MobEffect>> secondary) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundSetBeaconPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetBeaconPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetBeaconPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
