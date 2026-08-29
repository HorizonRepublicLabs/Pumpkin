package net.neoforged.neoforge.registries;

import com.mojang.datafixers.util.Either;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import dev.pumpkin.shim.Unimplemented;

public class DeferredHolder<R, T extends R> implements Holder<R>, Supplier<T> {

    // Pumpkin divergence from the generated shim: these three fields, the constructor and
    // get()/getId() below carry real behaviour. A DeferredHolder is the handle a mod keeps
    // onto something it registered, and DeferredRegister's flush reads both the id and the
    // value out of it; a throwing stub here means nothing is ever registered. Re-apply by
    // hand after any regeneration -- grep for "Pumpkin divergence".
    private final Identifier pumpkinId;

    private final Supplier<T> pumpkinFactory;

    private T pumpkinValue;

    // Pumpkin divergence: real body. A mod builds a holder for something another mod
    // registered -- MysticalAgriculture does this for its own blocks -- and only the value's
    // name matters here. Which registry it lives in is carried by the caller's own type, and
    // the flush that reads this holder resolves by name.
    //
    // The factory is null: this holder names something it did not create, so get() would
    // have nothing to call. A mod that asks for the value gets a NullPointerException rather
    // than a wrong object, which is the honest failure until cross-registry lookup exists.
    @SuppressWarnings("unchecked")
    public static <R, T extends R> DeferredHolder<R, T> create(ResourceKey<? extends Registry<R>> registryKey, Identifier valueName) {
        return new DeferredHolder<>(valueName, () -> {
            DeferredHolder<?, ?> target = PUMPKIN_BY_ID.get(registryKey.identifier() + "|" + valueName);
            if (target == null) {
                throw new IllegalStateException(valueName + " was never registered; a holder"
                        + " created by name can only resolve after its target registers");
            }
            return (T) target.get();
        });
    }

    public static <R, T extends R> DeferredHolder<R, T> create(Identifier registryName, Identifier valueName) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.create:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;)Lnet/neoforged/neoforge/registries/DeferredHolder;");
    }

    public static <R, T extends R> DeferredHolder<R, T> create(ResourceKey<R> key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.create:(Lnet/minecraft/resources/ResourceKey;)Lnet/neoforged/neoforge/registries/DeferredHolder;");
    }

    // Pumpkin divergence: real body.
    protected DeferredHolder(ResourceKey<R> key) {
        this(key.identifier(), null);
    }

    // Pumpkin divergence: no vanilla counterpart. DeferredRegister.register builds holders
    // through this; vanilla resolves them out of a real registry instead.
    DeferredHolder(Identifier id, Supplier<T> factory) {
        this.pumpkinId = id;
        this.pumpkinFactory = factory;
    }

    // Keyed by registry AND id, not id alone. A mod registers a block and an item under
    // the same id as a matter of course, and Cucumber registers codecs beside them; keyed
    // by id alone, whichever registered last won, and a slab asking for its base block got
    // a RecordCodecBuilder back -- a ClassCastException naming two classes and no cause.
    private static final java.util.Map<String, DeferredHolder<?, ?>> PUMPKIN_BY_ID =
            new java.util.concurrent.ConcurrentHashMap<>();

    // Called by DeferredRegister.register, which is the one place that knows both halves.
    static void pumpkinRecord(String registry, DeferredHolder<?, ?> holder) {
        PUMPKIN_BY_ID.put(registry + "|" + holder.getId(), holder);
    }

    // The RegisterEvent path hands over a value, not a supplier: it was built before the
    // helper ever saw it. Wrapped so holders created by name resolve regardless of which
    // of the two registration roads the target took -- MysticalAgriculture registers its
    // blocks on this one and its slabs then ask for them by name.
    static <V> void pumpkinRecordValue(String registry, Identifier id, V value) {
        PUMPKIN_BY_ID.put(registry + "|" + id, new DeferredHolder<>(id, () -> value));
    }

    public T value() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.value:()Lnet/neoforged/neoforge/registries/R;");
    }

    // Pumpkin divergence: real body. Resolves once, on first use, which is what makes the
    // registration deferred.
    public T get() {
        if (pumpkinValue == null) {
            pumpkinValue = pumpkinFactory.get();
        }
        return pumpkinValue;
    }

    // Pumpkin divergence: real body. Its printed form is the id Pumpkin registers under.
    public Identifier getId() {
        return pumpkinId;
    }

    public ResourceKey<R> getKey() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.getKey:()Lnet/minecraft/resources/ResourceKey;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.equals:(Ljava/lang/Object;)Z");
    }

    // Pumpkin divergence: real body. A mod keys a map by holder, so this has to answer.
    // The id is the identity -- two holders naming the same thing are the same handle,
    // which is what vanilla means by it too, and the resolved value is deliberately not
    // consulted because reading it would force every deferred registration.
    @Override
    public int hashCode() {
        return pumpkinId.hashCode();
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.toString:()Ljava/lang/String;");
    }

    public boolean isBound() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.isBound:()Z");
    }

    public boolean areComponentsBound() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.areComponentsBound:()Z");
    }

    public DataComponentMap components() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.components:()Lnet/minecraft/core/component/DataComponentMap;");
    }

    public boolean is(Identifier id) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.is:(Lnet/minecraft/resources/Identifier;)Z");
    }

    public boolean is(ResourceKey<R> key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.is:(Lnet/minecraft/resources/ResourceKey;)Z");
    }

    public boolean is(Predicate<ResourceKey<R>> filter) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.is:(Ljava/util/function/Predicate;)Z");
    }

    public boolean is(TagKey<R> tag) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.is:(Lnet/minecraft/tags/TagKey;)Z");
    }

    public boolean is(Holder<R> holder) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.is:(Lnet/minecraft/core/Holder;)Z");
    }

    public <Z> Z getData(DataMapType<R, Z> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.getData:(Lnet/neoforged/neoforge/registries/datamaps/DataMapType;)Ljava/lang/Object;");
    }

    public Stream<TagKey<R>> tags() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.tags:()Ljava/util/stream/Stream;");
    }

    public Either<ResourceKey<R>, R> unwrap() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.unwrap:()Lcom/mojang/datafixers/util/Either;");
    }

    public Optional<ResourceKey<R>> unwrapKey() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.unwrapKey:()Ljava/util/Optional;");
    }

    public Kind kind() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.kind:()Lnet/neoforged/neoforge/registries/Kind;");
    }

    public boolean canSerializeIn(HolderOwner<R> owner) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.canSerializeIn:(Lnet/minecraft/core/HolderOwner;)Z");
    }

    public Holder<R> getDelegate() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.getDelegate:()Lnet/minecraft/core/Holder;");
    }

    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class; this one has final fields to assign, so it delegates.
    public DeferredHolder() {
        this(Identifier.fromNamespaceAndPath("minecraft", "air"), null);
    }
}
