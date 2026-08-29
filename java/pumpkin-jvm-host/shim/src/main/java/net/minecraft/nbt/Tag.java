package net.minecraft.nbt;

import java.io.DataOutput;
import java.io.IOException;

public interface Tag {

    void write(DataOutput output) throws IOException;

    String toString();

    byte getId();

    TagType<?> getType();

    Tag copy();

    int sizeInBytes();

    void accept(TagVisitor visitor);

    StreamTagVisitor.ValueResult accept(StreamTagVisitor visitor);
}
