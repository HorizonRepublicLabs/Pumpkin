package net.minecraft.client.data.models;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public class BlockModelGenerators {

    public final Consumer<BlockModelDefinitionGenerator> blockStateOutput = null;

    public final BiConsumer<Identifier, ModelInstance> modelOutput = null;

    public static MultiVariant variant(Variant variant) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/BlockModelGenerators.variant:(Lnet/minecraft/client/renderer/block/dispatch/Variant;)Lnet/minecraft/client/data/models/MultiVariant;");
    }

    public static MultiVariant plainVariant(Identifier model) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/BlockModelGenerators.plainVariant:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/data/models/MultiVariant;");
    }

    public BlockModelGenerators(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
    }

    public void run() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/BlockModelGenerators.run:()V");
    }

    public class BlockFamilyProvider {

        public BlockFamilyProvider(TextureMapping mapping) {
        }

        public BlockModelGenerators.BlockFamilyProvider sign(Block sign) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/BlockModelGenerators$BlockFamilyProvider.sign:(Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/client/data/models/BlockModelGenerators$BlockFamilyProvider;");
        }

        public BlockFamilyProvider() {
        }
    }

    public interface BlockStateGeneratorSupplier {

        BlockModelDefinitionGenerator create(Block block, Variant normal, TextureMapping mapping, BiConsumer<Identifier, ModelInstance> modelOutput);
    }

    public record BookSlotModelCacheKey(ModelTemplate template, String modelSuffix) {
    }

    public enum PlantType {

        TINTED, NOT_TINTED, EMISSIVE_NOT_TINTED
    }

    public class WoodProvider {

        public WoodProvider(TextureMapping logMapping) {
        }

        public WoodProvider() {
        }
    }

    public BlockModelGenerators() {
    }
}
