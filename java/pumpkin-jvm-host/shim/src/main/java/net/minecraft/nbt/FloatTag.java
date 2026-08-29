package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;
import dev.pumpkin.shim.Unimplemented;

public record FloatTag(float value) implements NumericTag {

    public void write(DataOutput output) throws IOException {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.write:(Ljava/io/DataOutput;)V");
    }

    public int sizeInBytes() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.sizeInBytes:()I");
    }

    public byte getId() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.getId:()B");
    }

    public TagType<FloatTag> getType() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.getType:()Lnet/minecraft/nbt/TagType;");
    }

    public FloatTag copy() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.copy:()Lnet/minecraft/nbt/FloatTag;");
    }

    public void accept(TagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.accept:(Lnet/minecraft/nbt/TagVisitor;)V");
    }

    public long longValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.longValue:()J");
    }

    public int intValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.intValue:()I");
    }

    public short shortValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.shortValue:()S");
    }

    public byte byteValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.byteValue:()B");
    }

    public double doubleValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.doubleValue:()D");
    }

    public float floatValue() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.floatValue:()F");
    }

    public Number box() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.box:()Ljava/lang/Number;");
    }

    public StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor) {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.accept:(Lnet/minecraft/nbt/StreamTagVisitor;)Lnet/minecraft/nbt/StreamTagVisitor$ValueResult;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/nbt/FloatTag.toString:()Ljava/lang/String;");
    }
}
