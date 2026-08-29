package net.neoforged.neoforge.common.extensions;

import java.util.Map;
import java.util.function.BiFunction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamDecoder;
import net.minecraft.network.codec.StreamEncoder;
import org.apache.commons.lang3.function.TriConsumer;
import dev.pumpkin.shim.Unimplemented;

public interface IFriendlyByteBufExtension {

    default <K, V> Map<K, V> readMap(StreamDecoder<? super FriendlyByteBuf, K> keyReader, BiFunction<FriendlyByteBuf, K, V> valueReader) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IFriendlyByteBufExtension.readMap:(Lnet/minecraft/network/codec/StreamDecoder;Ljava/util/function/BiFunction;)Ljava/util/Map;");
    }

    default <K, V> void writeMap(Map<K, V> map, StreamEncoder<? super FriendlyByteBuf, K> keyWriter, TriConsumer<FriendlyByteBuf, K, V> valueWriter) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IFriendlyByteBufExtension.writeMap:(Ljava/util/Map;Lnet/minecraft/network/codec/StreamEncoder;Lorg/apache/commons/lang3/function/TriConsumer;)V");
    }
}
