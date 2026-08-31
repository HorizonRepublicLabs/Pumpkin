package net.neoforged.neoforge.registries.holdersets;

import com.mojang.datafixers.util.Either;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public abstract class CompositeHolderSet<T> implements ICustomHolderSet<T> {

    public CompositeHolderSet(List<HolderSet<T>> components) {
    }

    public boolean isImmediatelyResolvable() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.isImmediatelyResolvable:()Z");
    }

    protected abstract Set<Holder<T>> createSet();

    public List<HolderSet<T>> getComponents() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.getComponents:()Ljava/util/List;");
    }

    public List<Holder<T>> getList() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.getList:()Ljava/util/List;");
    }

    public void addInvalidationListener(Runnable runnable) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.addInvalidationListener:(Ljava/lang/Runnable;)V");
    }

    public Stream<Holder<T>> stream() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.stream:()Ljava/util/stream/Stream;");
    }

    public int size() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.size:()I");
    }

    public Either<TagKey<T>, List<Holder<T>>> unwrap() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.unwrap:()Lcom/mojang/datafixers/util/Either;");
    }

    public Optional<Holder<T>> getRandomElement(RandomSource rand) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.getRandomElement:(Lnet/minecraft/util/RandomSource;)Ljava/util/Optional;");
    }

    public Holder<T> get(int i) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.get:(I)Lnet/minecraft/core/Holder;");
    }

    public boolean contains(Holder<T> holder) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.contains:(Lnet/minecraft/core/Holder;)Z");
    }

    public boolean canSerializeIn(HolderOwner<T> holderOwner) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.canSerializeIn:(Lnet/minecraft/core/HolderOwner;)Z");
    }

    public Optional<TagKey<T>> unwrapKey() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.unwrapKey:()Ljava/util/Optional;");
    }

    public Iterator<Holder<T>> iterator() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.iterator:()Ljava/util/Iterator;");
    }

    public boolean isBound() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/CompositeHolderSet.isBound:()Z");
    }

    public CompositeHolderSet() {
    }
}
