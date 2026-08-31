package net.minecraft.nbt;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import dev.pumpkin.shim.Unimplemented;

public final class CompoundTag implements Tag {

    public static final Codec<CompoundTag> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.nbt.CompoundTag.CODEC");

    CompoundTag(Map<String, Tag> tags) {
    }

    public CompoundTag() {
    }

    public CompoundTag(int expectedEntries) {
    }

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.sizeInBytes:()I");
    }

    public Set<String> keySet() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.keySet:()Ljava/util/Set;");
    }

    public Set<Entry<String, Tag>> entrySet() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.entrySet:()Ljava/util/Set;");
    }

    public void forEach(BiConsumer<String, Tag> consumer) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.forEach:(Ljava/util/function/BiConsumer;)V");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getId:()B");
    }

    public TagType<CompoundTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.size:()I");
    }

    public Tag put(String name, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.put:(Ljava/lang/String;Lnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/Tag;");
    }

    public void putByte(String name, byte value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.putByte:(Ljava/lang/String;B)V");
    }

    public void putShort(String name, short value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.putShort:(Ljava/lang/String;S)V");
    }

    public void putInt(String name, int value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.putInt:(Ljava/lang/String;I)V");
    }

    public void putLong(String name, long value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.putLong:(Ljava/lang/String;J)V");
    }

    public void putFloat(String name, float value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.putFloat:(Ljava/lang/String;F)V");
    }

    public void putDouble(String name, double value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.putDouble:(Ljava/lang/String;D)V");
    }

    public void putString(String name, String value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.putString:(Ljava/lang/String;Ljava/lang/String;)V");
    }

    public void putIntArray(String name, int[] value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.putIntArray:(Ljava/lang/String;[I)V");
    }

    public void putBoolean(String name, boolean value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.putBoolean:(Ljava/lang/String;Z)V");
    }

    public Tag get(String name) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.get:(Ljava/lang/String;)Lnet/minecraft/nbt/Tag;");
    }

    public boolean contains(String name) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.contains:(Ljava/lang/String;)Z");
    }

    public byte getByteOr(String name, byte defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getByteOr:(Ljava/lang/String;B)B");
    }

    public short getShortOr(String name, short defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getShortOr:(Ljava/lang/String;S)S");
    }

    public Optional<Integer> getInt(String name) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getInt:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public int getIntOr(String name, int defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getIntOr:(Ljava/lang/String;I)I");
    }

    public Optional<Long> getLong(String name) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getLong:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public long getLongOr(String name, long defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getLongOr:(Ljava/lang/String;J)J");
    }

    public Optional<Float> getFloat(String name) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getFloat:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public float getFloatOr(String name, float defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getFloatOr:(Ljava/lang/String;F)F");
    }

    public double getDoubleOr(String name, double defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getDoubleOr:(Ljava/lang/String;D)D");
    }

    public Optional<String> getString(String name) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getString:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public String getStringOr(String name, String defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getStringOr:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
    }

    public Optional<int[]> getIntArray(String name) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getIntArray:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public boolean getBooleanOr(String string, boolean defaultValue) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.getBooleanOr:(Ljava/lang/String;Z)Z");
    }

    public Tag remove(String name) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.remove:(Ljava/lang/String;)Lnet/minecraft/nbt/Tag;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.toString:()Ljava/lang/String;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.isEmpty:()Z");
    }

    public CompoundTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.copy:()Lnet/minecraft/nbt/CompoundTag;");
    }

    public Optional<CompoundTag> asCompound() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.asCompound:()Ljava/util/Optional;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.hashCode:()I");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }

    public <T> void store(String name, Codec<T> codec, T value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.store:(Ljava/lang/String;Lcom/mojang/serialization/Codec;Ljava/lang/Object;)V");
    }

    public <T> void storeNullable(String name, Codec<T> codec, T value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.storeNullable:(Ljava/lang/String;Lcom/mojang/serialization/Codec;Ljava/lang/Object;)V");
    }

    public <T> void store(MapCodec<T> codec, T value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.store:(Lcom/mojang/serialization/MapCodec;Ljava/lang/Object;)V");
    }

    public <T> void store(MapCodec<T> codec, DynamicOps<Tag> ops, T value) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.store:(Lcom/mojang/serialization/MapCodec;Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)V");
    }

    public <T> Optional<T> read(String name, Codec<T> codec) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.read:(Ljava/lang/String;Lcom/mojang/serialization/Codec;)Ljava/util/Optional;");
    }

    public <T> Optional<T> read(String name, Codec<T> codec, DynamicOps<Tag> ops) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.read:(Ljava/lang/String;Lcom/mojang/serialization/Codec;Lcom/mojang/serialization/DynamicOps;)Ljava/util/Optional;");
    }

    public <T> Optional<T> read(MapCodec<T> codec) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.read:(Lcom/mojang/serialization/MapCodec;)Ljava/util/Optional;");
    }

    public <T> Optional<T> read(MapCodec<T> codec, DynamicOps<Tag> ops) {
        throw Unimplemented.forMember("net/minecraft/nbt/CompoundTag.read:(Lcom/mojang/serialization/MapCodec;Lcom/mojang/serialization/DynamicOps;)Ljava/util/Optional;");
    }
}
