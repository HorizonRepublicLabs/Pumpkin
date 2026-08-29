package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public final class IntArrayTag implements CollectionTag {

    public IntArrayTag(int[] data) {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.<init>:([I)V");
    }

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.getId:()B");
    }

    public TagType<IntArrayTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.toString:()Ljava/lang/String;");
    }

    public IntArrayTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.copy:()Lnet/minecraft/nbt/IntArrayTag;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.hashCode:()I");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.size:()I");
    }

    public IntTag get(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.get:(I)Lnet/minecraft/nbt/IntTag;");
    }

    public boolean setTag(int index, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.setTag:(ILnet/minecraft/nbt/Tag;)Z");
    }

    public boolean addTag(int index, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.addTag:(ILnet/minecraft/nbt/Tag;)Z");
    }

    public IntTag remove(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.remove:(I)Lnet/minecraft/nbt/IntTag;");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.clear:()V");
    }

    public Optional<int[]> asIntArray() {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.asIntArray:()Ljava/util/Optional;");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/IntArrayTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }

    public IntArrayTag() {
    }
}
