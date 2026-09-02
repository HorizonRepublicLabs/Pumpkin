package net.minecraft.core;

import com.mojang.datafixers.util.Either;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.common.extensions.IHolderExtension;
import dev.pumpkin.shim.Unimplemented;

public interface Holder<T> extends IHolderExtension<T> {

    T value();

    boolean isBound();

    boolean areComponentsBound();

    boolean is(Identifier key);

    boolean is(ResourceKey<T> key);

    boolean is(Predicate<ResourceKey<T>> predicate);

    boolean is(TagKey<T> tag);

    boolean is(Holder<T> holder);

    Stream<TagKey<T>> tags();

    DataComponentMap components();

    Either<ResourceKey<T>, T> unwrap();

    Optional<ResourceKey<T>> unwrapKey();

    Holder.Kind kind();

    boolean canSerializeIn(HolderOwner<T> registry);

    // Pumpkin divergence: vanilla's derivation -- the key's printed name, or the
    // unregistered marker when the holder has none.
    default String getRegisteredName() {
        return unwrapKey().map(key -> key.identifier().toString()).orElse("[unregistered]");
    }

    record Direct<T>(T value, DataComponentMap components) implements Holder<T> {

        public boolean isBound() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.isBound:()Z");
        }

        public boolean areComponentsBound() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.areComponentsBound:()Z");
        }

        public boolean is(Identifier key) {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.is:(Lnet/minecraft/resources/Identifier;)Z");
        }

        public boolean is(ResourceKey<T> key) {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.is:(Lnet/minecraft/resources/ResourceKey;)Z");
        }

        public boolean is(TagKey<T> tag) {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.is:(Lnet/minecraft/tags/TagKey;)Z");
        }

        public boolean is(Holder<T> holder) {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.is:(Lnet/minecraft/core/Holder;)Z");
        }

        public boolean is(Predicate<ResourceKey<T>> predicate) {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.is:(Ljava/util/function/Predicate;)Z");
        }

        public Either<ResourceKey<T>, T> unwrap() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.unwrap:()Lcom/mojang/datafixers/util/Either;");
        }

        public Optional<ResourceKey<T>> unwrapKey() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.unwrapKey:()Ljava/util/Optional;");
        }

        public Holder.Kind kind() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.kind:()Lnet/minecraft/core/Holder$Kind;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.toString:()Ljava/lang/String;");
        }

        public boolean canSerializeIn(HolderOwner<T> registry) {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.canSerializeIn:(Lnet/minecraft/core/HolderOwner;)Z");
        }

        public Stream<TagKey<T>> tags() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Direct.tags:()Ljava/util/stream/Stream;");
        }
    }

    enum Kind {

        REFERENCE, DIRECT
    }

    class Reference<T> implements Holder<T> {

        private Set<TagKey<T>> tags;

        private ResourceKey<T> key;

        private T value;

        // Pumpkin divergence: a reference really carries its key and value.
        protected Reference(Holder.Reference.Type type, HolderOwner<T> owner, ResourceKey<T> key, T value) {
            this.key = key;
            this.value = value;
        }

        public static <T> Holder.Reference<T> pumpkinOf(ResourceKey<T> key, T value) {
            return new Reference<>(null, null, key, value);
        }

        public ResourceKey<T> key() {
            return key;
        }

        public T value() {
            if (value == null) {
                throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.value:()Ljava/lang/Object;");
            }
            return value;
        }

        public boolean is(Identifier key) {
            return this.key != null && this.key.identifier().equals(key);
        }

        public boolean is(ResourceKey<T> key) {
            return this.key != null && this.key.equals(key);
        }

        // Pumpkin divergence: real body -- asked of the same datapack tags a registry
        // lookup reads, so "does this upgrade wear this tag" and "what wears this tag"
        // cannot disagree.
        public boolean is(TagKey<T> tag) {
            if (key == null || tag == null || tag.location() == null) {
                return false;
            }
            return dev.pumpkin.bridge.PumpkinRegistryTags.wears(
                    key.pumpkinRegistry().toString(),
                    tag.location().toString(),
                    key.identifier().toString());
        }

        public boolean is(Holder<T> holder) {
            return holder == this || (holder != null && value != null && holder.value() == value);
        }

        public boolean is(Predicate<ResourceKey<T>> predicate) {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.is:(Ljava/util/function/Predicate;)Z");
        }

        public boolean canSerializeIn(HolderOwner<T> context) {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.canSerializeIn:(Lnet/minecraft/core/HolderOwner;)Z");
        }

        public Either<ResourceKey<T>, T> unwrap() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.unwrap:()Lcom/mojang/datafixers/util/Either;");
        }

        // Pumpkin divergence: real body -- the reference carries its key.
        public Optional<ResourceKey<T>> unwrapKey() {
            return Optional.ofNullable(key);
        }

        // Pumpkin divergence: real body -- a reference is, by definition, that kind.
        public Holder.Kind kind() {
            return Holder.Kind.REFERENCE;
        }

        public boolean isBound() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.isBound:()Z");
        }

        public boolean areComponentsBound() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.areComponentsBound:()Z");
        }

        // Pumpkin divergence: data maps are not loaded yet -- same contract as
        // TypedInstanceExtension.getData, and the same loud once-per-map line.
        public <A> A getData(net.neoforged.neoforge.registries.datamaps.DataMapType<T, A> type) {
            if (net.neoforged.neoforge.common.extensions.TypedInstanceExtension.PUMPKIN_SAID
                    .add(String.valueOf(type))) {
                System.err.println("[pumpkin] data map " + type + " consulted; data maps are"
                        + " not loaded yet, so every entry answers as having none");
            }
            return null;
        }

        public Stream<TagKey<T>> tags() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.tags:()Ljava/util/stream/Stream;");
        }

        public DataComponentMap components() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.components:()Lnet/minecraft/core/component/DataComponentMap;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.toString:()Ljava/lang/String;");
        }

        public ResourceKey<T> getKey() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.getKey:()Lnet/minecraft/resources/ResourceKey;");
        }

        // Pumpkin divergence: real bodies, keyed on what the reference points at.
        //
        // Vanilla gets away with identity here because a registry interns one reference
        // per entry and hands the same object back every time. The bridge mints one per
        // lookup instead, so identity would make two references to the same upgrade
        // unequal -- and Mekanism keys an Object2IntMap of installed upgrades by holder,
        // which then finds nothing. Two references naming the same registry entry are
        // the same reference; where a key is absent, identity is all that is left.
        public int hashCode() {
            return key == null ? System.identityHashCode(this) : key.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj instanceof Holder.Reference<?> other
                    && key != null && key.equals(other.key);
        }

        public HolderLookup.RegistryLookup<T> unwrapLookup() {
            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.unwrapLookup:()Lnet/minecraft/core/HolderLookup$RegistryLookup;");
        }

        protected enum Type {

            STAND_ALONE, INTRUSIVE
        }

        protected Reference() {
        }
    }
}
