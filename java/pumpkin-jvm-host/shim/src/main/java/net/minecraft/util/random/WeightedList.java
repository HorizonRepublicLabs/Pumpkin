package net.minecraft.util.random;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public final class WeightedList<E> {

    private WeightedList(List<? extends Weighted<E>> items) {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.<init>:(Ljava/util/List;)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.isEmpty:()Z");
    }

    public <T> WeightedList<T> map(Function<E, T> mapper) {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.map:(Ljava/util/function/Function;)Lnet/minecraft/util/random/WeightedList;");
    }

    public Optional<E> getRandom(RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.getRandom:(Lnet/minecraft/util/RandomSource;)Ljava/util/Optional;");
    }

    public List<Weighted<E>> unwrap() {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.unwrap:()Ljava/util/List;");
    }

    public static <E> Codec<WeightedList<E>> codec(Codec<E> elementCodec) {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.codec:(Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");
    }

    public static <E> Codec<WeightedList<E>> codec(MapCodec<E> elementCodec) {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.codec:(Lcom/mojang/serialization/MapCodec;)Lcom/mojang/serialization/Codec;");
    }

    public static <E, B extends ByteBuf> StreamCodec<B, WeightedList<E>> streamCodec(StreamCodec<B, E> elementCodec) {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.streamCodec:(Lnet/minecraft/network/codec/StreamCodec;)Lnet/minecraft/network/codec/StreamCodec;");
    }

    public boolean contains(E value) {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.contains:(Ljava/lang/Object;)Z");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.hashCode:()I");
    }

    public static class Builder<E> {

        public WeightedList.Builder<E> add(E item) {
            throw Unimplemented.forMember("net/minecraft/util/random/WeightedList$Builder.add:(Ljava/lang/Object;)Lnet/minecraft/util/random/WeightedList$Builder;");
        }

        public WeightedList.Builder<E> add(E item, int weight) {
            throw Unimplemented.forMember("net/minecraft/util/random/WeightedList$Builder.add:(Ljava/lang/Object;I)Lnet/minecraft/util/random/WeightedList$Builder;");
        }

        public WeightedList.Builder<E> add(Weighted<E> value) {
            throw Unimplemented.forMember("net/minecraft/util/random/WeightedList$Builder.add:(Lnet/minecraft/util/random/Weighted;)Lnet/minecraft/util/random/WeightedList$Builder;");
        }

        public WeightedList.Builder<E> remove(Weighted<E> value) {
            throw Unimplemented.forMember("net/minecraft/util/random/WeightedList$Builder.remove:(Lnet/minecraft/util/random/Weighted;)Lnet/minecraft/util/random/WeightedList$Builder;");
        }

        public WeightedList.Builder<E> remove(E value) {
            throw Unimplemented.forMember("net/minecraft/util/random/WeightedList$Builder.remove:(Ljava/lang/Object;)Lnet/minecraft/util/random/WeightedList$Builder;");
        }

        public WeightedList<E> build() {
            throw Unimplemented.forMember("net/minecraft/util/random/WeightedList$Builder.build:()Lnet/minecraft/util/random/WeightedList;");
        }

        protected Builder() {
        }
    }

    private static class Compact<E> implements WeightedList.Selector<E> {

        private Compact(List<Weighted<E>> entries) {
            throw Unimplemented.forMember("net/minecraft/util/random/WeightedList$Compact.<init>:(Ljava/util/List;)V");
        }

        public E get(int selection) {
            throw Unimplemented.forMember("net/minecraft/util/random/WeightedList$Compact.get:(I)Ljava/lang/Object;");
        }

        protected Compact() {
        }
    }

    private static class Flat<E> implements WeightedList.Selector<E> {

        private Flat(List<Weighted<E>> entries, int totalWeight) {
            throw Unimplemented.forMember("net/minecraft/util/random/WeightedList$Flat.<init>:(Ljava/util/List;I)V");
        }

        public E get(int selection) {
            throw Unimplemented.forMember("net/minecraft/util/random/WeightedList$Flat.get:(I)Ljava/lang/Object;");
        }

        protected Flat() {
        }
    }

    private interface Selector<E> {

        E get(int selection);
    }

    protected WeightedList() {
    }
}
