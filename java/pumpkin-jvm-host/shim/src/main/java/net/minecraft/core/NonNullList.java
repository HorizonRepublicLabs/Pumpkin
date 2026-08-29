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
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.withSize:(ILjava/lang/Object;)Lnet/minecraft/core/NonNullList;");
    }

    public static <E> NonNullList<E> of(E defaultValue, E... values) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.of:(Ljava/lang/Object;[Ljava/lang/Object;)Lnet/minecraft/core/NonNullList;");
    }

    protected NonNullList(List<E> list, E defaultValue) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.<init>:(Ljava/util/List;Ljava/lang/Object;)V");
    }

    public E get(int index) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.get:(I)Ljava/lang/Object;");
    }

    public E set(int index, E element) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.set:(ILjava/lang/Object;)Ljava/lang/Object;");
    }

    public void add(int index, E element) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.add:(ILjava/lang/Object;)V");
    }

    public E remove(int index) {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.remove:(I)Ljava/lang/Object;");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.size:()I");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/core/NonNullList.clear:()V");
    }

    public NonNullList() {
    }
}
