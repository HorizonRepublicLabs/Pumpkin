package net.minecraft.world.level.block;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.BlockBehaviour;
import dev.pumpkin.shim.Unimplemented;

public class Blocks {

    public static final Block AIR = null;

    public static final Block WHEAT = null;

    public static final Block FARMLAND = null;

    public static final Block MYCELIUM = null;

    public static final Block CHORUS_FLOWER = null;

    private static Block register(BlockItemId id, BlockBehaviour.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Blocks.register:(Lnet/minecraft/references/BlockItemId;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;");
    }

    private static Block register(ResourceKey<Block> id, BlockBehaviour.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Blocks.register:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;");
    }

    public Blocks() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/Blocks");
        }
    }
}
