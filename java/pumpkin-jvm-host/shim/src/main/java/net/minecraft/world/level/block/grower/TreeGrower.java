package net.minecraft.world.level.block.grower;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import dev.pumpkin.shim.Unimplemented;

public final class TreeGrower {

    public TreeGrower(String name, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/grower/TreeGrower.<init>:(Ljava/lang/String;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;)V");
    }

    public TreeGrower(String name, float secondaryChance, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/grower/TreeGrower.<init>:(Ljava/lang/String;FLjava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;)V");
    }

    public boolean growTree(ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/grower/TreeGrower.growTree:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)Z");
    }

    protected TreeGrower() {
    }
}
