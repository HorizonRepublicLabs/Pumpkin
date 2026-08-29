package net.minecraft.network.protocol;

import java.util.function.Consumer;

public interface BundlerInfo {

    void unbundlePacket(Packet<?> packet, Consumer<Packet<?>> output);

    BundlerInfo.Bundler startPacketBundling(Packet<?> packet);

    interface Bundler {

        Packet<?> addPacket(Packet<?> packet);
    }
}
