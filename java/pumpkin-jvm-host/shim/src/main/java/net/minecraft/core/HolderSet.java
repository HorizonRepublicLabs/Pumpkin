package net.minecraft.core;

import com.mojang.datafixers.util.Either;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Stream;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.neoforged.neoforge.common.extensions.IHolderSetExtension;
import dev.pumpkin.shim.Unimplemented;

public interface HolderSet<T> extends Iterable<Holder<T>>, IHolderSetExtension<T> {

    Stream<Holder<T>> stream();

    int size();

    boolean isBound();

    Either<TagKey<T>, List<Holder<T>>> unwrap();

    Optional<Holder<T>> getRandomElement(RandomSource random);

    Holder<T> get(int index);

    boolean contains(final Holder<T> value);

    boolean canSerializeIn(HolderOwner<T> owner);

    Optional<TagKey<T>> unwrapKey();

    static <T> HolderSet<T> empty() {
        throw Unimplemented.forMember("net/minecraft/core/HolderSet.empty:()Lnet/minecraft/core/HolderSet;");
    }

    static <T> HolderSet.Direct<T> direct(Holder<T>... values) {
        throw Unimplemented.forMember("net/minecraft/core/HolderSet.direct:([Lnet/minecraft/core/Holder;)Lnet/minecraft/core/HolderSet$Direct;");
    }

    static <T> HolderSet.Direct<T> direct(List<? extends Holder<T>> values) {
        throw Unimplemented.forMember("net/minecraft/core/HolderSet.direct:(Ljava/util/List;)Lnet/minecraft/core/HolderSet$Direct;");
    }

    static <E, T> HolderSet.Direct<T> direct(Function<E, Holder<T>> holderGetter, E... elements) {
        throw Unimplemented.forMember("net/minecraft/core/HolderSet.direct:(Ljava/util/function/Function;[Ljava/lang/Object;)Lnet/minecraft/core/HolderSet$Direct;");
    }

    static <E, T> HolderSet.Direct<T> direct(Function<E, Holder<T>> holderGetter, Collection<E> elements) {
        throw Unimplemented.forMember("net/minecraft/core/HolderSet.direct:(Ljava/util/function/Function;Ljava/util/Collection;)Lnet/minecraft/core/HolderSet$Direct;");
    }

    final class Direct<T> extends HolderSet.ListBacked<T> {

        private Direct(List<Holder<T>> contents) {
        }

        protected List<Holder<T>> contents() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Direct.contents:()Ljava/util/List;");
        }

        public boolean isBound() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Direct.isBound:()Z");
        }

        public Either<TagKey<T>, List<Holder<T>>> unwrap() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Direct.unwrap:()Lcom/mojang/datafixers/util/Either;");
        }

        public Optional<TagKey<T>> unwrapKey() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Direct.unwrapKey:()Ljava/util/Optional;");
        }

        public boolean contains(Holder<T> value) {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Direct.contains:(Lnet/minecraft/core/Holder;)Z");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Direct.toString:()Ljava/lang/String;");
        }

        public boolean equals(Object obj) {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Direct.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Direct.hashCode:()I");
        }

        protected Direct() {
        }
    }

    abstract class ListBacked<T> implements HolderSet<T> {

        protected abstract List<Holder<T>> contents();

        public int size() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.size:()I");
        }

        public Spliterator<Holder<T>> spliterator() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.spliterator:()Ljava/util/Spliterator;");
        }

        public Iterator<Holder<T>> iterator() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.iterator:()Ljava/util/Iterator;");
        }

        public Stream<Holder<T>> stream() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.stream:()Ljava/util/stream/Stream;");
        }

        public Optional<Holder<T>> getRandomElement(RandomSource random) {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.getRandomElement:(Lnet/minecraft/util/RandomSource;)Ljava/util/Optional;");
        }

        public Holder<T> get(int index) {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.get:(I)Lnet/minecraft/core/Holder;");
        }

        public boolean canSerializeIn(HolderOwner<T> owner) {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.canSerializeIn:(Lnet/minecraft/core/HolderOwner;)Z");
        }

        protected ListBacked() {
        }
    }

    class Named<T> extends HolderSet.ListBacked<T> {

        Named(HolderOwner<T> owner, TagKey<T> key) {
        }

        public TagKey<T> key() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Named.key:()Lnet/minecraft/tags/TagKey;");
        }

        protected List<Holder<T>> contents() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Named.contents:()Ljava/util/List;");
        }

        public boolean isBound() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Named.isBound:()Z");
        }

        public Either<TagKey<T>, List<Holder<T>>> unwrap() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Named.unwrap:()Lcom/mojang/datafixers/util/Either;");
        }

        public Optional<TagKey<T>> unwrapKey() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Named.unwrapKey:()Ljava/util/Optional;");
        }

        public boolean contains(Holder<T> value) {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Named.contains:(Lnet/minecraft/core/Holder;)Z");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Named.toString:()Ljava/lang/String;");
        }

        public boolean canSerializeIn(HolderOwner<T> context) {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Named.canSerializeIn:(Lnet/minecraft/core/HolderOwner;)Z");
        }

        public void addInvalidationListener(Runnable runnable) {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Named.addInvalidationListener:(Ljava/lang/Runnable;)V");
        }

        public boolean isImmediatelyResolvable() {
            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Named.isImmediatelyResolvable:()Z");
        }

        protected Named() {
        }
    }
}
