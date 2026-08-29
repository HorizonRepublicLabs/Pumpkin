package net.minecraft.client.data.models.blockstates;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelDispatcher;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import dev.pumpkin.shim.Unimplemented;

public class MultiVariantGenerator implements BlockModelDefinitionGenerator {

    private MultiVariantGenerator(Block block, List<MultiVariantGenerator.Entry> entries, Set<Property<?>> seenProperties) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator.<init>:(Lnet/minecraft/world/level/block/Block;Ljava/util/List;Ljava/util/Set;)V");
    }

    public MultiVariantGenerator with(PropertyDispatch<VariantMutator> newStage) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator.with:(Lnet/minecraft/client/data/models/blockstates/PropertyDispatch;)Lnet/minecraft/client/data/models/blockstates/MultiVariantGenerator;");
    }

    public MultiVariantGenerator with(VariantMutator singleMutator) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator.with:(Lnet/minecraft/client/renderer/block/dispatch/VariantMutator;)Lnet/minecraft/client/data/models/blockstates/MultiVariantGenerator;");
    }

    public BlockStateModelDispatcher create() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator.create:()Lnet/minecraft/client/renderer/block/dispatch/BlockStateModelDispatcher;");
    }

    public Block block() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator.block:()Lnet/minecraft/world/level/block/Block;");
    }

    public static MultiVariantGenerator.Empty dispatch(Block block) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator.dispatch:(Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/client/data/models/blockstates/MultiVariantGenerator$Empty;");
    }

    public static MultiVariantGenerator dispatch(Block block, MultiVariant initialModel) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator.dispatch:(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/client/data/models/MultiVariant;)Lnet/minecraft/client/data/models/blockstates/MultiVariantGenerator;");
    }

    public static class Empty {

        public Empty(Block block) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator$Empty.<init>:(Lnet/minecraft/world/level/block/Block;)V");
        }

        public MultiVariantGenerator with(PropertyDispatch<MultiVariant> newStage) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator$Empty.with:(Lnet/minecraft/client/data/models/blockstates/PropertyDispatch;)Lnet/minecraft/client/data/models/blockstates/MultiVariantGenerator;");
        }

        public Empty() {
        }
    }

    private record Entry(PropertyValueList properties, MultiVariant variant) {

        public Stream<MultiVariantGenerator.Entry> apply(PropertyDispatch<VariantMutator> stage) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator$Entry.apply:(Lnet/minecraft/client/data/models/blockstates/PropertyDispatch;)Ljava/util/stream/Stream;");
        }

        public Stream<MultiVariantGenerator.Entry> apply(VariantMutator mutator) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/MultiVariantGenerator$Entry.apply:(Lnet/minecraft/client/renderer/block/dispatch/VariantMutator;)Ljava/util/stream/Stream;");
        }
    }

    public MultiVariantGenerator() {
    }
}
