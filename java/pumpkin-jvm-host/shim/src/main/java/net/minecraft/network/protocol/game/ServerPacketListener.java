package net.minecraft.network.protocol.game;

import net.minecraft.ReportedException;
import net.minecraft.network.ServerboundPacketListener;
import net.minecraft.network.protocol.Packet;
import dev.pumpkin.shim.Unimplemented;

public interface ServerPacketListener extends ServerboundPacketListener {

    default void onPacketError(Packet packet, Exception e) throws ReportedException {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerPacketListener.onPacketError:(Lnet/minecraft/network/protocol/Packet;Ljava/lang/Exception;)V");
    }
}
