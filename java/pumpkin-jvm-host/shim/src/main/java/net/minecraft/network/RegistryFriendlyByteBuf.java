package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.RegistryAccess;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class RegistryFriendlyByteBuf extends FriendlyByteBuf {

    private final RegistryAccess registryAccess = Stubs.of(RegistryAccess.class, "net/minecraft/core/RegistryAccess");

    public RegistryFriendlyByteBuf(ByteBuf source, RegistryAccess registryAccess) {
    }

    public RegistryFriendlyByteBuf(ByteBuf source, RegistryAccess registryAccess, net.neoforged.neoforge.network.connection.ConnectionType connectionType) {
    }

    public net.neoforged.neoforge.network.connection.ConnectionType getConnectionType() {
        throw Unimplemented.forMember("net/minecraft/network/RegistryFriendlyByteBuf.getConnectionType:()Lnet/neoforged/neoforge/network/connection/ConnectionType;");
    }

    public RegistryAccess registryAccess() {
        throw Unimplemented.forMember("net/minecraft/network/RegistryFriendlyByteBuf.registryAccess:()Lnet/minecraft/core/RegistryAccess;");
    }

    public RegistryFriendlyByteBuf() {
    }
}
