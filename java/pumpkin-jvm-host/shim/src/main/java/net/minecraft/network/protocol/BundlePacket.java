package net.minecraft.network.protocol;

import net.minecraft.network.PacketListener;
import dev.pumpkin.shim.Unimplemented;

public abstract class BundlePacket<T extends PacketListener> implements Packet<T> {

    protected BundlePacket(Iterable<Packet<? super T>> packets) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/BundlePacket.<init>:(Ljava/lang/Iterable;)V");
    }

    public abstract PacketType<? extends BundlePacket<T>> type();

    public BundlePacket() {
    }
}
