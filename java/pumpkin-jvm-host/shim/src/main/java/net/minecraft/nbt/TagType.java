package net.minecraft.nbt;

import java.io.DataInput;
import java.io.IOException;
import dev.pumpkin.shim.Unimplemented;

public interface TagType<T extends Tag> {

    T load(DataInput input, NbtAccounter accounter) throws IOException;

    StreamTagVisitor.ValueResult parse(DataInput input, StreamTagVisitor output, NbtAccounter accounter) throws IOException;

    void skip(DataInput input, int count, NbtAccounter accounter) throws IOException;

    void skip(DataInput input, NbtAccounter accounter) throws IOException;

    String getName();

    String getPrettyName();

    interface StaticSize<T extends Tag> extends TagType<T> {

        default void skip(DataInput input, NbtAccounter accounter) throws IOException {
            throw Unimplemented.forMember("net/minecraft/nbt/TagType$StaticSize.skip:(Ljava/io/DataInput;Lnet/minecraft/nbt/NbtAccounter;)V");
        }

        default void skip(DataInput input, int count, NbtAccounter accounter) throws IOException {
            throw Unimplemented.forMember("net/minecraft/nbt/TagType$StaticSize.skip:(Ljava/io/DataInput;ILnet/minecraft/nbt/NbtAccounter;)V");
        }

        int size();
    }

    interface VariableSize<T extends Tag> extends TagType<T> {

        default void skip(DataInput input, int count, NbtAccounter accounter) throws IOException {
            throw Unimplemented.forMember("net/minecraft/nbt/TagType$VariableSize.skip:(Ljava/io/DataInput;ILnet/minecraft/nbt/NbtAccounter;)V");
        }
    }
}
