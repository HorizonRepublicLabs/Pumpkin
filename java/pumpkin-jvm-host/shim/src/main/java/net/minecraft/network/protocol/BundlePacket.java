package net.minecraft.network.protocol;

import net.minecraft.network.PacketListener;

public abstract class BundlePacket<T extends PacketListener> implements Packet<T> {

    protected BundlePacket(Iterable<Packet<? super T>> packets) {
    }

    public abstract PacketType<? extends BundlePacket<T>> type();

    public BundlePacket() {
    }
}
