package net.neoforged.neoforge.network.registration;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

// Pumpkin divergence: the whole class. Pinned (generator/pinned/), so edit that copy too.
//
// Each registration becomes a channel the server negotiates with NeoForge clients, through
// a sink Bootstrap points at PumpkinHost::declareNetworkChannel -- the shim cannot depend
// on host. A mod's payloads are required on its client, so without this every client
// carrying the mod is refused. Codecs and handlers are dropped: Pumpkin does not deliver
// mod payloads yet, and a channel can be agreed without them.
public class PayloadRegistrar {

    /** Where a registered payload is declared. */
    @FunctionalInterface
    public interface Sink {
        void declare(String id, String phase, String version, boolean serverbound);
    }

    private static Sink sink = (id, phase, version, serverbound) -> {
    };

    public static void setSink(Sink sink) {
        PayloadRegistrar.sink = sink;
    }

    private final String version;

    public PayloadRegistrar(String version) {
        this.version = version;
    }

    private PayloadRegistrar(PayloadRegistrar source) {
        this.version = source.version;
    }

    public PayloadRegistrar() {
        this("1");
    }

    private PayloadRegistrar declare(CustomPacketPayload.Type<?> type, String phase, boolean serverbound) {
        sink.declare(type.id().toString(), phase, version, serverbound);
        return this;
    }

    public <T extends CustomPacketPayload> PayloadRegistrar playToClient(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, IPayloadHandler<T> handler) {
        return declare(type, "play", false);
    }

    public <T extends CustomPacketPayload> PayloadRegistrar playToClient(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        return declare(type, "play", false);
    }

    public <T extends CustomPacketPayload> PayloadRegistrar playToServer(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, IPayloadHandler<T> handler) {
        return declare(type, "play", true);
    }

    public <T extends CustomPacketPayload> PayloadRegistrar configurationToClient(CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf, T> codec, IPayloadHandler<T> handler) {
        return declare(type, "configuration", false);
    }

    public <T extends CustomPacketPayload> PayloadRegistrar configurationToClient(CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf, T> codec) {
        return declare(type, "configuration", false);
    }

    public <T extends CustomPacketPayload> PayloadRegistrar configurationToServer(CustomPacketPayload.Type<T> type, StreamCodec<? super FriendlyByteBuf, T> codec, IPayloadHandler<T> handler) {
        return declare(type, "configuration", true);
    }
}
