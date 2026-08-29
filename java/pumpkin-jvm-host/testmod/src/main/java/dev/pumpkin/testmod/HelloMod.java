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

    /**
     * System property this class's static initializer sets to {@code "true"} the moment it
     * runs. A test-only seam: {@code ModLoaderTest} reads it (as a plain string, since it has
     * no compile-time dependency on this class) to prove the initializer ran when the mod was
     * constructed and not earlier, while {@code ModLoader} was merely scanning the jar for the
     * {@code @Mod} class. The literal must stay in sync with the one hardcoded in the test.
     */
    public static final String STATIC_INITIALIZED_PROPERTY = "pumpkin.testmod.hellomod.staticInitialized";

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, ID);

    static {
        System.setProperty(STATIC_INITIALIZED_PROPERTY, "true");
        BLOCKS.register("ruby_block", () -> new Block(
                BlockBehaviour.Properties.of().pumpkinTemplate("stone").strength(4.5F)));
    }

    public HelloMod(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
