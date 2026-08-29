package net.minecraft.client.data.models;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.IModelProviderExtension;
import dev.pumpkin.shim.Unimplemented;

public class ModelProvider implements DataProvider, IModelProviderExtension {

    public ModelProvider(PackOutput output) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider.<init>:(Lnet/minecraft/data/PackOutput;)V");
    }

    public ModelProvider(PackOutput output, String modId) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider.<init>:(Lnet/minecraft/data/PackOutput;Ljava/lang/String;)V");
    }

    public CompletableFuture<?> run(CachedOutput cache) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider.run:(Lnet/minecraft/data/CachedOutput;)Ljava/util/concurrent/CompletableFuture;");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider.getName:()Ljava/lang/String;");
    }

    private static class BlockStateGeneratorCollector implements Consumer<BlockModelDefinitionGenerator> {

        public BlockStateGeneratorCollector(Supplier<java.util.stream.Stream<? extends net.minecraft.core.Holder<Block>>> knownBlocks) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$BlockStateGeneratorCollector.<init>:(Ljava/util/function/Supplier;)V");
        }

        public BlockStateGeneratorCollector() {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$BlockStateGeneratorCollector.<init>:()V");
        }

        public void accept(BlockModelDefinitionGenerator generator) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$BlockStateGeneratorCollector.accept:(Lnet/minecraft/client/data/models/blockstates/BlockModelDefinitionGenerator;)V");
        }

        public CompletableFuture<?> save(CachedOutput cache, PackOutput.PathProvider pathProvider) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$BlockStateGeneratorCollector.save:(Lnet/minecraft/data/CachedOutput;Lnet/minecraft/data/PackOutput$PathProvider;)Ljava/util/concurrent/CompletableFuture;");
        }
    }

    private static class ItemInfoCollector implements ItemModelOutput {

        public ItemInfoCollector(Supplier<java.util.stream.Stream<? extends net.minecraft.core.Holder<Item>>> knownItems) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$ItemInfoCollector.<init>:(Ljava/util/function/Supplier;)V");
        }

        public ItemInfoCollector() {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$ItemInfoCollector.<init>:()V");
        }

        public void accept(Item item, ItemModel.Unbaked model, ClientItem.Properties properties) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$ItemInfoCollector.accept:(Lnet/minecraft/world/item/Item;Lnet/minecraft/client/renderer/item/ItemModel$Unbaked;Lnet/minecraft/client/renderer/item/ClientItem$Properties;)V");
        }

        public void register(Item item, ClientItem itemInfo) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$ItemInfoCollector.register:(Lnet/minecraft/world/item/Item;Lnet/minecraft/client/renderer/item/ClientItem;)V");
        }

        public void register(Identifier identifier, ClientItem clientItem) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$ItemInfoCollector.register:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/renderer/item/ClientItem;)V");
        }

        public void copy(Item donor, Item acceptor) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$ItemInfoCollector.copy:(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/Item;)V");
        }

        public CompletableFuture<?> save(CachedOutput cache, PackOutput.PathProvider pathProvider) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$ItemInfoCollector.save:(Lnet/minecraft/data/CachedOutput;Lnet/minecraft/data/PackOutput$PathProvider;)Ljava/util/concurrent/CompletableFuture;");
        }
    }

    private static class SimpleModelCollector implements BiConsumer<Identifier, ModelInstance> {

        public void accept(Identifier id, ModelInstance contents) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$SimpleModelCollector.accept:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/data/models/model/ModelInstance;)V");
        }

        public CompletableFuture<?> save(CachedOutput cache, PackOutput.PathProvider pathProvider) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/ModelProvider$SimpleModelCollector.save:(Lnet/minecraft/data/CachedOutput;Lnet/minecraft/data/PackOutput$PathProvider;)Ljava/util/concurrent/CompletableFuture;");
        }

        protected SimpleModelCollector() {
        }
    }

    public ModelProvider() {
    }
}
