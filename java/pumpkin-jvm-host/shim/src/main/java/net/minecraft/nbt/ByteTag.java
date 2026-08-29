package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import dev.pumpkin.shim.Unimplemented;

public record ByteTag(byte value) implements NumericTag {

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.getId:()B");
    }

    public TagType<ByteTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public ByteTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.copy:()Lnet/minecraft/nbt/ByteTag;");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public long longValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.longValue:()J");
    }

    public int intValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.intValue:()I");
    }

    public short shortValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.shortValue:()S");
    }

    public byte byteValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.byteValue:()B");
    }

    public double doubleValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.doubleValue:()D");
    }

    public float floatValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.floatValue:()F");
    }

    public Number box() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.box:()Ljava/lang/Number;");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/ByteTag.toString:()Ljava/lang/String;");
    }

    private static class Cache {

        static {
            if (true) {
                throw Unimplemented.forMember("net/minecraft/nbt/ByteTag$Cache");
            }
        }
    }
}
