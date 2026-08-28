package dev.pumpkin.testmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(HelloMod.ID)
public class HelloMod {
    public static final String ID = "hellomod";

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, ID);

    static {
        BLOCKS.register("ruby_block", () -> new Block(BlockBehaviour.Properties.of().pumpkinTemplate("stone")));
    }

    public HelloMod(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
