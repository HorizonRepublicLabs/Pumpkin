package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import dev.pumpkin.shim.Unimplemented;

public record ShortTag(short value) implements NumericTag {

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.getId:()B");
    }

    public TagType<ShortTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public ShortTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.copy:()Lnet/minecraft/nbt/ShortTag;");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public long longValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.longValue:()J");
    }

    public int intValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.intValue:()I");
    }

    public short shortValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.shortValue:()S");
    }

    public byte byteValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.byteValue:()B");
    }

    public double doubleValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.doubleValue:()D");
    }

    public float floatValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.floatValue:()F");
    }

    public Number box() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.box:()Ljava/lang/Number;");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/ShortTag.toString:()Ljava/lang/String;");
    }

    private static class Cache {

        protected Cache() {
        }
    }
}
