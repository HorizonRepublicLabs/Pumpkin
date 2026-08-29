package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import dev.pumpkin.shim.Unimplemented;

public record DoubleTag(double value) implements NumericTag {

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.getId:()B");
    }

    public TagType<DoubleTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public DoubleTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.copy:()Lnet/minecraft/nbt/DoubleTag;");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public long longValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.longValue:()J");
    }

    public int intValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.intValue:()I");
    }

    public short shortValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.shortValue:()S");
    }

    public byte byteValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.byteValue:()B");
    }

    public double doubleValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.doubleValue:()D");
    }

    public float floatValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.floatValue:()F");
    }

    public Number box() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.box:()Ljava/lang/Number;");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/DoubleTag.toString:()Ljava/lang/String;");
    }
}
