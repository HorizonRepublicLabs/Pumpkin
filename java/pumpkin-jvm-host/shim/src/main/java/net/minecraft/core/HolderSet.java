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

    // Pumpkin divergence: vanilla bodies over a really-stored list.
    @SafeVarargs
    static <T> HolderSet.Direct<T> direct(Holder<T>... values) {
        return direct(List.of(values));
    }

    @SuppressWarnings("unchecked")
    static <T> HolderSet.Direct<T> direct(List<? extends Holder<T>> values) {
        return new Direct<>((List<Holder<T>>) List.copyOf(values));
    }

    static <E, T> HolderSet.Direct<T> direct(Function<E, Holder<T>> holderGetter, E... elements) {
        throw Unimplemented.forMember("net/minecraft/core/HolderSet.direct:(Ljava/util/function/Function;[Ljava/lang/Object;)Lnet/minecraft/core/HolderSet$Direct;");
    }

    static <E, T> HolderSet.Direct<T> direct(Function<E, Holder<T>> holderGetter, Collection<E> elements) {
        throw Unimplemented.forMember("net/minecraft/core/HolderSet.direct:(Ljava/util/function/Function;Ljava/util/Collection;)Lnet/minecraft/core/HolderSet$Direct;");
    }

    final class Direct<T> extends HolderSet.ListBacked<T> {

        private List<Holder<T>> pumpkinContents = List.of();

        private Direct(List<Holder<T>> contents) {
            this.pumpkinContents = contents;
        }

        @Override
        protected List<Holder<T>> contents() {
            return pumpkinContents;
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
            return contents().size();
        }

        public Spliterator<Holder<T>> spliterator() {
            return contents().spliterator();
        }

        public Iterator<Holder<T>> iterator() {
            return contents().iterator();
        }

        public Stream<Holder<T>> stream() {
            return contents().stream();
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

        private final TagKey<T> key = null;

        // Pumpkin divergence: a named set really carries its tag and its members, so a
        // mod that looks a tag up can iterate what wears it.
        private TagKey<T> pumpkinKey;

        private List<Holder<T>> pumpkinContents = List.of();

        /** The set a tag lookup answers with: the tag, and the holders wearing it. */
        public static <T> HolderSet.Named<T> pumpkinOf(TagKey<T> key, List<Holder<T>> contents) {
            HolderSet.Named<T> set = new HolderSet.Named<>(null, key);
            set.pumpkinKey = key;
            set.pumpkinContents = contents;
            return set;
        }

        Named(HolderOwner<T> owner, TagKey<T> key) {
        }

        // Pumpkin divergence: real body.
        public TagKey<T> key() {
            return pumpkinKey;
        }

        // Pumpkin divergence: real body.
        protected List<Holder<T>> contents() {
            return pumpkinContents;
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
