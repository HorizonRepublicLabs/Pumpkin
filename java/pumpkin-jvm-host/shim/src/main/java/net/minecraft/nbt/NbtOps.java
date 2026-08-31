package net.minecraft.nbt;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.RecordBuilder;
import com.mojang.serialization.RecordBuilder.AbstractStringBuilder;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import dev.pumpkin.shim.Unimplemented;

public class NbtOps implements DynamicOps<Tag> {

    public static final NbtOps INSTANCE = null;

    protected NbtOps() {
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.toString:()Ljava/lang/String;");
    }

    public RecordBuilder<Tag> mapBuilder() {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.mapBuilder:()Lcom/mojang/serialization/RecordBuilder;");
    }

    private static class ByteListCollector implements NbtOps.ListCollector {

        public ByteListCollector(byte[] initialValues) {
        }

        public NbtOps.ListCollector accept(Tag tag) {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$ByteListCollector.accept:(Lnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/NbtOps$ListCollector;");
        }

        public Tag result() {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$ByteListCollector.result:()Lnet/minecraft/nbt/Tag;");
        }

        protected ByteListCollector() {
        }
    }

    private static class GenericListCollector implements NbtOps.ListCollector {

        protected GenericListCollector() {
        }

        private GenericListCollector(ListTag initial) {
        }

        public GenericListCollector(IntArrayList initials) {
        }

        public GenericListCollector(ByteArrayList initials) {
        }

        public GenericListCollector(LongArrayList initials) {
        }

        public NbtOps.ListCollector accept(Tag tag) {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$GenericListCollector.accept:(Lnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/NbtOps$ListCollector;");
        }

        public Tag result() {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$GenericListCollector.result:()Lnet/minecraft/nbt/Tag;");
        }
    }

    private static class IntListCollector implements NbtOps.ListCollector {

        public IntListCollector(int[] initialValues) {
        }

        public NbtOps.ListCollector accept(Tag tag) {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$IntListCollector.accept:(Lnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/NbtOps$ListCollector;");
        }

        public Tag result() {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$IntListCollector.result:()Lnet/minecraft/nbt/Tag;");
        }

        protected IntListCollector() {
        }
    }

    private interface ListCollector {

        NbtOps.ListCollector accept(Tag t);

        Tag result();
    }

    private static class LongListCollector implements NbtOps.ListCollector {

        public LongListCollector(long[] initialValues) {
        }

        public NbtOps.ListCollector accept(Tag tag) {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$LongListCollector.accept:(Lnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/NbtOps$ListCollector;");
        }

        public Tag result() {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$LongListCollector.result:()Lnet/minecraft/nbt/Tag;");
        }

        protected LongListCollector() {
        }
    }

    public Tag empty() {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.empty:()Lnet/minecraft/nbt/Tag;");
    }

    public <U> U convertTo(com.mojang.serialization.DynamicOps<U> ops, Tag input) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.convertTo:(Lcom/mojang/serialization/DynamicOps;Lnet/minecraft/nbt/Tag;)Ljava/lang/Object;");
    }

    public com.mojang.serialization.DataResult<Number> getNumberValue(Tag input) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.getNumberValue:(Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;");
    }

    public Tag createNumeric(Number value) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.createNumeric:(Ljava/lang/Number;)Lnet/minecraft/nbt/Tag;");
    }

    public com.mojang.serialization.DataResult<String> getStringValue(Tag input) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.getStringValue:(Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;");
    }

    public Tag createString(String value) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.createString:(Ljava/lang/String;)Lnet/minecraft/nbt/Tag;");
    }

    public com.mojang.serialization.DataResult<Tag> mergeToList(Tag list, Tag value) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.mergeToList:(Lnet/minecraft/nbt/Tag;Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;");
    }

    public com.mojang.serialization.DataResult<Tag> mergeToMap(Tag map, Tag key, Tag value) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.mergeToMap:(Lnet/minecraft/nbt/Tag;Lnet/minecraft/nbt/Tag;Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;");
    }

    public com.mojang.serialization.DataResult<java.util.stream.Stream<com.mojang.datafixers.util.Pair<Tag, Tag>>> getMapValues(Tag input) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.getMapValues:(Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;");
    }

    public Tag createMap(java.util.stream.Stream<com.mojang.datafixers.util.Pair<Tag, Tag>> entries) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.createMap:(Ljava/util/stream/Stream;)Lnet/minecraft/nbt/Tag;");
    }

    public com.mojang.serialization.DataResult<java.util.stream.Stream<Tag>> getStream(Tag input) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.getStream:(Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;");
    }

    public Tag createList(java.util.stream.Stream<Tag> input) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.createList:(Ljava/util/stream/Stream;)Lnet/minecraft/nbt/Tag;");
    }

    public Tag remove(Tag input, String key) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.remove:(Lnet/minecraft/nbt/Tag;Ljava/lang/String;)Lnet/minecraft/nbt/Tag;");
    }

    private class NbtRecordBuilder extends AbstractStringBuilder<Tag, CompoundTag> {

        // Pumpkin divergence: DFU's AbstractStringBuilder has no no-arg constructor; it
        // takes the ops it builds against, and the enclosing NbtOps is exactly that.
        protected NbtRecordBuilder() {
            super(NbtOps.this);
        }

        protected CompoundTag initBuilder() {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$NbtRecordBuilder.initBuilder:()Lnet/minecraft/nbt/CompoundTag;");
        }

        protected CompoundTag append(String key, Tag value, CompoundTag builder) {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$NbtRecordBuilder.append:(Ljava/lang/String;Lnet/minecraft/nbt/Tag;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/nbt/CompoundTag;");
        }

        protected DataResult<Tag> build(CompoundTag builder, Tag prefix) {
            throw Unimplemented.forMember("net/minecraft/nbt/NbtOps$NbtRecordBuilder.build:(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;");
        }
    }
}
