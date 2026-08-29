package net.minecraft.network;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ByteProcessor;
import it.unimi.dsi.fastutil.ints.IntList;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.time.Instant;
import java.util.BitSet;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.Tag;
import net.minecraft.network.codec.StreamDecoder;
import net.minecraft.network.codec.StreamEncoder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.BlockHitResult;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import net.neoforged.neoforge.common.extensions.IFriendlyByteBufExtension;
import dev.pumpkin.shim.Unimplemented;

public class FriendlyByteBuf extends ByteBuf implements IFriendlyByteBufExtension {

    public FriendlyByteBuf(ByteBuf source) {
    }

    public <T> T readWithCodecTrusted(DynamicOps<Tag> ops, Codec<T> codec) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readWithCodecTrusted:(Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/Codec;)Ljava/lang/Object;");
    }

    public <T> T readWithCodec(DynamicOps<Tag> ops, Codec<T> codec, NbtAccounter accounter) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readWithCodec:(Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/Codec;Lnet/minecraft/nbt/NbtAccounter;)Ljava/lang/Object;");
    }

    public <T> FriendlyByteBuf writeWithCodec(DynamicOps<Tag> ops, Codec<T> codec, T value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeWithCodec:(Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/Codec;Ljava/lang/Object;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public <T> T readLenientJsonWithCodec(Codec<T> codec) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readLenientJsonWithCodec:(Lcom/mojang/serialization/Codec;)Ljava/lang/Object;");
    }

    public <T> void writeJsonWithCodec(Codec<T> codec, T value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeJsonWithCodec:(Lcom/mojang/serialization/Codec;Ljava/lang/Object;)V");
    }

    public static <T> IntFunction<T> limitValue(IntFunction<T> original, int limit) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.limitValue:(Ljava/util/function/IntFunction;I)Ljava/util/function/IntFunction;");
    }

    public <T, C extends Collection<T>> C readCollection(IntFunction<C> ctor, StreamDecoder<? super FriendlyByteBuf, T> elementDecoder) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readCollection:(Ljava/util/function/IntFunction;Lnet/minecraft/network/codec/StreamDecoder;)Ljava/util/Collection;");
    }

    public <T> void writeCollection(Collection<T> collection, StreamEncoder<? super FriendlyByteBuf, T> encoder) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeCollection:(Ljava/util/Collection;Lnet/minecraft/network/codec/StreamEncoder;)V");
    }

    public <T> List<T> readList(StreamDecoder<? super FriendlyByteBuf, T> elementDecoder) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readList:(Lnet/minecraft/network/codec/StreamDecoder;)Ljava/util/List;");
    }

    public IntList readIntIdList() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readIntIdList:()Lit/unimi/dsi/fastutil/ints/IntList;");
    }

    public void writeIntIdList(IntList ids) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeIntIdList:(Lit/unimi/dsi/fastutil/ints/IntList;)V");
    }

    public <K, V, M extends Map<K, V>> M readMap(IntFunction<M> ctor, StreamDecoder<? super FriendlyByteBuf, K> keyDecoder, StreamDecoder<? super FriendlyByteBuf, V> valueDecoder) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readMap:(Ljava/util/function/IntFunction;Lnet/minecraft/network/codec/StreamDecoder;Lnet/minecraft/network/codec/StreamDecoder;)Ljava/util/Map;");
    }

    public <K, V> Map<K, V> readMap(StreamDecoder<? super FriendlyByteBuf, K> keyDecoder, StreamDecoder<? super FriendlyByteBuf, V> valueDecoder) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readMap:(Lnet/minecraft/network/codec/StreamDecoder;Lnet/minecraft/network/codec/StreamDecoder;)Ljava/util/Map;");
    }

    public <K, V> void writeMap(Map<K, V> map, StreamEncoder<? super FriendlyByteBuf, K> keyEncoder, StreamEncoder<? super FriendlyByteBuf, V> valueEncoder) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeMap:(Ljava/util/Map;Lnet/minecraft/network/codec/StreamEncoder;Lnet/minecraft/network/codec/StreamEncoder;)V");
    }

    public void readWithCount(Consumer<FriendlyByteBuf> reader) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readWithCount:(Ljava/util/function/Consumer;)V");
    }

    public <E extends Enum<E>> void writeEnumSet(EnumSet<E> set, Class<E> clazz) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeEnumSet:(Ljava/util/EnumSet;Ljava/lang/Class;)V");
    }

    public <E extends Enum<E>> EnumSet<E> readEnumSet(Class<E> clazz) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readEnumSet:(Ljava/lang/Class;)Ljava/util/EnumSet;");
    }

    public <T> void writeOptional(Optional<T> value, StreamEncoder<? super FriendlyByteBuf, T> valueWriter) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeOptional:(Ljava/util/Optional;Lnet/minecraft/network/codec/StreamEncoder;)V");
    }

    public <T> Optional<T> readOptional(StreamDecoder<? super FriendlyByteBuf, T> valueReader) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readOptional:(Lnet/minecraft/network/codec/StreamDecoder;)Ljava/util/Optional;");
    }

    public <L, R> void writeEither(Either<L, R> value, StreamEncoder<? super FriendlyByteBuf, L> leftWriter, StreamEncoder<? super FriendlyByteBuf, R> rightWriter) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeEither:(Lcom/mojang/datafixers/util/Either;Lnet/minecraft/network/codec/StreamEncoder;Lnet/minecraft/network/codec/StreamEncoder;)V");
    }

    public <L, R> Either<L, R> readEither(StreamDecoder<? super FriendlyByteBuf, L> leftReader, StreamDecoder<? super FriendlyByteBuf, R> rightReader) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readEither:(Lnet/minecraft/network/codec/StreamDecoder;Lnet/minecraft/network/codec/StreamDecoder;)Lcom/mojang/datafixers/util/Either;");
    }

    public <T> T readNullable(StreamDecoder<? super FriendlyByteBuf, T> valueDecoder) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readNullable:(Lnet/minecraft/network/codec/StreamDecoder;)Ljava/lang/Object;");
    }

    public static <T, B extends ByteBuf> T readNullable(B input, StreamDecoder<? super B, T> valueDecoder) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readNullable:(Lio/netty/buffer/ByteBuf;Lnet/minecraft/network/codec/StreamDecoder;)Ljava/lang/Object;");
    }

    public <T> void writeNullable(T value, StreamEncoder<? super FriendlyByteBuf, T> valueEncoder) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeNullable:(Ljava/lang/Object;Lnet/minecraft/network/codec/StreamEncoder;)V");
    }

    public static <T, B extends ByteBuf> void writeNullable(B output, T value, StreamEncoder<? super B, T> valueEncoder) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeNullable:(Lio/netty/buffer/ByteBuf;Ljava/lang/Object;Lnet/minecraft/network/codec/StreamEncoder;)V");
    }

    public byte[] readByteArray() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readByteArray:()[B");
    }

    public static byte[] readByteArray(ByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readByteArray:(Lio/netty/buffer/ByteBuf;)[B");
    }

    public FriendlyByteBuf writeByteArray(byte[] bytes) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeByteArray:([B)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public static void writeByteArray(ByteBuf output, byte[] bytes) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeByteArray:(Lio/netty/buffer/ByteBuf;[B)V");
    }

    public byte[] readByteArray(int maxSize) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readByteArray:(I)[B");
    }

    public static byte[] readByteArray(ByteBuf input, int maxSize) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readByteArray:(Lio/netty/buffer/ByteBuf;I)[B");
    }

    public FriendlyByteBuf writeVarIntArray(int[] ints) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeVarIntArray:([I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int[] readVarIntArray() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readVarIntArray:()[I");
    }

    public int[] readVarIntArray(int maxSize) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readVarIntArray:(I)[I");
    }

    public FriendlyByteBuf writeLongArray(long[] longs) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeLongArray:([J)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public static void writeLongArray(ByteBuf output, long[] longs) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeLongArray:(Lio/netty/buffer/ByteBuf;[J)V");
    }

    public FriendlyByteBuf writeFixedSizeLongArray(long[] longs) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeFixedSizeLongArray:([J)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public static void writeFixedSizeLongArray(ByteBuf output, long[] longs) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeFixedSizeLongArray:(Lio/netty/buffer/ByteBuf;[J)V");
    }

    public long[] readLongArray() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readLongArray:()[J");
    }

    public long[] readFixedSizeLongArray(long[] output) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readFixedSizeLongArray:([J)[J");
    }

    public static long[] readLongArray(ByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readLongArray:(Lio/netty/buffer/ByteBuf;)[J");
    }

    public static long[] readFixedSizeLongArray(ByteBuf input, long[] output) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readFixedSizeLongArray:(Lio/netty/buffer/ByteBuf;[J)[J");
    }

    public BlockPos readBlockPos() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBlockPos:()Lnet/minecraft/core/BlockPos;");
    }

    public static BlockPos readBlockPos(ByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBlockPos:(Lio/netty/buffer/ByteBuf;)Lnet/minecraft/core/BlockPos;");
    }

    public FriendlyByteBuf writeBlockPos(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBlockPos:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public static void writeBlockPos(ByteBuf output, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBlockPos:(Lio/netty/buffer/ByteBuf;Lnet/minecraft/core/BlockPos;)V");
    }

    public ChunkPos readChunkPos() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readChunkPos:()Lnet/minecraft/world/level/ChunkPos;");
    }

    public FriendlyByteBuf writeChunkPos(ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeChunkPos:(Lnet/minecraft/world/level/ChunkPos;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public static ChunkPos readChunkPos(ByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readChunkPos:(Lio/netty/buffer/ByteBuf;)Lnet/minecraft/world/level/ChunkPos;");
    }

    public static void writeChunkPos(ByteBuf output, ChunkPos chunkPos) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeChunkPos:(Lio/netty/buffer/ByteBuf;Lnet/minecraft/world/level/ChunkPos;)V");
    }

    public GlobalPos readGlobalPos() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readGlobalPos:()Lnet/minecraft/core/GlobalPos;");
    }

    public void writeGlobalPos(GlobalPos globalPos) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeGlobalPos:(Lnet/minecraft/core/GlobalPos;)V");
    }

    public Vector3f readVector3f() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readVector3f:()Lorg/joml/Vector3f;");
    }

    public static Vector3f readVector3f(ByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readVector3f:(Lio/netty/buffer/ByteBuf;)Lorg/joml/Vector3f;");
    }

    public void writeVector3f(Vector3f v) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeVector3f:(Lorg/joml/Vector3f;)V");
    }

    public static void writeVector3f(ByteBuf output, Vector3fc v) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeVector3f:(Lio/netty/buffer/ByteBuf;Lorg/joml/Vector3fc;)V");
    }

    public Quaternionf readQuaternion() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readQuaternion:()Lorg/joml/Quaternionf;");
    }

    public static Quaternionf readQuaternion(ByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readQuaternion:(Lio/netty/buffer/ByteBuf;)Lorg/joml/Quaternionf;");
    }

    public void writeQuaternion(Quaternionf q) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeQuaternion:(Lorg/joml/Quaternionf;)V");
    }

    public static void writeQuaternion(ByteBuf output, Quaternionfc value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeQuaternion:(Lio/netty/buffer/ByteBuf;Lorg/joml/Quaternionfc;)V");
    }

    public <T extends Enum<T>> T readEnum(Class<T> clazz) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readEnum:(Ljava/lang/Class;)Ljava/lang/Enum;");
    }

    public FriendlyByteBuf writeEnum(Enum<?> value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeEnum:(Ljava/lang/Enum;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public <T> T readById(IntFunction<T> converter) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readById:(Ljava/util/function/IntFunction;)Ljava/lang/Object;");
    }

    public <T> FriendlyByteBuf writeById(ToIntFunction<T> converter, T value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeById:(Ljava/util/function/ToIntFunction;Ljava/lang/Object;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int readVarInt() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readVarInt:()I");
    }

    public long readVarLong() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readVarLong:()J");
    }

    public FriendlyByteBuf writeUUID(UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeUUID:(Ljava/util/UUID;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public static void writeUUID(ByteBuf output, UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeUUID:(Lio/netty/buffer/ByteBuf;Ljava/util/UUID;)V");
    }

    public UUID readUUID() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUUID:()Ljava/util/UUID;");
    }

    public static UUID readUUID(ByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUUID:(Lio/netty/buffer/ByteBuf;)Ljava/util/UUID;");
    }

    public FriendlyByteBuf writeVarInt(int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeVarInt:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeVarLong(long value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeVarLong:(J)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeNbt(Tag tag) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeNbt:(Lnet/minecraft/nbt/Tag;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public static void writeNbt(ByteBuf output, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeNbt:(Lio/netty/buffer/ByteBuf;Lnet/minecraft/nbt/Tag;)V");
    }

    public CompoundTag readNbt() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readNbt:()Lnet/minecraft/nbt/CompoundTag;");
    }

    public static CompoundTag readNbt(ByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readNbt:(Lio/netty/buffer/ByteBuf;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public static Tag readNbt(ByteBuf input, NbtAccounter accounter) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readNbt:(Lio/netty/buffer/ByteBuf;Lnet/minecraft/nbt/NbtAccounter;)Lnet/minecraft/nbt/Tag;");
    }

    public Tag readNbt(NbtAccounter accounter) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readNbt:(Lnet/minecraft/nbt/NbtAccounter;)Lnet/minecraft/nbt/Tag;");
    }

    public String readUtf() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUtf:()Ljava/lang/String;");
    }

    public String readUtf(int maxLength) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUtf:(I)Ljava/lang/String;");
    }

    public FriendlyByteBuf writeUtf(String value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeUtf:(Ljava/lang/String;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeUtf(String value, int maxLength) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeUtf:(Ljava/lang/String;I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public Identifier readIdentifier() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readIdentifier:()Lnet/minecraft/resources/Identifier;");
    }

    public FriendlyByteBuf writeIdentifier(Identifier identifier) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeIdentifier:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public <T> ResourceKey<T> readResourceKey(ResourceKey<? extends Registry<T>> registry) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readResourceKey:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/resources/ResourceKey;");
    }

    public void writeResourceKey(ResourceKey<?> key) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeResourceKey:(Lnet/minecraft/resources/ResourceKey;)V");
    }

    public <T> ResourceKey<? extends Registry<T>> readRegistryKey() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readRegistryKey:()Lnet/minecraft/resources/ResourceKey;");
    }

    public Instant readInstant() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readInstant:()Ljava/time/Instant;");
    }

    public void writeInstant(Instant value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeInstant:(Ljava/time/Instant;)V");
    }

    public PublicKey readPublicKey() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readPublicKey:()Ljava/security/PublicKey;");
    }

    public FriendlyByteBuf writePublicKey(PublicKey publicKey) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writePublicKey:(Ljava/security/PublicKey;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public BlockHitResult readBlockHitResult() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBlockHitResult:()Lnet/minecraft/world/phys/BlockHitResult;");
    }

    public void writeBlockHitResult(BlockHitResult blockHit) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBlockHitResult:(Lnet/minecraft/world/phys/BlockHitResult;)V");
    }

    public BitSet readBitSet() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBitSet:()Ljava/util/BitSet;");
    }

    public void writeBitSet(BitSet bitSet) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBitSet:(Ljava/util/BitSet;)V");
    }

    public BitSet readFixedBitSet(int size) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readFixedBitSet:(I)Ljava/util/BitSet;");
    }

    public void writeFixedBitSet(BitSet bitSet, int size) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeFixedBitSet:(Ljava/util/BitSet;I)V");
    }

    public static int readContainerId(ByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readContainerId:(Lio/netty/buffer/ByteBuf;)I");
    }

    public int readContainerId() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readContainerId:()I");
    }

    public static void writeContainerId(ByteBuf output, int id) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeContainerId:(Lio/netty/buffer/ByteBuf;I)V");
    }

    public void writeContainerId(int id) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeContainerId:(I)V");
    }

    public boolean isContiguous() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.isContiguous:()Z");
    }

    public int maxFastWritableBytes() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.maxFastWritableBytes:()I");
    }

    public int capacity() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.capacity:()I");
    }

    public FriendlyByteBuf capacity(int newCapacity) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.capacity:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int maxCapacity() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.maxCapacity:()I");
    }

    public ByteBufAllocator alloc() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.alloc:()Lio/netty/buffer/ByteBufAllocator;");
    }

    public ByteOrder order() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.order:()Ljava/nio/ByteOrder;");
    }

    public ByteBuf order(ByteOrder endianness) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.order:(Ljava/nio/ByteOrder;)Lio/netty/buffer/ByteBuf;");
    }

    public ByteBuf unwrap() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.unwrap:()Lio/netty/buffer/ByteBuf;");
    }

    public boolean isDirect() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.isDirect:()Z");
    }

    public boolean isReadOnly() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.isReadOnly:()Z");
    }

    public ByteBuf asReadOnly() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.asReadOnly:()Lio/netty/buffer/ByteBuf;");
    }

    public int readerIndex() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readerIndex:()I");
    }

    public FriendlyByteBuf readerIndex(int readerIndex) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readerIndex:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int writerIndex() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writerIndex:()I");
    }

    public FriendlyByteBuf writerIndex(int writerIndex) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writerIndex:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setIndex(int readerIndex, int writerIndex) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setIndex:(II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int readableBytes() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readableBytes:()I");
    }

    public int writableBytes() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writableBytes:()I");
    }

    public int maxWritableBytes() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.maxWritableBytes:()I");
    }

    public boolean isReadable() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.isReadable:()Z");
    }

    public boolean isReadable(int size) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.isReadable:(I)Z");
    }

    public boolean isWritable() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.isWritable:()Z");
    }

    public boolean isWritable(int size) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.isWritable:(I)Z");
    }

    public FriendlyByteBuf clear() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.clear:()Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf markReaderIndex() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.markReaderIndex:()Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf resetReaderIndex() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.resetReaderIndex:()Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf markWriterIndex() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.markWriterIndex:()Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf resetWriterIndex() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.resetWriterIndex:()Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf discardReadBytes() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.discardReadBytes:()Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf discardSomeReadBytes() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.discardSomeReadBytes:()Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf ensureWritable(int minWritableBytes) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.ensureWritable:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int ensureWritable(int minWritableBytes, boolean force) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.ensureWritable:(IZ)I");
    }

    public boolean getBoolean(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getBoolean:(I)Z");
    }

    public byte getByte(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getByte:(I)B");
    }

    public short getUnsignedByte(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getUnsignedByte:(I)S");
    }

    public short getShort(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getShort:(I)S");
    }

    public short getShortLE(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getShortLE:(I)S");
    }

    public int getUnsignedShort(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getUnsignedShort:(I)I");
    }

    public int getUnsignedShortLE(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getUnsignedShortLE:(I)I");
    }

    public int getMedium(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getMedium:(I)I");
    }

    public int getMediumLE(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getMediumLE:(I)I");
    }

    public int getUnsignedMedium(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getUnsignedMedium:(I)I");
    }

    public int getUnsignedMediumLE(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getUnsignedMediumLE:(I)I");
    }

    public int getInt(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getInt:(I)I");
    }

    public int getIntLE(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getIntLE:(I)I");
    }

    public long getUnsignedInt(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getUnsignedInt:(I)J");
    }

    public long getUnsignedIntLE(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getUnsignedIntLE:(I)J");
    }

    public long getLong(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getLong:(I)J");
    }

    public long getLongLE(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getLongLE:(I)J");
    }

    public char getChar(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getChar:(I)C");
    }

    public float getFloat(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getFloat:(I)F");
    }

    public double getDouble(int index) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getDouble:(I)D");
    }

    public FriendlyByteBuf getBytes(int index, ByteBuf dst) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getBytes:(ILio/netty/buffer/ByteBuf;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf getBytes(int index, ByteBuf dst, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getBytes:(ILio/netty/buffer/ByteBuf;I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getBytes:(ILio/netty/buffer/ByteBuf;II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf getBytes(int index, byte[] dst) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getBytes:(I[B)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getBytes:(I[BII)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf getBytes(int index, ByteBuffer dst) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getBytes:(ILjava/nio/ByteBuffer;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getBytes:(ILjava/io/OutputStream;I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int getBytes(int index, GatheringByteChannel out, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getBytes:(ILjava/nio/channels/GatheringByteChannel;I)I");
    }

    public int getBytes(int index, FileChannel out, long position, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getBytes:(ILjava/nio/channels/FileChannel;JI)I");
    }

    public CharSequence getCharSequence(int index, int length, Charset charset) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getCharSequence:(IILjava/nio/charset/Charset;)Ljava/lang/CharSequence;");
    }

    public FriendlyByteBuf setBoolean(int index, boolean value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setBoolean:(IZ)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setByte(int index, int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setByte:(II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setShort(int index, int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setShort:(II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setShortLE(int index, int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setShortLE:(II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setMedium(int index, int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setMedium:(II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setMediumLE(int index, int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setMediumLE:(II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setInt(int index, int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setInt:(II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setIntLE(int index, int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setIntLE:(II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setLong(int index, long value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setLong:(IJ)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setLongLE(int index, long value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setLongLE:(IJ)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setChar(int index, int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setChar:(II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setFloat(int index, float value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setFloat:(IF)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setDouble(int index, double value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setDouble:(ID)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setBytes(int index, ByteBuf src) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setBytes:(ILio/netty/buffer/ByteBuf;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setBytes(int index, ByteBuf src, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setBytes:(ILio/netty/buffer/ByteBuf;I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setBytes:(ILio/netty/buffer/ByteBuf;II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setBytes(int index, byte[] src) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setBytes:(I[B)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setBytes:(I[BII)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf setBytes(int index, ByteBuffer src) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setBytes:(ILjava/nio/ByteBuffer;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int setBytes(int index, InputStream in, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setBytes:(ILjava/io/InputStream;I)I");
    }

    public int setBytes(int index, ScatteringByteChannel in, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setBytes:(ILjava/nio/channels/ScatteringByteChannel;I)I");
    }

    public int setBytes(int index, FileChannel in, long position, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setBytes:(ILjava/nio/channels/FileChannel;JI)I");
    }

    public FriendlyByteBuf setZero(int index, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setZero:(II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int setCharSequence(int index, CharSequence sequence, Charset charset) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.setCharSequence:(ILjava/lang/CharSequence;Ljava/nio/charset/Charset;)I");
    }

    public boolean readBoolean() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBoolean:()Z");
    }

    public byte readByte() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readByte:()B");
    }

    public short readUnsignedByte() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUnsignedByte:()S");
    }

    public short readShort() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readShort:()S");
    }

    public short readShortLE() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readShortLE:()S");
    }

    public int readUnsignedShort() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUnsignedShort:()I");
    }

    public int readUnsignedShortLE() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUnsignedShortLE:()I");
    }

    public int readMedium() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readMedium:()I");
    }

    public int readMediumLE() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readMediumLE:()I");
    }

    public int readUnsignedMedium() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUnsignedMedium:()I");
    }

    public int readUnsignedMediumLE() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUnsignedMediumLE:()I");
    }

    public int readInt() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readInt:()I");
    }

    public int readIntLE() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readIntLE:()I");
    }

    public long readUnsignedInt() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUnsignedInt:()J");
    }

    public long readUnsignedIntLE() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readUnsignedIntLE:()J");
    }

    public long readLong() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readLong:()J");
    }

    public long readLongLE() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readLongLE:()J");
    }

    public char readChar() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readChar:()C");
    }

    public float readFloat() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readFloat:()F");
    }

    public double readDouble() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readDouble:()D");
    }

    public ByteBuf readBytes(int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBytes:(I)Lio/netty/buffer/ByteBuf;");
    }

    public ByteBuf readSlice(int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readSlice:(I)Lio/netty/buffer/ByteBuf;");
    }

    public ByteBuf readRetainedSlice(int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readRetainedSlice:(I)Lio/netty/buffer/ByteBuf;");
    }

    public FriendlyByteBuf readBytes(ByteBuf dst) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBytes:(Lio/netty/buffer/ByteBuf;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf readBytes(ByteBuf dst, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBytes:(Lio/netty/buffer/ByteBuf;I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf readBytes(ByteBuf dst, int dstIndex, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBytes:(Lio/netty/buffer/ByteBuf;II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf readBytes(byte[] dst) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBytes:([B)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf readBytes(byte[] dst, int dstIndex, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBytes:([BII)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf readBytes(ByteBuffer dst) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBytes:(Ljava/nio/ByteBuffer;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf readBytes(OutputStream out, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBytes:(Ljava/io/OutputStream;I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int readBytes(GatheringByteChannel out, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBytes:(Ljava/nio/channels/GatheringByteChannel;I)I");
    }

    public CharSequence readCharSequence(int length, Charset charset) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readCharSequence:(ILjava/nio/charset/Charset;)Ljava/lang/CharSequence;");
    }

    public String readString(int length, Charset charset) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readString:(ILjava/nio/charset/Charset;)Ljava/lang/String;");
    }

    public int readBytes(FileChannel out, long position, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.readBytes:(Ljava/nio/channels/FileChannel;JI)I");
    }

    public FriendlyByteBuf skipBytes(int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.skipBytes:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeBoolean(boolean value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBoolean:(Z)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeByte(int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeByte:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeShort(int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeShort:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeShortLE(int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeShortLE:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeMedium(int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeMedium:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeMediumLE(int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeMediumLE:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeInt(int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeInt:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeIntLE(int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeIntLE:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeLong(long value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeLong:(J)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeLongLE(long value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeLongLE:(J)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeChar(int value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeChar:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeFloat(float value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeFloat:(F)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeDouble(double value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeDouble:(D)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeBytes(ByteBuf src) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBytes:(Lio/netty/buffer/ByteBuf;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeBytes(ByteBuf src, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBytes:(Lio/netty/buffer/ByteBuf;I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeBytes(ByteBuf src, int srcIndex, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBytes:(Lio/netty/buffer/ByteBuf;II)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeBytes(byte[] src) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBytes:([B)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeBytes(byte[] src, int srcIndex, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBytes:([BII)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf writeBytes(ByteBuffer src) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBytes:(Ljava/nio/ByteBuffer;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int writeBytes(InputStream in, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBytes:(Ljava/io/InputStream;I)I");
    }

    public int writeBytes(ScatteringByteChannel in, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBytes:(Ljava/nio/channels/ScatteringByteChannel;I)I");
    }

    public int writeBytes(FileChannel in, long position, int length) throws IOException {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeBytes:(Ljava/nio/channels/FileChannel;JI)I");
    }

    public FriendlyByteBuf writeZero(int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeZero:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int writeCharSequence(CharSequence sequence, Charset charset) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.writeCharSequence:(Ljava/lang/CharSequence;Ljava/nio/charset/Charset;)I");
    }

    public int indexOf(int fromIndex, int toIndex, byte value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.indexOf:(IIB)I");
    }

    public int bytesBefore(byte value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.bytesBefore:(B)I");
    }

    public int bytesBefore(int length, byte value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.bytesBefore:(IB)I");
    }

    public int bytesBefore(int index, int length, byte value) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.bytesBefore:(IIB)I");
    }

    public int forEachByte(ByteProcessor processor) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.forEachByte:(Lio/netty/util/ByteProcessor;)I");
    }

    public int forEachByte(int index, int length, ByteProcessor processor) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.forEachByte:(IILio/netty/util/ByteProcessor;)I");
    }

    public int forEachByteDesc(ByteProcessor processor) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.forEachByteDesc:(Lio/netty/util/ByteProcessor;)I");
    }

    public int forEachByteDesc(int index, int length, ByteProcessor processor) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.forEachByteDesc:(IILio/netty/util/ByteProcessor;)I");
    }

    public ByteBuf copy() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.copy:()Lio/netty/buffer/ByteBuf;");
    }

    public ByteBuf copy(int index, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.copy:(II)Lio/netty/buffer/ByteBuf;");
    }

    public ByteBuf slice() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.slice:()Lio/netty/buffer/ByteBuf;");
    }

    public ByteBuf retainedSlice() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.retainedSlice:()Lio/netty/buffer/ByteBuf;");
    }

    public ByteBuf slice(int index, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.slice:(II)Lio/netty/buffer/ByteBuf;");
    }

    public ByteBuf retainedSlice(int index, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.retainedSlice:(II)Lio/netty/buffer/ByteBuf;");
    }

    public ByteBuf duplicate() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.duplicate:()Lio/netty/buffer/ByteBuf;");
    }

    public ByteBuf retainedDuplicate() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.retainedDuplicate:()Lio/netty/buffer/ByteBuf;");
    }

    public int nioBufferCount() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.nioBufferCount:()I");
    }

    public ByteBuffer nioBuffer() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.nioBuffer:()Ljava/nio/ByteBuffer;");
    }

    public ByteBuffer nioBuffer(int index, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.nioBuffer:(II)Ljava/nio/ByteBuffer;");
    }

    public ByteBuffer internalNioBuffer(int index, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.internalNioBuffer:(II)Ljava/nio/ByteBuffer;");
    }

    public ByteBuffer[] nioBuffers() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.nioBuffers:()[Ljava/nio/ByteBuffer;");
    }

    public ByteBuffer[] nioBuffers(int index, int length) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.nioBuffers:(II)[Ljava/nio/ByteBuffer;");
    }

    public boolean hasArray() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.hasArray:()Z");
    }

    public byte[] array() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.array:()[B");
    }

    public int arrayOffset() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.arrayOffset:()I");
    }

    public boolean hasMemoryAddress() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.hasMemoryAddress:()Z");
    }

    public long memoryAddress() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.memoryAddress:()J");
    }

    public String toString(Charset charset) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.toString:(Ljava/nio/charset/Charset;)Ljava/lang/String;");
    }

    public String toString(int index, int length, Charset charset) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.toString:(IILjava/nio/charset/Charset;)Ljava/lang/String;");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.hashCode:()I");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.equals:(Ljava/lang/Object;)Z");
    }

    public int compareTo(ByteBuf buffer) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.compareTo:(Lio/netty/buffer/ByteBuf;)I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.toString:()Ljava/lang/String;");
    }

    public FriendlyByteBuf retain(int increment) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.retain:(I)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf retain() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.retain:()Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf touch() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.touch:()Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public FriendlyByteBuf touch(Object hint) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.touch:(Ljava/lang/Object;)Lnet/minecraft/network/FriendlyByteBuf;");
    }

    public int refCnt() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.refCnt:()I");
    }

    public boolean release() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.release:()Z");
    }

    public boolean release(int decrement) {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.release:(I)Z");
    }

    public ByteBuf getSource() {
        throw Unimplemented.forMember("net/minecraft/network/FriendlyByteBuf.getSource:()Lio/netty/buffer/ByteBuf;");
    }

    public FriendlyByteBuf() {
    }
}
