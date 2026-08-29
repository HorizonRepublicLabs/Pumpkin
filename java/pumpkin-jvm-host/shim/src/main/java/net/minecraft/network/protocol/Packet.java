package net.minecraft.network.protocol;

import net.minecraft.network.PacketListener;

public interface Packet<T extends PacketListener> {

    PacketType<? extends Packet<T>> type();

    void handle(T listener);
}
