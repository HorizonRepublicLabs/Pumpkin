package net.minecraft.core;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public final class UUIDUtil {

    public static final Codec<UUID> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/UUIDUtil.CODEC");

    public static final Codec<UUID> STRING_CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/UUIDUtil.STRING_CODEC");

    public static final Codec<UUID> LENIENT_CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/UUIDUtil.LENIENT_CODEC");

    public static final StreamCodec<ByteBuf, UUID> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    protected UUIDUtil() {
    }

    // Pumpkin divergence: no throwing initializer -- the codecs answer inertly.
}
