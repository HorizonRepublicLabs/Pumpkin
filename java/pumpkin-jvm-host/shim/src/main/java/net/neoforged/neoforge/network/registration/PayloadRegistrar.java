package net.neoforged.neoforge.network.registration;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import dev.pumpkin.shim.Unimplemented;

public class PayloadRegistrar {

    public PayloadRegistrar(String version) {
    }

    private PayloadRegistrar(PayloadRegistrar source) {
    }

    public <T extends CustomPacketPayload> PayloadRegistrar playToClient(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, IPayloadHandler<T> handler) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/registration/PayloadRegistrar.playToClient:(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload$Type;Lnet/minecraft/network/codec/StreamCodec;Lnet/neoforged/neoforge/network/handling/IPayloadHandler;)Lnet/neoforged/neoforge/network/registration/PayloadRegistrar;");
    }

    public <T extends CustomPacketPayload> PayloadRegistrar playToClient(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/registration/PayloadRegistrar.playToClient:(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload$Type;Lnet/minecraft/network/codec/StreamCodec;)Lnet/neoforged/neoforge/network/registration/PayloadRegistrar;");
    }

    public <T extends CustomPacketPayload> PayloadRegistrar playToServer(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, IPayloadHandler<T> handler) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/registration/PayloadRegistrar.playToServer:(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload$Type;Lnet/minecraft/network/codec/StreamCodec;Lnet/neoforged/neoforge/network/handling/IPayloadHandler;)Lnet/neoforged/neoforge/network/registration/PayloadRegistrar;");
    }

    public PayloadRegistrar() {
    }
}
