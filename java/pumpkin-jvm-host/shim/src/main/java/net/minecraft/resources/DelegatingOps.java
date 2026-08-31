package net.minecraft.resources;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.ListBuilder;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.RecordBuilder;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import dev.pumpkin.shim.Unimplemented;

public abstract class DelegatingOps<T> implements DynamicOps<T> {

    protected DelegatingOps(DynamicOps<T> delegate) {
    }

    public T empty() {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.empty:()Ljava/lang/Object;");
    }

    public T emptyMap() {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.emptyMap:()Ljava/lang/Object;");
    }

    public T emptyList() {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.emptyList:()Ljava/lang/Object;");
    }

    public <U> U convertTo(DynamicOps<U> outOps, T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.convertTo:(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public DataResult<Number> getNumberValue(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getNumberValue:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public T createNumeric(Number i) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createNumeric:(Ljava/lang/Number;)Ljava/lang/Object;");
    }

    public T createByte(byte value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createByte:(B)Ljava/lang/Object;");
    }

    public T createShort(short value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createShort:(S)Ljava/lang/Object;");
    }

    public T createInt(int value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createInt:(I)Ljava/lang/Object;");
    }

    public T createLong(long value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createLong:(J)Ljava/lang/Object;");
    }

    public T createFloat(float value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createFloat:(F)Ljava/lang/Object;");
    }

    public T createDouble(double value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createDouble:(D)Ljava/lang/Object;");
    }

    public DataResult<Boolean> getBooleanValue(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getBooleanValue:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public T createBoolean(boolean value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createBoolean:(Z)Ljava/lang/Object;");
    }

    public DataResult<String> getStringValue(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getStringValue:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public T createString(String value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createString:(Ljava/lang/String;)Ljava/lang/Object;");
    }

    public DataResult<T> mergeToList(T list, T value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.mergeToList:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public DataResult<T> mergeToList(T list, List<T> values) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.mergeToList:(Ljava/lang/Object;Ljava/util/List;)Lcom/mojang/serialization/DataResult;");
    }

    public DataResult<T> mergeToMap(T map, T key, T value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.mergeToMap:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public DataResult<T> mergeToMap(T map, MapLike<T> values) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.mergeToMap:(Ljava/lang/Object;Lcom/mojang/serialization/MapLike;)Lcom/mojang/serialization/DataResult;");
    }

    public DataResult<T> mergeToMap(T map, Map<T, T> values) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.mergeToMap:(Ljava/lang/Object;Ljava/util/Map;)Lcom/mojang/serialization/DataResult;");
    }

    public DataResult<T> mergeToPrimitive(T prefix, T value) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.mergeToPrimitive:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public DataResult<Stream<Pair<T, T>>> getMapValues(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getMapValues:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public DataResult<Consumer<BiConsumer<T, T>>> getMapEntries(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getMapEntries:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public T createMap(Map<T, T> map) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createMap:(Ljava/util/Map;)Ljava/lang/Object;");
    }

    public T createMap(Stream<Pair<T, T>> map) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createMap:(Ljava/util/stream/Stream;)Ljava/lang/Object;");
    }

    public DataResult<MapLike<T>> getMap(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getMap:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public DataResult<Stream<T>> getStream(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getStream:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public DataResult<Consumer<Consumer<T>>> getList(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getList:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public T createList(Stream<T> input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createList:(Ljava/util/stream/Stream;)Ljava/lang/Object;");
    }

    public DataResult<ByteBuffer> getByteBuffer(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getByteBuffer:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public T createByteList(ByteBuffer input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createByteList:(Ljava/nio/ByteBuffer;)Ljava/lang/Object;");
    }

    public DataResult<IntStream> getIntStream(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getIntStream:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public T createIntList(IntStream input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createIntList:(Ljava/util/stream/IntStream;)Ljava/lang/Object;");
    }

    public DataResult<LongStream> getLongStream(T input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.getLongStream:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
    }

    public T createLongList(LongStream input) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.createLongList:(Ljava/util/stream/LongStream;)Ljava/lang/Object;");
    }

    public T remove(T input, String key) {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.remove:(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;");
    }

    public boolean compressMaps() {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.compressMaps:()Z");
    }

    public ListBuilder<T> listBuilder() {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.listBuilder:()Lcom/mojang/serialization/ListBuilder;");
    }

    public RecordBuilder<T> mapBuilder() {
        throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps.mapBuilder:()Lcom/mojang/serialization/RecordBuilder;");
    }

    protected class DelegateListBuilder implements ListBuilder<T> {

        protected DelegateListBuilder(ListBuilder<T> original) {
        }

        public DynamicOps<T> ops() {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateListBuilder.ops:()Lcom/mojang/serialization/DynamicOps;");
        }

        public DataResult<T> build(T prefix) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateListBuilder.build:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
        }

        public ListBuilder<T> add(T value) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateListBuilder.add:(Ljava/lang/Object;)Lcom/mojang/serialization/ListBuilder;");
        }

        public ListBuilder<T> add(DataResult<T> value) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateListBuilder.add:(Lcom/mojang/serialization/DataResult;)Lcom/mojang/serialization/ListBuilder;");
        }

        public <E> ListBuilder<T> add(E value, Encoder<E> encoder) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateListBuilder.add:(Ljava/lang/Object;Lcom/mojang/serialization/Encoder;)Lcom/mojang/serialization/ListBuilder;");
        }

        public <E> ListBuilder<T> addAll(Iterable<E> values, Encoder<E> encoder) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateListBuilder.addAll:(Ljava/lang/Iterable;Lcom/mojang/serialization/Encoder;)Lcom/mojang/serialization/ListBuilder;");
        }

        public ListBuilder<T> withErrorsFrom(DataResult<?> result) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateListBuilder.withErrorsFrom:(Lcom/mojang/serialization/DataResult;)Lcom/mojang/serialization/ListBuilder;");
        }

        public ListBuilder<T> mapError(UnaryOperator<String> onError) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateListBuilder.mapError:(Ljava/util/function/UnaryOperator;)Lcom/mojang/serialization/ListBuilder;");
        }

        public DataResult<T> build(DataResult<T> prefix) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateListBuilder.build:(Lcom/mojang/serialization/DataResult;)Lcom/mojang/serialization/DataResult;");
        }

        protected DelegateListBuilder() {
        }
    }

    protected class DelegateRecordBuilder implements RecordBuilder<T> {

        protected DelegateRecordBuilder(RecordBuilder<T> original) {
        }

        public DynamicOps<T> ops() {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.ops:()Lcom/mojang/serialization/DynamicOps;");
        }

        public RecordBuilder<T> add(T key, T value) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.add:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public RecordBuilder<T> add(T key, DataResult<T> value) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.add:(Ljava/lang/Object;Lcom/mojang/serialization/DataResult;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public RecordBuilder<T> add(DataResult<T> key, DataResult<T> value) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.add:(Lcom/mojang/serialization/DataResult;Lcom/mojang/serialization/DataResult;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public RecordBuilder<T> add(String key, T value) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.add:(Ljava/lang/String;Ljava/lang/Object;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public RecordBuilder<T> add(String key, DataResult<T> value) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.add:(Ljava/lang/String;Lcom/mojang/serialization/DataResult;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public <E> RecordBuilder<T> add(String key, E value, Encoder<E> encoder) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.add:(Ljava/lang/String;Ljava/lang/Object;Lcom/mojang/serialization/Encoder;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public RecordBuilder<T> withErrorsFrom(DataResult<?> result) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.withErrorsFrom:(Lcom/mojang/serialization/DataResult;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public RecordBuilder<T> setLifecycle(Lifecycle lifecycle) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.setLifecycle:(Lcom/mojang/serialization/Lifecycle;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public RecordBuilder<T> mapError(UnaryOperator<String> onError) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.mapError:(Ljava/util/function/UnaryOperator;)Lcom/mojang/serialization/RecordBuilder;");
        }

        public DataResult<T> build(T prefix) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.build:(Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;");
        }

        public DataResult<T> build(DataResult<T> prefix) {
            throw Unimplemented.forMember("net/minecraft/resources/DelegatingOps$DelegateRecordBuilder.build:(Lcom/mojang/serialization/DataResult;)Lcom/mojang/serialization/DataResult;");
        }

        protected DelegateRecordBuilder() {
        }
    }

    public DelegatingOps() {
    }
}
