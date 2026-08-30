package net.minecraft.world.level.block;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.BlockBehaviour;
import dev.pumpkin.shim.Unimplemented;

public class Blocks {

    // Pumpkin divergence: real values. Measured over both mods, every use of these is
    // reference identity -- `state.getBlock() == Blocks.FARMLAND` compiles to if_acmpeq --
    // or passing the object into shim code that reads its template. A canonical singleton
    // per vanilla block satisfies both: the shim is the only source of these objects, so
    // identity holds by construction, and the template ties each one to the Pumpkin block
    // it stands for. When shim state and Pumpkin's registry meet properly (a design step
    // still ahead), these are the objects that binding will hang off.
    public static final Block AIR = pumpkinVanilla("air");

    public static final Block WHEAT = pumpkinVanilla("wheat");

    // Pumpkin divergence: a real FarmlandBlock, not a bare template holder -- crop growth
    // reads state.getValue(FarmlandBlock.MOISTURE) off the soil, so the soil's state has
    // to declare the property.
    public static final Block FARMLAND =
            new FarmlandBlock(BlockBehaviour.Properties.of().pumpkinTemplate("farmland"));

    public static final Block MYCELIUM = pumpkinVanilla("mycelium");

    public static final Block CHORUS_FLOWER = pumpkinVanilla("chorus_flower");

    private static Block pumpkinVanilla(String name) {
        return new Block(BlockBehaviour.Properties.of().pumpkinTemplate(name));
    }

    private static Block register(BlockItemId id, BlockBehaviour.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Blocks.register:(Lnet/minecraft/references/BlockItemId;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;");
    }

    private static Block register(ResourceKey<Block> id, BlockBehaviour.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Blocks.register:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;");
    }

    public Blocks() {
    }

}
