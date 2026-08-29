package net.neoforged.neoforge.registries;

import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import dev.pumpkin.shim.Unimplemented;

public class DeferredRegister<T> {

    // Pumpkin divergence from the generated shim: everything in this block, plus the five
    // members below marked the same way, carries real behaviour that vanilla NeoForge has
    // no counterpart for or that the pruner stubbed out.
    //
    // The sink exists so that the shim need not depend on `host`: NeoForge's
    // DeferredRegister writes into the game's own registries, and Pumpkin's live in Rust
    // behind a JNI native. Bootstrap.installDefaultSink points this at
    // PumpkinHost::registerBlock during boot; tests install their own. Without it a
    // registration has nowhere to go.
    //
    // Re-apply by hand after any regeneration -- grep for "Pumpkin divergence".

    /** Where a registration ends up. Returns the assigned id. */
    @FunctionalInterface
    public interface Sink {
        int registerBlock(String id, String template);
    }

    private static Sink pumpkinSink = (id, template) -> {
        throw new IllegalStateException("no registration sink installed for " + id);
    };

    public static void setSink(Sink replacement) {
        pumpkinSink = replacement;
    }

    private ResourceKey<? extends Registry<T>> pumpkinRegistryKey;

    private String pumpkinNamespace;

    private final java.util.List<DeferredHolder<T, ? extends T>> pumpkinPending = new java.util.ArrayList<>();

    // Pumpkin divergence: real body. A registry knows which registry it is, so this needs
    // nothing the ResourceKey overload does not already do. The BuiltInRegistries stubs
    // answer key() for exactly this call.
    public static <T> DeferredRegister<T> create(Registry<T> registry, String namespace) {
        return create(registry.key(), namespace);
    }

    // Pumpkin divergence: real body.
    public static <T> DeferredRegister<T> create(ResourceKey<? extends Registry<T>> key, String namespace) {
        return new DeferredRegister<>(key, namespace);
    }

    public static <B> DeferredRegister<B> create(Identifier registryName, String modid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.create:(Lnet/minecraft/resources/Identifier;Ljava/lang/String;)Lnet/neoforged/neoforge/registries/DeferredRegister;");
    }

    // Pumpkin divergence: real body.
    protected DeferredRegister(ResourceKey<? extends Registry<T>> registryKey, String namespace) {
        this.pumpkinRegistryKey = registryKey;
        this.pumpkinNamespace = namespace;
    }

    // Pumpkin divergence: real body. Records the registration; nothing runs until the
    // RegisterEvent fires.
    public <I extends T> DeferredHolder<T, I> register(final String name, final Supplier<? extends I> sup) {
        DeferredHolder<T, I> holder =
                new DeferredHolder<>(Identifier.fromNamespaceAndPath(pumpkinNamespace, name), sup::get);
        pumpkinPending.add(holder);
        return holder;
    }

    public <I extends T> DeferredHolder<T, I> register(final String name, final Function<Identifier, ? extends I> func) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.register:(Ljava/lang/String;Ljava/util/function/Function;)Lnet/neoforged/neoforge/registries/DeferredHolder;");
    }

    public void addAlias(Identifier from, Identifier to) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.addAlias:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;)V");
    }

    // Pumpkin divergence: real body.
    public void register(IEventBus bus) {
        bus.addListener(RegisterEvent.class, event -> pumpkinFlush());
    }

    // Pumpkin divergence: no vanilla counterpart. Replays every recorded registration into
    // the sink once the event fires. Only blocks are supported so far; anything else stops
    // loudly rather than being silently dropped.
    private void pumpkinFlush() {
        for (DeferredHolder<T, ? extends T> holder : pumpkinPending) {
            Object object = holder.get();
            if (object instanceof Block block) {
                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate());
            } else {
                throw new IllegalStateException("registry " + pumpkinRegistryKey.identifier()
                        + " is not supported yet: " + holder.getId());
            }
        }
    }

    public static class Blocks extends DeferredRegister<Block> {

        protected Blocks(String namespace) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$Blocks.<init>:(Ljava/lang/String;)V");
        }

        public <B extends Block> DeferredBlock<B> register(String name, Function<Identifier, ? extends B> func) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$Blocks.register:(Ljava/lang/String;Ljava/util/function/Function;)Lnet/neoforged/neoforge/registries/DeferredBlock;");
        }

        public <B extends Block> DeferredBlock<B> register(String name, Supplier<? extends B> sup) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$Blocks.register:(Ljava/lang/String;Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/registries/DeferredBlock;");
        }

        protected <I extends Block> DeferredBlock<I> createHolder(ResourceKey<? extends Registry<Block>> registryKey, Identifier key) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$Blocks.createHolder:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;)Lnet/neoforged/neoforge/registries/DeferredBlock;");
        }

        protected Blocks() {
        }
    }

    public static class Items extends DeferredRegister<Item> {

        protected Items(String namespace) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$Items.<init>:(Ljava/lang/String;)V");
        }

        public <I extends Item> DeferredItem<I> register(String name, Function<Identifier, ? extends I> func) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$Items.register:(Ljava/lang/String;Ljava/util/function/Function;)Lnet/neoforged/neoforge/registries/DeferredItem;");
        }

        public <I extends Item> DeferredItem<I> register(String name, Supplier<? extends I> sup) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$Items.register:(Ljava/lang/String;Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/registries/DeferredItem;");
        }

        protected <I extends Item> DeferredItem<I> createHolder(ResourceKey<? extends Registry<Item>> registryKey, Identifier key) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$Items.createHolder:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;)Lnet/neoforged/neoforge/registries/DeferredItem;");
        }

        protected Items() {
        }
    }

    public static class DataComponents extends DeferredRegister<DataComponentType<?>> {

        protected DataComponents(ResourceKey<Registry<DataComponentType<?>>> registryKey, String namespace) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$DataComponents.<init>:(Lnet/minecraft/resources/ResourceKey;Ljava/lang/String;)V");
        }

        protected DataComponents() {
        }
    }

    public static class Entities extends DeferredRegister<EntityType<?>> {

        protected Entities(String namespace) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$Entities.<init>:(Ljava/lang/String;)V");
        }

        protected Entities() {
        }
    }

    private static class RegistryHolder<V> implements Supplier<Registry<V>> {

        private RegistryHolder(ResourceKey<? extends Registry<V>> registryKey) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$RegistryHolder.<init>:(Lnet/minecraft/resources/ResourceKey;)V");
        }

        public Registry<V> get() {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$RegistryHolder.get:()Lnet/minecraft/core/Registry;");
        }

        protected RegistryHolder() {
        }
    }

    protected DeferredRegister() {
    }
}
