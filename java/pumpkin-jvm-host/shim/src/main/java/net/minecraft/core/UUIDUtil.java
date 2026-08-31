package net.minecraft.core;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public final class UUIDUtil {

    public static final Codec<UUID> CODEC = null;

    public static final Codec<UUID> STRING_CODEC = null;

    public static final Codec<UUID> LENIENT_CODEC = null;

    public static final StreamCodec<ByteBuf, UUID> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    protected UUIDUtil() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/core/UUIDUtil");
        }
    }
}
