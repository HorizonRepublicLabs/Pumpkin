package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import java.util.AbstractList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import dev.pumpkin.shim.Unimplemented;

public final class ListTag extends AbstractList<Tag> implements CollectionTag {

    public ListTag() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.<init>:()V");
    }

    public ListTag(int initialCapacity) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.<init>:(I)V");
    }

    ListTag(List<Tag> list) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.<init>:(Ljava/util/List;)V");
    }

    private static Tag tryUnwrap(CompoundTag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.tryUnwrap:(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/nbt/Tag;");
    }

    private static boolean isWrapper(CompoundTag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.isWrapper:(Lnet/minecraft/nbt/CompoundTag;)Z");
    }

    private static Tag wrapIfNeeded(byte elementType, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.wrapIfNeeded:(BLnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/Tag;");
    }

    private static CompoundTag wrapElement(Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.wrapElement:(Lnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.write:(Ljava/io/DataOutput;)V");
    }

    byte identifyRawElementType() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.identifyRawElementType:()B");
    }

    public void addAndUnwrap(Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.addAndUnwrap:(Lnet/minecraft/nbt/Tag;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getId:()B");
    }

    public TagType<ListTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.toString:()Ljava/lang/String;");
    }

    public Tag remove(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.remove:(I)Lnet/minecraft/nbt/Tag;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.isEmpty:()Z");
    }

    public Optional<CompoundTag> getCompound(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getCompound:(I)Ljava/util/Optional;");
    }

    public CompoundTag getCompoundOrEmpty(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getCompoundOrEmpty:(I)Lnet/minecraft/nbt/CompoundTag;");
    }

    public Optional<ListTag> getList(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getList:(I)Ljava/util/Optional;");
    }

    public ListTag getListOrEmpty(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getListOrEmpty:(I)Lnet/minecraft/nbt/ListTag;");
    }

    public Optional<Short> getShort(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getShort:(I)Ljava/util/Optional;");
    }

    public short getShortOr(int index, short defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getShortOr:(IS)S");
    }

    public Optional<Integer> getInt(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getInt:(I)Ljava/util/Optional;");
    }

    public int getIntOr(int index, int defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getIntOr:(II)I");
    }

    public Optional<int[]> getIntArray(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getIntArray:(I)Ljava/util/Optional;");
    }

    public Optional<long[]> getLongArray(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getLongArray:(I)Ljava/util/Optional;");
    }

    public Optional<Double> getDouble(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getDouble:(I)Ljava/util/Optional;");
    }

    public double getDoubleOr(int index, double defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getDoubleOr:(ID)D");
    }

    public Optional<Float> getFloat(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getFloat:(I)Ljava/util/Optional;");
    }

    public float getFloatOr(int index, float defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getFloatOr:(IF)F");
    }

    public Optional<String> getString(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getString:(I)Ljava/util/Optional;");
    }

    public String getStringOr(int index, String defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getStringOr:(ILjava/lang/String;)Ljava/lang/String;");
    }

    private Tag getNullable(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getNullable:(I)Lnet/minecraft/nbt/Tag;");
    }

    private Optional<Tag> getOptional(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.getOptional:(I)Ljava/util/Optional;");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.size:()I");
    }

    public Tag get(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.get:(I)Lnet/minecraft/nbt/Tag;");
    }

    public Tag set(int index, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.set:(ILnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/Tag;");
    }

    public void add(int index, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.add:(ILnet/minecraft/nbt/Tag;)V");
    }

    public boolean setTag(int index, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.setTag:(ILnet/minecraft/nbt/Tag;)Z");
    }

    public boolean addTag(int index, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.addTag:(ILnet/minecraft/nbt/Tag;)Z");
    }

    public ListTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.copy:()Lnet/minecraft/nbt/ListTag;");
    }

    public Optional<ListTag> asList() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.asList:()Ljava/util/Optional;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.hashCode:()I");
    }

    public Stream<Tag> stream() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.stream:()Ljava/util/stream/Stream;");
    }

    public Stream<CompoundTag> compoundStream() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.compoundStream:()Ljava/util/stream/Stream;");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.clear:()V");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/ListTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }
}
