package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public record StringTag(String value) implements PrimitiveTag {

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/StringTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/StringTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/StringTag.getId:()B");
    }

    public TagType<StringTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/StringTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/StringTag.toString:()Ljava/lang/String;");
    }

    public StringTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/StringTag.copy:()Lnet/minecraft/nbt/StringTag;");
    }

    public Optional<String> asString() {
        throw Unimplemented.forMember("net/minecraft/nbt/StringTag.asString:()Ljava/util/Optional;");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/StringTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/StringTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }
}
