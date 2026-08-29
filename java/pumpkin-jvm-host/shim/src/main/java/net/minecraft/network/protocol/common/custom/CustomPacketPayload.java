package net.minecraft.network.protocol.common.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

public interface CustomPacketPayload {

    CustomPacketPayload.Type<? extends CustomPacketPayload> type();

    interface FallbackProvider<B extends FriendlyByteBuf> {

        StreamCodec<B, ? extends CustomPacketPayload> create(Identifier typeId);
    }

    record Type<T extends CustomPacketPayload>(Identifier id) {
    }

    record TypeAndCodec<B extends FriendlyByteBuf, T extends CustomPacketPayload>(CustomPacketPayload.Type<T> type, StreamCodec<B, T> codec) {
    }
}
