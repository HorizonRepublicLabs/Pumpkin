package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.RegistryAccess;
import dev.pumpkin.shim.Unimplemented;

public class RegistryFriendlyByteBuf extends FriendlyByteBuf {

    public RegistryFriendlyByteBuf(ByteBuf source, RegistryAccess registryAccess) {
        throw Unimplemented.forMember("net/minecraft/network/RegistryFriendlyByteBuf.<init>:(Lio/netty/buffer/ByteBuf;Lnet/minecraft/core/RegistryAccess;)V");
    }

    public RegistryFriendlyByteBuf(ByteBuf source, RegistryAccess registryAccess, net.neoforged.neoforge.network.connection.ConnectionType connectionType) {
        throw Unimplemented.forMember("net/minecraft/network/RegistryFriendlyByteBuf.<init>:(Lio/netty/buffer/ByteBuf;Lnet/minecraft/core/RegistryAccess;Lnet/neoforged/neoforge/network/connection/ConnectionType;)V");
    }

    public net.neoforged.neoforge.network.connection.ConnectionType getConnectionType() {
        throw Unimplemented.forMember("net/minecraft/network/RegistryFriendlyByteBuf.getConnectionType:()Lnet/neoforged/neoforge/network/connection/ConnectionType;");
    }

    public RegistryAccess registryAccess() {
        throw Unimplemented.forMember("net/minecraft/network/RegistryFriendlyByteBuf.registryAccess:()Lnet/minecraft/core/RegistryAccess;");
    }

    protected RegistryFriendlyByteBuf() {
    }
}
