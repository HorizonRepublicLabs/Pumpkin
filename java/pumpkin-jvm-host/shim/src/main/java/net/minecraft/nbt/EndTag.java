package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import dev.pumpkin.shim.Unimplemented;

public final class EndTag implements Tag {

    protected EndTag() {
    }

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/EndTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/EndTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/EndTag.getId:()B");
    }

    public TagType<EndTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/EndTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/EndTag.toString:()Ljava/lang/String;");
    }

    public EndTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/EndTag.copy:()Lnet/minecraft/nbt/EndTag;");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/EndTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/EndTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }
}
