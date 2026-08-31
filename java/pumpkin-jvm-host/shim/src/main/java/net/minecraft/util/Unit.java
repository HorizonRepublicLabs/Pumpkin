package net.minecraft.util;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Stubs;

public enum Unit {

    INSTANCE;

    public static final Codec<Unit> CODEC = null;

    public static final StreamCodec<ByteBuf, Unit> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");
}
