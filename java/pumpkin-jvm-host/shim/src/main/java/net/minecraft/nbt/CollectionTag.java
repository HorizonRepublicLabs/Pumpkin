package net.minecraft.nbt;

import java.util.Iterator;
import java.util.stream.Stream;
import dev.pumpkin.shim.Unimplemented;

public interface CollectionTag extends Tag, Iterable<Tag> {

    void clear();

    boolean setTag(int index, Tag tag);

    boolean addTag(int index, Tag tag);

    Tag remove(int index);

    Tag get(int index);

    int size();

    default boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/nbt/CollectionTag.isEmpty:()Z");
    }

    default Iterator<Tag> iterator() {
        throw Unimplemented.forMember("net/minecraft/nbt/CollectionTag.iterator:()Ljava/util/Iterator;");
    }

    default Stream<Tag> stream() {
        throw Unimplemented.forMember("net/minecraft/nbt/CollectionTag.stream:()Ljava/util/stream/Stream;");
    }
}
