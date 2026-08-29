package net.minecraft.network;

import net.minecraft.network.protocol.Packet;
import net.neoforged.neoforge.network.handling.QueuedPacket;
import dev.pumpkin.shim.Unimplemented;

public class PacketProcessor implements AutoCloseable {

    public PacketProcessor(Thread runningThread) {
        throw Unimplemented.forMember("net/minecraft/network/PacketProcessor.<init>:(Ljava/lang/Thread;)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/network/PacketProcessor.close:()V");
    }

    private record ListenerAndPacket<T extends PacketListener>(T listener, Packet<T> packet) implements QueuedPacket {

        public void handle() {
            throw Unimplemented.forMember("net/minecraft/network/PacketProcessor$ListenerAndPacket.handle:()V");
        }
    }

    protected PacketProcessor() {
    }
}
