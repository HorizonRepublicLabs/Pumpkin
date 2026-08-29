package net.neoforged.neoforge.network.handling;

import dev.pumpkin.shim.Unimplemented;

public interface QueuedPacket {

    void handle();

    record CustomPayload(Runnable task) implements QueuedPacket {

        public void handle() {
            throw Unimplemented.forMember("net/neoforged/neoforge/network/handling/QueuedPacket$CustomPayload.handle:()V");
        }
    }
}
