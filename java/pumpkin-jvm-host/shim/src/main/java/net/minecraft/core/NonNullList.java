package net.minecraft.core;

import java.util.AbstractList;
import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public class NonNullList<E> extends AbstractList<E> {

    public static <E> com.mojang.serialization.Codec<NonNullList<E>> codecOf(com.mojang.serialization.Codec<E> entryCodec) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.codecOf:(Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");
    }

    public static <E> NonNullList<E> copyOf(java.util.Collection<? extends E> entries) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.copyOf:(Ljava/util/Collection;)Lnet/minecraft/core/NonNullList;");
    }

    public static <E> NonNullList<E> create() {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.create:()Lnet/minecraft/core/NonNullList;");
    }

    public static <E> NonNullList<E> createWithCapacity(int capacity) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.createWithCapacity:(I)Lnet/minecraft/core/NonNullList;");
    }

    public static <E> NonNullList<E> withSize(int size, E defaultValue) {
        List<E> backing = new java.util.ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            backing.add(defaultValue);
        }
        return new NonNullList<>(backing, defaultValue);
    }

    public static <E> NonNullList<E> of(E defaultValue, E... values) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.of:(Ljava/lang/Object;[Ljava/lang/Object;)Lnet/minecraft/core/NonNullList;");
    }

    // Pumpkin divergence: really backed by the list it wraps; vanilla is the same thin
    // wrapper, minus the null checks nothing here needs yet.
    private List<E> pumpkinBacking;

    protected NonNullList(List<E> list, E defaultValue) {
        this.pumpkinBacking = list;
    }

    public E get(int index) {
        return pumpkinBacking.get(index);
    }

    public E set(int index, E element) {
        return pumpkinBacking.set(index, element);
    }

    public void add(int index, E element) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.add:(ILjava/lang/Object;)V");
    }

    public E remove(int index) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.remove:(I)Ljava/lang/Object;");
    }

    public int size() {
        return pumpkinBacking.size();
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.clear:()V");
    }

    public NonNullList() {
    }
}
