package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public final class ByteArrayTag implements CollectionTag {

    public ByteArrayTag(byte[] data) {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.<init>:([B)V");
    }

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.getId:()B");
    }

    public TagType<ByteArrayTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.toString:()Ljava/lang/String;");
    }

    public Tag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.copy:()Lnet/minecraft/nbt/Tag;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.hashCode:()I");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.size:()I");
    }

    public ByteTag get(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.get:(I)Lnet/minecraft/nbt/ByteTag;");
    }

    public boolean setTag(int index, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.setTag:(ILnet/minecraft/nbt/Tag;)Z");
    }

    public boolean addTag(int index, Tag tag) {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.addTag:(ILnet/minecraft/nbt/Tag;)Z");
    }

    public ByteTag remove(int index) {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.remove:(I)Lnet/minecraft/nbt/ByteTag;");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.clear:()V");
    }

    public Optional<byte[]> asByteArray() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.asByteArray:()Ljava/util/Optional;");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteArrayTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }

    protected ByteArrayTag() {
    }
}
