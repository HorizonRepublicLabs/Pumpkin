package net.minecraft.world.level.block;

import net.minecraft.world.level.block.state.BlockBehaviour;

/** A block. Carries only the template Pumpkin registers from. */
public class Block {
    private final BlockBehaviour.Properties properties;

    public Block(BlockBehaviour.Properties properties) {
        this.properties = properties;
    }

    public String pumpkinTemplate() {
        return properties.template();
    }
}
