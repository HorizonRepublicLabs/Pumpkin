package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public final class LongArrayTag implements CollectionTag {

    public LongArrayTag(long[] data) {
    }

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.getId:()B");
    }

    public TagType<LongArrayTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.toString:()Ljava/lang/String;");
    }

    public LongArrayTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.copy:()Lnet/minecraft/nbt/LongArrayTag;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.hashCode:()I");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.size:()I");
    }

    public LongTag get(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.get:(I)Lnet/minecraft/nbt/LongTag;");
    }

    public boolean setTag(int index, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.setTag:(ILnet/minecraft/nbt/Tag;)Z");
    }

    public boolean addTag(int index, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.addTag:(ILnet/minecraft/nbt/Tag;)Z");
    }

    public LongTag remove(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.remove:(I)Lnet/minecraft/nbt/LongTag;");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.clear:()V");
    }

    public Optional<long[]> asLongArray() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.asLongArray:()Ljava/util/Optional;");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/LongArrayTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }

    public LongArrayTag() {
    }
}
