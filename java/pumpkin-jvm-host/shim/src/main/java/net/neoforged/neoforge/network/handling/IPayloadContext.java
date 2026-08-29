package net.neoforged.neoforge.network.handling;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.network.ConfigurationTask;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.extensions.ICommonPacketListener;
import dev.pumpkin.shim.Unimplemented;

public interface IPayloadContext {

    ICommonPacketListener listener();

    Player player();

    default void disconnect(Component reason) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/handling/IPayloadContext.disconnect:(Lnet/minecraft/network/chat/Component;)V");
    }

    CompletableFuture<Void> enqueueWork(Runnable task);

    <T> CompletableFuture<T> enqueueWork(Supplier<T> task);

    PacketFlow flow();

    default ConnectionProtocol protocol() {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/handling/IPayloadContext.protocol:()Lnet/minecraft/network/ConnectionProtocol;");
    }

    default void handle(Packet<?> packet) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/handling/IPayloadContext.handle:(Lnet/minecraft/network/protocol/Packet;)V");
    }

    void handle(CustomPacketPayload payload);

    void finishCurrentTask(ConfigurationTask.Type type);
}
