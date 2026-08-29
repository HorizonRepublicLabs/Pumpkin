package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import dev.pumpkin.shim.Unimplemented;

public record LongTag(long value) implements NumericTag {

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.getId:()B");
    }

    public TagType<LongTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public LongTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.copy:()Lnet/minecraft/nbt/LongTag;");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public long longValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.longValue:()J");
    }

    public int intValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.intValue:()I");
    }

    public short shortValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.shortValue:()S");
    }

    public byte byteValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.byteValue:()B");
    }

    public double doubleValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.doubleValue:()D");
    }

    public float floatValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.floatValue:()F");
    }

    public Number box() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.box:()Ljava/lang/Number;");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/LongTag.toString:()Ljava/lang/String;");
    }

    private static class Cache {

        protected Cache() {
        }
    }
}
