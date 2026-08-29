package net.neoforged.neoforge.data.event;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.server.packs.PackResources;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class GatherDataEvent extends Event implements IModBusEvent {

    public GatherDataEvent(final ModContainer mc, final DataGenerator dataGenerator, final DataGeneratorConfig dataGeneratorConfig) {
        throw Unimplemented.forMember("net/neoforged/neoforge/data/event/GatherDataEvent.<init>:(Lnet/neoforged/fml/ModContainer;Lnet/minecraft/data/DataGenerator;Lnet/neoforged/neoforge/data/event/GatherDataEvent$DataGeneratorConfig;)V");
    }

    public static class Server extends GatherDataEvent {

        public Server(ModContainer mc, DataGenerator dataGenerator, DataGeneratorConfig dataGeneratorConfig) {
            throw Unimplemented.forMember("net/neoforged/neoforge/data/event/GatherDataEvent$Server.<init>:(Lnet/neoforged/fml/ModContainer;Lnet/minecraft/data/DataGenerator;Lnet/neoforged/neoforge/data/event/GatherDataEvent$DataGeneratorConfig;)V");
        }

        protected Server() {
        }
    }

    public static class Client extends GatherDataEvent {

        public Client(ModContainer mc, DataGenerator dataGenerator, DataGeneratorConfig dataGeneratorConfig) {
            throw Unimplemented.forMember("net/neoforged/neoforge/data/event/GatherDataEvent$Client.<init>:(Lnet/neoforged/fml/ModContainer;Lnet/minecraft/data/DataGenerator;Lnet/neoforged/neoforge/data/event/GatherDataEvent$DataGeneratorConfig;)V");
        }

        protected Client() {
        }
    }

    public static class DataGeneratorConfig {

        public DataGeneratorConfig(final Set<String> mods, final Path path, final Collection<Path> inputs, final CompletableFuture<HolderLookup.Provider> lookupProvider, final boolean dev, final boolean reports, final boolean validate, final boolean flat, final DataGenerator vanillaGenerator, Collection<Path> existingPacks, Consumer<Consumer<PackResources>> vanillaClientAssets) {
            throw Unimplemented.forMember("net/neoforged/neoforge/data/event/GatherDataEvent$DataGeneratorConfig.<init>:(Ljava/util/Set;Ljava/nio/file/Path;Ljava/util/Collection;Ljava/util/concurrent/CompletableFuture;ZZZZLnet/minecraft/data/DataGenerator;Ljava/util/Collection;Ljava/util/function/Consumer;)V");
        }

        protected DataGeneratorConfig() {
        }
    }

    public interface DataProviderFromOutput<T extends DataProvider> {

        T create(PackOutput output);
    }

    public interface DataProviderFromOutputLookup<T extends DataProvider> {

        T create(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider);
    }

    public interface GatherDataEventGenerator {

        GatherDataEvent create(final ModContainer mc, final DataGenerator dataGenerator, final DataGeneratorConfig dataGeneratorConfig);
    }

    public interface ItemTagsProvider {

        TagsProvider<Item> create(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> contentsGetter);
    }

    protected GatherDataEvent() {
    }
}
