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

        // Pumpkin divergence: the wide path. strength() and requiresCorrectToolForDrops()
        // record onto Properties precisely so these can arrive; a sink that cannot carry
        // them re-creates the bug where a mod's stone-hard block registers dirt-hard.
        // Default implementation drops them so the single-method lambda tests keep working
        // -- but the production sink overrides it.
        default int registerBlock(String id, String template, Float destroyTime,
                Float explosionResistance, boolean requiresTool) {
            return registerBlock(id, template);
        }

        // Pumpkin divergence: items too. Throwing, not dropping, is the default so a
        // block-only test sink that unexpectedly receives an item fails saying why;
        // the production sink in Bootstrap overrides this with the real native.
        default int registerItem(String id, String template) {
            throw new IllegalStateException("this sink cannot register items: " + id);
        }

        // Pumpkin divergence: the wide path, mirroring the block one above. stacksTo()
        // and durability() record onto Item.Properties precisely so these can arrive;
        // blockId links a BlockItem to the block it places. Default drops them so
        // single-method test sinks keep working -- the production sink overrides it.
        default int registerItem(String id, String template, int maxStackSize,
                int maxDamage, String blockId) {
            return registerItem(id, template);
        }

        // Pumpkin divergence: block entity types too. Same contract as registerItem's
        // narrow default: throwing, not dropping, so a sink that cannot take one says so.
        default int registerBlockEntityType(String id) {
            throw new IllegalStateException("this sink cannot register block entity types: " + id);
        }

        // Pumpkin divergence: same contract as the two above.
        default int registerMenuType(String id) {
            throw new IllegalStateException("this sink cannot register menu types: " + id);
        }

        default int registerSoundEvent(String id) {
            throw new IllegalStateException("this sink cannot register sound events: " + id);
        }

        default int registerDataComponentType(String id) {
            throw new IllegalStateException("this sink cannot register data component types: " + id);
        }
    }

    private static Sink pumpkinSink = (id, template) -> {
        throw new IllegalStateException("no registration sink installed for " + id);
    };

    public static void setSink(Sink replacement) {
        pumpkinSink = replacement;
    }

    // Pumpkin divergence: no vanilla counterpart. Registrations into registries Pumpkin
    // does not model -- data components, recipe serializers, sounds -- are accepted and
    // said out loud once per registry, not thrown and not silently dropped. Throwing
    // stopped a whole mod over content that cannot matter until the thing consuming it
    // exists; silence is the failure this project refuses everywhere. Same line
    // registerConfig draws: the mod goes on, and the operator knows what is missing.
    static void pumpkinWarnUnsupported(String registry, String entry) {
        if (PUMPKIN_UNSUPPORTED_WARNED.add(registry)) {
            String explanation = PUMPKIN_ACKNOWLEDGED.get(registry);
            if (explanation != null) {
                System.err.println("[pumpkin] " + registry + " (e.g. " + entry + "): " + explanation);
            } else {
                System.err.println("[pumpkin] " + registry + " is not modelled yet; entries like "
                        + entry + " are accepted so their mod can load, but nothing reads them.");
            }
        }
    }

    // Pumpkin divergence: no vanilla counterpart. Registries whose entries the server
    // understands well enough to say exactly why its job ends at accepting them. Each
    // message names the missing subsystem, so the generic "not modelled yet" line is
    // reserved for registries nothing has looked at.
    private static final java.util.Map<String, String> PUMPKIN_ACKNOWLEDGED = java.util.Map.of(
            "minecraft:recipe_type",
            "vanilla-typed recipes from the mod's datapack already load and craft; a custom"
                    + " recipe type is its machine's input, and mod machines do not run"
                    + " server-side yet.",
            "minecraft:recipe_serializer",
            "Pumpkin parses vanilla recipe formats itself; a custom serializer's format is"
                    + " skipped and counted when the mod's datapack loads.",
            "minecraft:worldgen/feature",
            "world generation does not take mod features yet; ores and plants from mods"
                    + " will not spawn until generation opens up to them.",
            "neoforge:ingredient_serializer",
            "reads custom ingredient JSON, which only appears in recipe formats Pumpkin"
                    + " already skips and counts.",
            "neoforge:condition_codecs",
            "datapack load conditions; Pumpkin loads a mod's datapack unconditionally, so"
                    + " a condition meant to disable content is not evaluated.",
            "neoforge:biome_modifier_serializers",
            "biome modifiers steer world generation, which does not take mod input yet.");

    private static final java.util.Set<String> PUMPKIN_UNSUPPORTED_WARNED =
            java.util.concurrent.ConcurrentHashMap.newKeySet();

    // Pumpkin divergence: no vanilla counterpart. A creative tab is client-side
    // presentation -- the protocol never carries it, so there is nothing for a server to
    // store. Running its generator is still real work: every holder the mod put in the
    // tab must resolve, which catches a broken registration at load time. Package-private
    // for the same reason as pumpkinSink().
    static void pumpkinReportCreativeTab(String id, net.minecraft.world.item.CreativeModeTab tab) {
        int entries = tab.pumpkinRunDisplayItems();
        System.out.println("[pumpkin] creative tab " + id + ": " + entries
                + " entries resolved; tabs are client-side presentation, so the server's job ends here.");
    }

    // Pumpkin divergence: no vanilla counterpart. RegisterEvent registers straight into the
    // game rather than through a DeferredRegister, so it needs the same sink. Package-private
    // because only its sibling in this package has any business reaching it.
    static Sink pumpkinSink() {
        return pumpkinSink;
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
        // This is the one place that knows both the registry and the holder; see
        // DeferredHolder.pumpkinRecord for why the key needs both.
        DeferredHolder.pumpkinRecord(pumpkinRegistryKey.identifier().toString(), holder);
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
                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();
                // Recorded on the block so its BlockItem, registering later, can name it.
                block.pumpkinSetRegisteredId(holder.getId().toString());
                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate(),
                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),
                        props.pumpkinRequiresTool());
            } else if (object instanceof net.minecraft.world.item.Item item) {
                pumpkinSink.registerItem(holder.getId().toString(), item.pumpkinTemplate(),
                        item.pumpkinMaxStackSize(), item.pumpkinMaxDamage(),
                        item.pumpkinPlacedBlockId());
            } else if (object instanceof net.minecraft.world.level.block.entity.BlockEntityType) {
                pumpkinSink.registerBlockEntityType(holder.getId().toString());
            } else if (object instanceof net.minecraft.world.item.CreativeModeTab tab) {
                pumpkinReportCreativeTab(holder.getId().toString(), tab);
            } else if (object instanceof net.minecraft.world.inventory.MenuType) {
                pumpkinSink.registerMenuType(holder.getId().toString());
            } else if (object instanceof net.minecraft.sounds.SoundEvent) {
                pumpkinSink.registerSoundEvent(holder.getId().toString());
            } else if (object instanceof net.minecraft.core.component.DataComponentType) {
                pumpkinSink.registerDataComponentType(holder.getId().toString());
            } else {
                pumpkinWarnUnsupported(pumpkinRegistryKey.identifier().toString(),
                        holder.getId().toString());
            }
        }
    }

    public static class Blocks extends DeferredRegister<Block> {

        protected Blocks(String namespace) {
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

        public Blocks() {
        }
    }

    public static class Items extends DeferredRegister<Item> {

        protected Items(String namespace) {
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

        public Items() {
        }
    }

    public static class DataComponents extends DeferredRegister<DataComponentType<?>> {

        protected DataComponents(ResourceKey<Registry<DataComponentType<?>>> registryKey, String namespace) {
        }

        public DataComponents() {
        }
    }

    public static class Entities extends DeferredRegister<EntityType<?>> {

        protected Entities(String namespace) {
        }

        public Entities() {
        }
    }

    private static class RegistryHolder<V> implements Supplier<Registry<V>> {

        private RegistryHolder(ResourceKey<? extends Registry<V>> registryKey) {
        }

        public Registry<V> get() {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister$RegistryHolder.get:()Lnet/minecraft/core/Registry;");
        }

        protected RegistryHolder() {
        }
    }

    public DeferredRegister() {
    }
}
